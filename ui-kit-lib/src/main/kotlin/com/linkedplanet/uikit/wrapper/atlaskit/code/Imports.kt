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
@file:JsModule("@atlaskit/code")

package com.linkedplanet.uikit.wrapper.atlaskit.code

import react.ComponentClass
import react.Props

@JsName("CodeBlock")
external val CodeBlock: ComponentClass<CodeBlockProps>

external interface CodeBlockProps : Props {

    /**
     * The code to be formatted.
     */
    var text: String

    /**
     * Whether to showLineNumbers or not, defaults to true.
     */
    var showLineNumbers: Boolean

    /**
     * The language in which the code is written.
     */
    var language: String

    /**
     * Lines to highlight comma delimited. Example uses:
     * - To highlight one line highlight="3"
     * - To highlight a group of lines highlight="1-5"
     * - To highlight multiple groups highlight="1-5,7,10,15-20"
     */
    var highlight: String
}
