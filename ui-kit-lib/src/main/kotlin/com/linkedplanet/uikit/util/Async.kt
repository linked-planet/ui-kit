/**
 * Copyright 2022 linked-planet GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedplanet.uikit.util

import kotlin.coroutines.*
import kotlin.js.Promise

/*
 * Utility that can be used to asynchronously send HTTP requests, while only
 * executing callbacks on success for the latest request of that type.
 *
 * Consider a dropdown that causes an HTTP request to be sent on selection
 * change. If the user changes the selection in quick succession, multiple
 * HTTP requests will be in flight. The responses for these requests are
 * not guaranteed to arrive in order. But to be consistent, the UI must
 * only update in accordance with the latest selection. Thus, the `complete`
 * function takes care of discarding success responses of obsolete requests.
 */
@Suppress("unused")
object Async {

    private var taskId: MutableMap<String, Int> = mutableMapOf()

    /**
     * Runs the given task function asynchronously.
     *
     * Will execute the completion function once the result is available, but only if no
     * other async request with the same task name has been started in the meantime.
     *
     * Will execute the catch function if the task fails, **no matter** whether another request
     * with same task name has been started in the meantime.
     */
    fun <T> complete(
        taskName: String,
        taskFun: suspend (Int) -> T,
        completeFun: (T) -> Unit,
        catchFun: (Throwable) -> Unit = { }
    ) {
        val thisId = incrementTaskId(taskName)
        async {
            val result = taskFun(thisId)
            if (taskId[taskName] == thisId) {
                completeFun(result)
            }
        }.catch {
            catchFun(it)
        }
    }

    /**
     * Increments the task id so any pending complete handler will be ignored.
     * Useful to avoid CState changes when React components already unmounted.
     */
    fun clear(taskName: String) {
        incrementTaskId(taskName)
    }

    private fun incrementTaskId(taskName: String): Int {
        val nextId = taskId[taskName]?.let { it + 1 } ?: 0
        taskId[taskName] = nextId
        return nextId
    }
}

private fun <T> async(block: suspend () -> T): Promise<T> = Promise { resolve, reject ->
    block.startCoroutine(object : Continuation<T> {
        override val context: CoroutineContext get() = EmptyCoroutineContext
        override fun resumeWith(result: Result<T>) {
            result.fold(
                onSuccess = { value -> resolve(value) },
                onFailure = { exception -> reject(exception) }
            )
        }
    })
}
