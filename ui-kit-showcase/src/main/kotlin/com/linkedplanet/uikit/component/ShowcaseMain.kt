package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.atlaskit.avatar.Avatar
import com.linkedplanet.uikit.atlaskit.avatar.AvatarItem
import com.linkedplanet.uikit.atlaskit.button.*
import com.linkedplanet.uikit.atlaskit.checkbox.Checkbox
import com.linkedplanet.uikit.atlaskit.datetimepicker.DateTimePicker
import com.linkedplanet.uikit.atlaskit.dropdownmenu.*
import com.linkedplanet.uikit.atlaskit.flag.Flag
import com.linkedplanet.uikit.atlaskit.icon.*
import com.linkedplanet.uikit.atlaskit.pagelayout.Main
import com.linkedplanet.uikit.atlaskit.select.*
import com.linkedplanet.uikit.atlaskit.tab.Tab
import com.linkedplanet.uikit.atlaskit.tab.Tabs
import com.linkedplanet.uikit.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.atlaskit.taggroup.TagGroup
import com.linkedplanet.uikit.style.ShowcaseStyles
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.span
import styled.css
import styled.styledDiv

external interface ShowcaseMainProps : Props

val ShowcaseMain = fc<ShowcaseMainProps> { props ->

    val (isLoading, setIsLoading) = useState(false)
    val (isCheckboxActive, setIsCheckboxActive) = useState(false)

    Main {
        styledDiv {
            css {
                +ShowcaseStyles.showcaseItemsContainer
            }

            // ---------------------
            ShowcaseItem {
                name = "Avatar"
                packages = Package("@atlaskit/avatar", "https://atlassian.design/components/avatar/examples").toList()

                val avatar = createElement {
                    Avatar {
                        attrs.size = "large"
                        attrs.presence = "online"
                    }
                }!!

                val example1 = createElement {
                    AvatarItem {
                        attrs.avatar = avatar
                    }
                }

                val example2 = createElement {
                    AvatarItem {
                        attrs.avatar = avatar
                        attrs.primaryText = "Carl Coder"
                        attrs.secondaryText = "Software Engineer"
                    }
                }

                examples = listOfNotNull(example1, example2)
            }

            ShowcaseItem {
                name = "Button & Button-Group"
                packages = Package("@atlaskit/button", "https://atlassian.design/components/button/examples").toList()

                val example = createElement {
                    ButtonGroup {
                        Button {
                            +"Normal button"
                            attrs.appearance = "primary"
                            attrs.onClick = {
                                window.postMessage("TEST", "")
                            }
                        }

                        LoadingButton {
                            +"Loading button"
                            attrs.isLoading = isLoading
                            attrs.onClick = { _ ->
                                setIsLoading(true)
                                window.setTimeout({ setIsLoading(false) }, 5000)
                            }
                        }
                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Checkbox"
                packages =
                    Package("@atlaskit/checkbox", "https://atlassian.design/components/checkbox/example").toList()

                val example = createElement {
                    Checkbox {
                        attrs.isChecked = isCheckboxActive
                        attrs.onChange = {
                            setIsCheckboxActive(!isCheckboxActive)
                        }
                    }
                }

                examples = listOfNotNull(example)
            }


            ShowcaseItem {
                name = "Date time picker"
                packages = Package(
                    "@atlaskit/datetime-picker",
                    "https://atlassian.design/components/datetime-picker/examples"
                ).toList()

                val example = createElement {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseItemExampleMediumSize
                        }
                        DateTimePicker {}
                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Dropdown menu"
                packages = Package(
                    "@atlaskit/dropdown-menu",
                    "https://atlassian.design/components/dropdown-menu/examples"
                ).toList()

                val example = createElement {
                    DropdownMenu {
                        attrs.trigger = "Dropdown"

                        DropdownItemCheckbox { +"Dropdown Checkbox Item" }
                        DropdownItemGroup {
                            attrs.title = "Group"

                            DropdownItem { +"First dropdown Item" }
                            DropdownItem { +"First dropdown Item" }
                        }

                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Flag"
                packages = Package("@atlaskit/flag", "https://atlassian.design/components/flag/examples").toList()

                val warningIcon = createElement {
                    WarningIcon {}
                }

                val example = createElement {
                    Flag {
                        attrs.title = "Flag"
                        attrs.icon = warningIcon
                        attrs.description = "Description of flag."
                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Select"
                packages = Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

                val example1 = createElement {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseItemExampleMediumSize
                        }
                        Select {
                            attrs.options = arrayOf(
                                SelectOption("First option", "first"),
                                SelectOption("Second option", "second")
                            )
                        }
                    }
                }

                val example2 = createElement {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseItemExampleMediumSize
                        }
                        SelectGroup {
                            attrs.options = arrayOf(
                                GroupedSelectOptions("First group", arrayOf(SelectOption("First option", "first"))),
                                GroupedSelectOptions("Second group", arrayOf(SelectOption("Second option", "second")))
                            )
                        }
                    }
                }

                examples = listOfNotNull(example1, example2)
            }

            ShowcaseItem {
                name = "Tabs"
                packages = Package("@atlaskit/tabs", "https://atlassian.design/components/tabs/examples").toList()

                val example = createElement {
                    styledDiv {
                        css {
                            +ShowcaseStyles.showcaseItemExampleMediumSize
                        }
                        Tabs {
                            attrs.tabs = arrayOf(
                                Tab("First tab", createElement { span { +"First" } }!!),
                                Tab("Second tab", createElement { span { +"Second" } }!!),
                            )
                        }
                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Tag & Tag-Group"
                packages = Package("@atlaskit/tag", "https://atlassian.design/components/tag/examples").toList()
                    .plus(Package("@atlaskit/tag-group", "https://atlassian.design/components/tag-group/examples"))

                val example = createElement {
                    TagGroup {
                        SimpleTag {
                            attrs.text = "Simple Tag"
                        }
                        SimpleTag {
                            attrs.text = "Colored simple Tag"
                            attrs.color = "purple"
                        }
                    }
                }

                examples = listOfNotNull(example)
            }

            ShowcaseItem {
                name = "Icon"
                packages = Package("@atlaskit/icon", "https://atlassian.design/components/icon/icon-explorer").toList()

                val example = createElement {
                    ArrowDownIcon {}
                    BulletListIcon{}
                    CheckCircleIcon {}
                    LogIcon {}
                    RefreshIcon {}
                    SendIcon {}
                    TrashIcon {
                        attrs.primaryColor = "red"
                    }
                }

                examples = listOfNotNull(example)
            }
        }
    }
}

fun RBuilder.ShowcaseMain(handler: ShowcaseMainProps.() -> Unit) =
    child(ShowcaseMain) { attrs { handler() } }