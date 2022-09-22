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

import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.icon.EmojiTravelIcon
import com.linkedplanet.uikit.wrapper.atlaskit.icon.FileIcon
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.LeftSidebar
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.*
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextField
import csstype.FlexDirection
import kotlinx.browser.document
import kotlinx.js.jso
import nesting
import nestingFC
import org.w3c.dom.HTMLInputElement
import overrideBackButton
import react.*
import react.dom.a
import react.dom.html.ReactHTML.div

external interface ShowcaseLeftSidebarProps : Props

val nestingTextFieldWithClearButtonCustomComponent = nestingFC<Props> {
    var data by useState("")

    div {
        style = jso {
            display = csstype.Display.flex
            flexDirection = FlexDirection.row
        }
        TextField {
            placeholder = "Custom Nesting TextField"
            value = data
            isCompact = true
            type = "text"
            onChange ={
                data = (it.target as HTMLInputElement).value.trim()
            }
        }
        Button {
            onClick = {
                data = ""
            }
            + "Clear ${data.take(10)}"
        }
    }
}

val nestingTextField = TextField.nesting()


val ShowcaseLeftSidebar = fc<ShowcaseLeftSidebarProps> {

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
                        attrs.iconBefore = createElement(EmojiTravelIcon, jso {})
                        attrs.onClick = {
                            console.log("My Flights clicked")
                        }
                    }

                    NestingItem {
                        attrs.id = "nesting-item-id"
                        attrs.title = "Nested menu item"
                        attrs.iconBefore = createElement(FileIcon, jso {})
                        attrs.overrideBackButton { props ->
                                props.description = "BackDescription!"
                                +"Custom GoBackItem"
                        }

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
                            NestingItem {
                                attrs.id = "nesting-item-id-2"
                                attrs.title = "Go Deeper"

                                Section {
                                    attrs.title = "Section 2.1"
                                    ButtonItem {
                                        +"The end!"
                                    }
                                }
                            }
                        }

                        Section {
                            attrs.title = "Custom Component Section"
                            nestingTextField {
                                attrs.placeholder = "Nesting Textfield"
                                attrs.isCompact = true
                            }
                            TextField {
                                attrs.placeholder = "Sticks if Going Deeper"
                                attrs.isCompact = true
                            }
                            nestingTextFieldWithClearButtonCustomComponent {
                            }
                        }

                        Section {
                            attrs.title = "Link Section"
                            CustomItem {
                                attrs.id = "custom-item-id"
                                attrs.component = fc<Props> {
                                    react.dom.html.ReactHTML.a {
                                        attrs.href = "/"
                                        +"Link to Home"
                                    }
                                }
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
            }
        }
    }
}

fun RBuilder.ShowcaseLeftSidebar(handler: ShowcaseLeftSidebarProps.() -> Unit) =
    child(ShowcaseLeftSidebar) { attrs { handler() } }
