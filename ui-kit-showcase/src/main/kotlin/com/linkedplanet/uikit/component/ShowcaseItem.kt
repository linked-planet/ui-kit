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
package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.style.ShowcaseStyles
import react.*
import react.dom.div
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.h3
import react.dom.span
import styled.css
import styled.styledDiv


external interface ShowcaseItemProps : PropsWithChildren {
    var name: String
    var packages: List<Package>

    var examples: List<ReactElement>
}

data class Package(
    val name: String,
    val url: String
) {
    fun toList(): List<Package> = listOf(this)
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
            span {
                +"Packages: "
            }
            props.packages.forEachIndexed { index, pack ->
                a {
                    attrs.href = pack.url
                    attrs.target = AnchorTarget._blank
                    +pack.name
                }
                if (index != props.packages.size - 1) {
                    +", "
                }
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
