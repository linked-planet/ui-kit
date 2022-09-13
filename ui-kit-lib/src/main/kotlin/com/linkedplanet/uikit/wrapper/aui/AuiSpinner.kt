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
package com.linkedplanet.uikit.wrapper.aui

import com.linkedplanet.uikit.util.createElementNullSafe
import kotlinx.html.HTMLTag
import react.ChildrenBuilder
import react.RBuilder
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RBuilder.auiSpinner(block: RDOMBuilder<HTMLTag>.() -> Unit): Unit = tag(block) {
    HTMLTag("aui-spinner", it, mapOf(), null, inlineTag = true, false)
}

fun ChildrenBuilder.auiSpinner() {
    this.child(createElementNullSafe { auiSpinner {} })
}
