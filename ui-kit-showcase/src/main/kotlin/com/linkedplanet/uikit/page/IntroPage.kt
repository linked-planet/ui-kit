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

import com.linkedplanet.uikit.component.LPEditor
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.TextArea
import org.w3c.dom.HTMLTextAreaElement
import react.Props
import react.dom.button
import react.dom.div
import react.dom.html.ReactHTML.h1
import react.fc
import react.useState
import kotlin.js.json


external interface IntroPageProps : Props

val IntroPage = fc<IntroPageProps> { _ ->

    val (editorString, setEditorString) = useState("<h1>Hello \$object.Name</h1>")
    val (objectString, setObjectString) = useState("""{ "object": { "Name": { "lol" : "inception" } } }""")

    div {
        h1 {
            +"Editor Test!"
        }

        button {
            //attrs { onClick={showRefs()} }
            +"Show Editor Ref"
        }

        div {
            attrs["style"] = json(
                "backgroundColor" to "gray",
                "display" to "flex",
                "flexDirection" to "column",
                "minHeight" to "0",
                "minWidth" to "0",
                "flex" to "1",
                "width" to "100%",
                "height" to "100%"
            )

            div {
                attrs["style"] = json(
                    "flex" to "0",
                    "minHeight" to "50px",
                    "width" to "100%",
                    "minWidth" to "100%",
                    "display" to "flex"
                )
                // Json-String
                TextArea {
                    attrs.value = objectString
                    attrs.onChange = { event ->
                        console.info("onChange:", event.target)
                        setObjectString((event.target as HTMLTextAreaElement).value)
                    }
                }
            }

            div {
                attrs["style"] = json(
                    "flex" to "1",
                    "minHeight" to "250px",
                    "width" to "100%"
                )

                console.info("Reload IntroPage with ObjectString", objectString)

                // Editor
                LPEditor {
                    attrs.editorString = editorString
                    attrs.objectString = objectString
                    attrs.onChange = {
                        console.info("OnChange")
                        console.info(it)
                    }
                }
            }
        }

    }
}
