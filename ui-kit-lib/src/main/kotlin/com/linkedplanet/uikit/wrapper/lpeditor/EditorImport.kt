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
@file:JsModule("@monaco-editor/react")

package com.linkedplanet.uikit.wrapper.lpeditor

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props
import kotlin.js.Json


@JsName("default")
external var Editor: ComponentClass<EditorProps>

external interface EditorProps : Props {
    //TODO: documentation
    var height: String
    var width: String
    var defaultLanguage: String
    var defaultValue: String
    var value: String
    var onMount: (dynamic, dynamic) -> Unit // editor, monaco
    var onChange: (String, Event) -> Unit
    var beforeMount: (dynamic) -> Unit // monaco
    var options: Json
    var theme: String
}

external interface Position : Props {
    var lineNumber: Int
    var column: Int
}
