package com.linkedplanet.uikit.util

import com.linkedplanet.uikit.component.ShowcaseWrapperItemProps
import react.Props
import react.RBuilder

fun RBuilder.ShowcaseWrapperItem(handler: ShowcaseWrapperItemProps.() -> Unit) =
    child(com.linkedplanet.uikit.component.ShowcaseWrapperItem) { attrs { handler() } }

external interface ShowcaseProps: Props {
    var overallSourceCode: String
}
