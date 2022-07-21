@file:JsModule("@monaco-editor/react")

package com.linkedplanet.uikit.imports

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props
import kotlin.js.Json
import kotlin.js.json


@JsName("default")
external val Editor: ComponentClass<EditorProps>

//@JsName("loader")
//external val Loader:

external interface EditorProps : Props {
    var height: String
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
