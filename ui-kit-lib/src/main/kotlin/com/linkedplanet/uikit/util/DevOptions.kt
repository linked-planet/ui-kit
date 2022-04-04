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

import kotlinx.coroutines.delay
import kotlin.random.Random

@Suppress("ConstantConditionIf")
object DevOptions {

    abstract class DevOption(protected val enabled: Boolean) {
        init {
            if (enabled) {
                console.log("======= ATTENTION: ${this::class.simpleName} developer option is enabled !!! =======")
                console.log("======= If this is production, re-build the application without the flag =======")
            }
        }
    }

    object FailSpecificHttpRequests : DevOption(false) {
        private val failRoutes: List<FailRoute> = listOf(
        )

        suspend fun failIfDesired(method: String, path: String) {
            if (enabled) {
                failRoutes
                    .find { it.method == method && it.pathRegex.containsMatchIn(path) }
                    ?.let { route ->
                        console.log("${this::class.simpleName} --> $route")
                        delay(500)
                        throw FailSpecificHttpRequestException()
                    }
            }
        }

        data class FailRoute(val method: String, val pathRegex: Regex)

        class FailSpecificHttpRequestException : RuntimeException()
    }

    object RandomHttpDelay : DevOption(false) {
        private const val maxDelayMillis = 1500L

        suspend fun randomDelay() {
            if (enabled) {
                val delayMillis = Random.nextLong(maxDelayMillis)
                console.log("${this::class.simpleName} --> $delayMillis ms")
                delay(delayMillis)
            }
        }
    }
}
