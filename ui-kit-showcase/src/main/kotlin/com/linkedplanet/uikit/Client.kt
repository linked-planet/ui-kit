package com.linkedplanet.uikit

import com.linkedplanet.uikit.component.Showcase
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

@ExperimentalJsExport
fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Showcase::class) {
                attrs {
                    name = "Kotlin/JS"
                }
            }
        }
    }
}
