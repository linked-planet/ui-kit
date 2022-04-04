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
import com.linkedplanet.uikit.style.ShowcaseStyles
import com.linkedplanet.uikit.wrapper.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.*
import react.*
import react.router.dom.*
import styled.css
import styled.styledDiv

external interface ShowcaseProps : Props {
    var name: String
}

interface ShowcaseAppState : State

class ShowcaseApp(props: ShowcaseProps) : RComponent<ShowcaseProps, ShowcaseAppState>(props) {

    override fun RBuilder.render() {
        CssReset

        PageLayout {

            ShowcaseTopNavigation {}

            Content {

                ShowcaseLeftSidebar {}


                Main {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseContainer
                        }
                        HashRouter {
                            Switch { // use switch to render only the first matching result
                                Route {
                                    attrs.path = arrayOf("/intro")
                                    attrs.exact = true
                                    attrs.component = IntroPage
                                }

                                Route {
                                    attrs.path = arrayOf("/wrappers")
                                    attrs.exact = true
                                    attrs.component = WrappersPage
                                }

                                Route {
                                    attrs.path = arrayOf("/utils")
                                    attrs.exact = true
                                    attrs.component = UtilsPage
                                }

                                Route {
                                    attrs.component = NotFoundPage
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
