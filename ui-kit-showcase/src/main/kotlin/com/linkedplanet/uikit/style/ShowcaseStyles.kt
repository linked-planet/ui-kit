package com.linkedplanet.uikit.style

import kotlinx.css.*
import styled.StyleSheet

object ShowcaseStyles : StyleSheet("ShowcaseStyles", isStatic = true) {
    val mainContainer by css {
        margin(30.px)
    }

    val showcaseDemoItem by css {
        margin(20.px, 0.px, 10.px)
    }
}