package com.linkedplanet.uikit

import com.linkedplanet.uikit.component.Showcase
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

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
