package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.style.ShowcaseStyles
import react.*
import react.dom.div
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.h3
import react.dom.span
import styled.css
import styled.styledDiv


external interface ShowcaseItemProps : PropsWithChildren {
    var name: String
    var packageName: String
    var docUrl: String?

    var examples: List<ReactElement>
}

val ShowcaseItem = fc<ShowcaseItemProps> { props ->

    styledDiv {
        css {
            +ShowcaseStyles.showcaseItem
        }

        h3 {
            +props.name
        }

        div {
            props.docUrl
                ?.let { docUrl ->
                    span {
                        +"Package: "
                    }
                    a {
                        attrs.href = docUrl
                        +props.packageName
                    }
                }
                ?: span {
                    +"Package: ${props.packageName}"
                }
        }

        div {
            span {
                +"Examples:"
            }

            styledDiv {
                css {
                    +ShowcaseStyles.showcaseItemExamplesContainer
                }
                props.examples.forEach {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseItemExample
                        }
                        +it
                    }
                }
            }
        }
    }
}

fun RBuilder.ShowcaseItem(handler: ShowcaseItemProps.() -> Unit) =
    child(ShowcaseItem) { attrs { handler() } }
