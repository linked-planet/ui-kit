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

import com.linkedplanet.uikit.wrapper.mimetypes.lookupMimeType
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.*
import org.w3c.files.File
import org.w3c.files.FilePropertyBag
import kotlin.js.json
import kotlin.random.Random

@Suppress("unused", "MemberVisibilityCanBePrivate")
object RequestUtil {

    suspend fun <T> requestAndParseNullableResult(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        parse: (dynamic) -> T
    ): T? =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                val statusCode = response.status.toInt()
                when {
                    statusCode < 400 -> parse(response.json().await())
                    statusCode == 401 -> throw SessionExpiredException()
                    statusCode == 404 -> null
                    else -> throw BadStatusCodeException(response.status.toInt())
                }
            }
        )

    suspend fun <T> requestAndParseResultWithError(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        parse: (dynamic) -> T
    ): T =
        requestAndHandleError(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                val statusCode = response.status.toInt()
                when {
                    statusCode < 400 -> {
                        val json = response.json().await()
                        parse(json)
                    }

                    statusCode == 400 -> {
                        val json = response.json().await().asDynamic()
                        throw FrontendException(json.error as String, json.message as String)
                    }

                    statusCode == 401 -> throw SessionExpiredException()

                    else -> {
                        throw BadStatusCodeException(response.status.toInt())
                    }
                }
            }
        )

    suspend fun <T> requestAndParseResult(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        parse: (dynamic) -> T
    ): T =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                if (response.status < 400) {
                    val json = response.json().await()
                    parse(json)
                } else {
                    throw BadStatusCodeException(response.status.toInt())
                }
            }
        )

    suspend fun <T> requestAndHandleSuccess(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        handler: suspend (Response) -> T
    ): T =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                if (response.status < 400) {
                    handler(response)
                } else {
                    throw BadStatusCodeException(response.status.toInt())
                }
            }
        )

    suspend fun <T> requestAndHandleError(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        handler: suspend (Response) -> T
    ): T =
        requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = { response ->
                val status = response.status.toInt()
                when {
                    status < 400 -> handler(response)

                    status == 400 -> {
                        val json = response.json().await().asDynamic()
                        throw FrontendException(json.error as String, json.message as String)
                    }

                    status == 401 -> throw SessionExpiredException()

                    else -> throw BadStatusCodeException(response.status.toInt())
                }
            }
        )

    suspend fun <T> requestAndHandle(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        handler: suspend (Response) -> T
    ): T {
        DevOptions.RandomHttpDelay.randomDelay()
        DevOptions.FailSpecificHttpRequests.failIfDesired(method, url)
        val chaosCausedFailure = GlobalOptions.chaosMode && Random.nextBoolean()
        val response = if (chaosCausedFailure) {
            console.log("CHAOS: $method - $url")
            throw ChaosModeException()
        } else {
            window.fetch(url, object : RequestInit {
                override var method: String? = method
                override var headers: dynamic = headers
                override var credentials: RequestCredentials? = requestCredentials
                override var redirect: RequestRedirect? = requestRedirect
                override var body: dynamic = body
            }).await()
        }
        return handler(response)
    }

    suspend fun requestAndDownload(
        url: String,
        method: String = "GET",
        headers: dynamic = json(),
        requestCredentials: RequestCredentials = RequestCredentials.SAME_ORIGIN,
        requestRedirect: RequestRedirect = RequestRedirect.FOLLOW,
        body: dynamic = null,
        defaultMimeType: String? = null,
        defaultFilename: String? = null
    ): File {
        return requestAndHandle(
            url = url,
            method = method,
            headers = headers,
            requestCredentials = requestCredentials,
            requestRedirect = requestRedirect,
            body = body,
            handler = {
                val blob = it.blob().await()
                val contentDisposition = it.headers.get("Content-Disposition") ?: ""
                val filename = defaultFilename ?: (parseFilenameFromDisposition(contentDisposition)
                    ?: error("Couldn't detect filename for download"))
                val contentType =
                    defaultMimeType ?: (it.headers.get("Content-Type") ?: parseContentTypeByExtension(filename))
                val fileProperties = FilePropertyBag(type = contentType)
                File(arrayOf(blob), filename, fileProperties)
            })
    }

    fun parseFilenameFromDisposition(disposition: String): String? =
        "filename=\"(.*)\"".toRegex().find(disposition)?.groups?.get(1)?.value

    fun parseContentTypeByExtension(filename: String): String? {
        return lookupMimeType(filename).takeIf { it != "false" }
    }

    fun entriesOf(jsObject: dynamic): List<Pair<String, Any?>> =
        (js("Object.entries") as (dynamic) -> Array<Array<Any?>>)
            .invoke(jsObject)
            .map { entry -> entry[0] as String to entry[1] }

    fun mapOf(jsObject: dynamic): Map<String, Any?> =
        entriesOf(jsObject).toMap()

}

data class ProblemDescription(val title: String, val detail: String)

class FrontendException(val error: String, val detailMessage: String) : Exception()

class ChaosModeException : RuntimeException()

open class BadStatusCodeException(val statusCode: Int, val problemDescription: ProblemDescription? = null) :
    RuntimeException()

class SessionExpiredException :
    BadStatusCodeException(401, ProblemDescription("Session expired", "Please start a new session."))
