package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.atlaskit.icon.*
import com.linkedplanet.uikit.atlaskit.pagelayout.LeftSidebar
import com.linkedplanet.uikit.atlaskit.sidenavigation.*
import kotlinext.js.jsObject
import kotlinx.browser.document
import react.*
import react.dom.a

external interface ShowcaseLeftSidebarProps : Props

val ShowcaseLeftSidebar = fc<ShowcaseLeftSidebarProps> { props ->

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
            }
        }
    }
}

fun RBuilder.ShowcaseLeftSidebar(handler: ShowcaseLeftSidebarProps.() -> Unit) =
    child(ShowcaseLeftSidebar) { attrs { handler() } }