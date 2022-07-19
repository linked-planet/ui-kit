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

import com.linkedplanet.uikit.imports.Editor
import com.linkedplanet.uikit.operator.EditorOperator
import com.linkedplanet.uikit.operator.EditorOperator.Item
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.TextArea
import kotlinx.js.Object
import kotlinx.js.ReadonlyArray
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

    fun flatObject(parentKey: String, obj: dynamic): List<Item> {
        val keys: ReadonlyArray<String> = Object.keys(obj)
        return keys.flatMap { key: String ->
            val value = obj[key]
            if (value == null || value == undefined) {
                listOf(Item(parentKey, key, ""))
            } else if (Object.keys(value as Any).isNotEmpty() && value !is String) { // assumes value is object
                flatObject("$parentKey$key.", value).plus(Item(parentKey, key, value.toString()))
            } else {
                listOf(Item(parentKey, key, value.toString()))
            }
        }
    }

    div {
        h1 {
            +"Editor Test!"
        }

    }
}
