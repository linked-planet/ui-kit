package com.linkedplanet.uikit.style

import kotlinx.css.*
import styled.StyleSheet

object ShowcaseStyles : StyleSheet("ShowcaseStyles") {
    val showcaseItemsContainer by css {
        margin(50.px)

        children {
            borderBottom = "1px solid #EBECF0"
        }

        lastChild {
            borderBottom = "none"
        }
    }

    val showcaseItem by css {
        padding(20.px)
    }

    val showcaseItemExamplesContainer by css {
        display = Display.flex
    }

    val showcaseItemExample by css {
        border = "1px solid #EBECF0"
        margin(5.px)
        padding(5.px)

        firstChild {
            marginLeft = 0.px
        }
    }

    val showcaseItemExampleDateTimePicker by css {
        minWidth = 300.px
    }
}