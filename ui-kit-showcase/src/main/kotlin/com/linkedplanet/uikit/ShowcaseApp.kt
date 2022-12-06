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
package com.linkedplanet.uikit

import com.linkedplanet.uikit.component.ShowcaseLeftSidebar
import com.linkedplanet.uikit.component.ShowcaseTopNavigation
import com.linkedplanet.uikit.page.*
import com.linkedplanet.uikit.util.createElementNullSafe
import com.linkedplanet.uikit.wrapper.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.*
import csstype.*
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.router.*
import react.router.dom.HashRouter
import kotlin.js.Date

external interface ShowcaseAppProps : Props {
    var name: String
}

external interface ShowcaseAppState : State

class ShowcaseApp(props: ShowcaseAppProps) : RComponent<ShowcaseAppProps, ShowcaseAppState>(props) {

    override fun RBuilder.render() {
        kotlinext.js.require("./style/custom.scss")

        CssReset

        PageLayout {

            ShowcaseTopNavigation {}

            Content {

                ShowcaseLeftSidebar {}

                Main {
                    div {
                        attrs.css {
                            margin = Margin(50.px, 50.px)
                            display = Display.flex
                            flexDirection = FlexDirection.column
                        }

                        HashRouter {
                            Routes { // use switch to render only the first matching result
                                Route {
                                    attrs.path = "/"
                                    attrs.element = createElementNullSafe {
                                        Navigate {
                                            attrs.to = "/intro"
                                        }
                                    }
                                }

                                Route {
                                    attrs.path = "/intro"
                                    attrs.element = createElement(IntroPage)
                                }

                                Route {
                                    attrs.path = "/wrappers"
                                    attrs.element = createElement(WrappersPage)
                                }

                                Route {
                                    attrs.path = "/utils"
                                    attrs.element = createElement(UtilsPage)
                                }

                                Route {
                                    attrs.element = createElement(NotFoundPage)
                                }
                            }
                        }

                        // Copyright footer
                        div {
                            attrs.css {
                                fontSize = 0.7.rem
                                fontWeight = FontWeight.lighter
                                marginTop = 30.px
                            }

                            span {
                                val dynAttrs = attrs.asDynamic()
                                dynAttrs["data-year"] = "${Date().getFullYear()}"
                            }
                        }
                    }
                }
            }
        }
    }
}
