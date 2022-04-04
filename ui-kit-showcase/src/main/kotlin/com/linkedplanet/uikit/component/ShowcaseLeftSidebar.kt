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

import com.linkedplanet.uikit.wrapper.atlaskit.icon.EmojiTravelIcon
import com.linkedplanet.uikit.wrapper.atlaskit.icon.FileIcon
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.LeftSidebar
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.*
import kotlinext.js.jsObject
import kotlinx.browser.document
import react.*
import react.dom.a
import kotlin.js.Date

external interface ShowcaseLeftSidebarProps : Props

val ShowcaseLeftSidebar = fc<ShowcaseLeftSidebarProps> { _ ->

    LeftSidebar {

        SideNavigation {

            NavigationHeader {
                Header {
                    +document.title.uppercase()
                    attrs.description = "linked-planet"
                }
            }

            NestableNavigationContent {
                Section {
                    ButtonItem {
                        +"Menu item"
                        attrs.iconBefore = createElement(EmojiTravelIcon, jsObject {})
                        attrs.onClick = {
                            console.log("My Flights clicked")
                        }
                    }
                    NestingItem {
                        attrs.id = "nesting-item-id"
                        attrs.title = "Nested menu item"
                        attrs.iconBefore = createElement(FileIcon, jsObject {})
                        Section {
                            attrs.title = "Section 1"
                            ButtonItem {
                                +"Do something"
                            }
                        }
                        Section {
                            attrs.title = "Section 2"
                            ButtonItem {
                                +"Do something else"
                            }
                        }
                    }
                }
            }

            NavigationFooter {
                Footer {
                    +"Made with ❤ by "
                    a(href = "https://www.linked-planet.com/") {
                        +"linked-planet"
                    }
                }
                Footer {
                    +"Licensed under "
                    a(href = "http://www.apache.org/licenses/LICENSE-2.0") {
                        +"Apache License, Version 2.0"
                    }
                }
                Footer {
                    +"Copyright ${Date().getFullYear()} "
                    a(href = "https://www.linked-planet.com/") {
                        +"linked-planet GmbH"
                    }
                }
            }
        }
    }
}

fun RBuilder.ShowcaseLeftSidebar(handler: ShowcaseLeftSidebarProps.() -> Unit) =
    child(ShowcaseLeftSidebar) { attrs { handler() } }