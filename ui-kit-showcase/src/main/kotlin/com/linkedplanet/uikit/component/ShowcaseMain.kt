package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.atlaskit.avatar.Avatar
import com.linkedplanet.uikit.atlaskit.avatar.AvatarItem
import com.linkedplanet.uikit.atlaskit.button.*
import com.linkedplanet.uikit.atlaskit.checkbox.Checkbox
import com.linkedplanet.uikit.atlaskit.datetimepicker.DateTimePicker
import com.linkedplanet.uikit.atlaskit.dropdownmenu.*
import com.linkedplanet.uikit.atlaskit.flag.Flag
import com.linkedplanet.uikit.atlaskit.pagelayout.Main
import com.linkedplanet.uikit.atlaskit.select.*
import com.linkedplanet.uikit.atlaskit.tab.Tab
import com.linkedplanet.uikit.atlaskit.tab.Tabs
import com.linkedplanet.uikit.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.atlaskit.taggroup.TagGroup
import com.linkedplanet.uikit.style.ShowcaseStyles
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.hr
import react.dom.html.ReactHTML.span
import styled.css
import styled.styledDiv

external interface ShowcaseMainProps : Props

val ShowcaseMain = fc<ShowcaseMainProps> { props ->

    val (isLoading, setIsLoading) = useState(false)

    Main {
        styledDiv {
            css {
                +ShowcaseStyles.showcaseItemsContainer
            }

            // ---------------------
            ShowcaseItem {
                name = "Avatar"
                docUrl = "https://atlassian.design/components/avatar/examples"
                packageName = "@atlaskit/avatar"

                val avatar = createElement {
                    Avatar {
                        attrs.size = "large"
                    }
                }

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

            // ---------------------
            hr {}
            h1 {
                +"Buttons"
            }

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
                    attrs.onClick = { _, _ ->
                        setIsLoading(true)
                        window.setTimeout({ setIsLoading(false) }, 5000)
                    }
                }
            }

            // ---------------------
            hr {}
            h1 {
                +"Checkbox"
            }

            Checkbox {
                attrs.isChecked = isLoading
            }

            // ---------------------
            hr {}
            h1 {
                +"Date time picker"
            }

            DateTimePicker {
            }

            // ---------------------
            hr {}
            h1 {
                +"Dropdown menu"
            }

            DropdownMenu {
                attrs.trigger = "Dropdown"

                DropdownItemCheckbox { +"Dropdown Checkbox Item" }
                DropdownItemGroup {
                    attrs.title = "Group"

                    DropdownItem { +"First dropdown Item" }
                    DropdownItem { +"First dropdown Item" }
                }

            }

            // ---------------------
            hr {}
            h1 {
                +"TODO!"
            }

            // ---------------------
            hr {}
            h1 {
                +"Flag"
            }

            Flag {
                attrs.title = "Flag"
                attrs.description = "Description of flag."
            }

            // ---------------------
            hr {}
            h1 {
                +"Select"
            }

            Select {
                attrs.options = arrayOf(
                    SelectOption("First option", "first"),
                    SelectOption("Second option", "second")
                )
            }
            br {}
            SelectGroup {
                attrs.options = arrayOf(
                    GroupedSelectOptions("First group", arrayOf(SelectOption("First option", "first"))),
                    GroupedSelectOptions("Second group", arrayOf(SelectOption("Second option", "second")))
                )
            }

            // ---------------------
            hr {}
            h1 {
                +"Tabs"
            }

            Tabs {
                attrs.tabs = arrayOf(
                    Tab("First tab", createElement { span { +"First" } }!!),
                    Tab("Second tab", createElement { span { +"Second" } }!!),
                )
            }

            // ---------------------
            hr {}
            h1 {
                +"Tag & Tag-Group"
            }

            TagGroup {
                SimpleTag {
                    attrs.text = "Simple Tag"
                }
            }
        }
    }
}

fun RBuilder.ShowcaseMain(handler: ShowcaseMainProps.() -> Unit) =
    child(ShowcaseMain) { attrs { handler() } }