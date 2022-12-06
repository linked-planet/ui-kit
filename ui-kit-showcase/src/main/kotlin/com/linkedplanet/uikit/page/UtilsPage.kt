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
package com.linkedplanet.uikit.page

import com.linkedplanet.uikit.wrapper.atlaskit.code.CodeBlock
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.p
import react.fc

external interface UtilsPageProps : Props

val UtilsPage = fc<UtilsPageProps> { _ ->

    div {
        h1 {
            +"Utilities"
        }

        // --------------------------------------
        h3 {
            +"RequestUtil"
        }

        p {
            +"RequestUtil contains multiple functions to execute a http request and parse the corresponding json response."
        }

        h5 {
            +"Example"
        }

        CodeBlock {
            attrs.language = "kotlin"
            attrs.text = """
                requestAndParseResultWithError(
                    url = "/rest/v1/items",
                    headers = json(
                        "Accept" to "application/json"
                    ),
                    parse = { json ->
                        Object.keys(json).map { item ->
                            val entries = json[item] as Array<dynamic>
                            item.toInt() to
                                    entries.map {
                                        Item(
                                            it.id as Int,
                                            it.name as String,
                                            it.parentId as Int
                                        )
                                    }
                        }.toMap()
                    }
                )
            """.trimIndent()
        }

        // --------------------------------------
        h3 {
            +"Async"
        }

        p {
            +"Utility that can be used to asynchronously send HTTP requests, while only executing callbacks on "
            +"success for the latest request of that type."
        }

        p {
            +"Consider a dropdown that causes an HTTP request to be sent on selection "
            +"change. If the user changes the selection in quick succession, multiple "
            +"HTTP requests will be in flight. The responses for these requests are "
            +"not guaranteed to arrive in order. But to be consistent, the UI must "
            +"only update in accordance with the latest selection. Thus, the `complete` "
            +"function takes care of discarding success responses of obsolete requests. "
        }

        h5 {
            +"Example"
        }

        CodeBlock {
            attrs.language = "kotlin"
            attrs.text = """
                Async.complete(
                    taskName = "send-documents",
                    taskFun = {
                        SendDocumentsRequest.sendDocuments(
                            methodByIssue
                                .filterKeys { selectedIssues.contains(it) }
                                .filterNullValues()
                        )
                    },
                    completeFun = {
                        console.log("success")
                    },
                    catchFun = {
                        console.log("error")
                    }
                )
            """.trimIndent()
        }
    }
}
