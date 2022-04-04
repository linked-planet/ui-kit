package com.linkedplanet.uikit.page

import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.emptystate.EmptyState
import kotlinx.browser.window
import react.*
import react.dom.span

external interface NotFoundPageProps : Props

val NotFoundPage = fc<NotFoundPageProps> { props ->

    EmptyState {
        attrs.header = "404 - Not Found"
        attrs.description = createElement {
            span {
                +"The page you were looking for doesn't exist..."
            }
        }!!

        attrs.primaryAction = createElement {
            Button {
                attrs.onClick = {
                    window.location.href = "/"
                }
                attrs.appearance = "primary"
                +"Back to start"
            }
        }!!
    }
}
