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
package com.linkedplanet.uikit.wrapper.dom

import org.w3c.dom.Element

fun Element.getStyle(): Map<String, String> {
    return this.getAttribute("style")
        ?.split(";")
        ?.filter { it.contains(":") }
        ?.associate { entry ->
            val entryKeyValue = entry.split(":")
            val key = entryKeyValue[0].trim()
            val value = entryKeyValue[1].trim()
            key to value
        }
        ?: emptyMap()
}

fun Element.setStyle(style: Map<String, String>) {
    this.setAttribute("style", style.map { "${it.key}: ${it.value}" }.joinToString(separator = ";"))
}

@Suppress("unused")
fun Element.getStyleAttribute(key: String): String? {
    return getStyle()[key]
}

@Suppress("unused")
fun Element.setStyleAttribute(key: String, value: String) {
    val style = getStyle().toMutableMap()
    style[key] = value
    setStyle(style)
}