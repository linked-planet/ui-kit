package com.linkedplanet.uikit.page

import react.Props
import react.dom.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.span
import react.fc

external interface IntroPageProps : Props

val IntroPage = fc<IntroPageProps> { props ->

    div {
        h1 {
            +"Welcome to UI-Kit"
        }

        h3 {
            +"Usage"
        }

        span {
            +"There will be more content soon..."
        }
    }
}
