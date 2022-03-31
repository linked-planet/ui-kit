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