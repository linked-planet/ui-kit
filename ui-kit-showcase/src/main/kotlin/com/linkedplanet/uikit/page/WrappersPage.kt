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
package com.linkedplanet.uikit.page

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.component.ShowcaseItem
import com.linkedplanet.uikit.style.ShowcaseStyles
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.Avatar
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.AvatarItem
import com.linkedplanet.uikit.wrapper.atlaskit.banner.Banner
import com.linkedplanet.uikit.wrapper.atlaskit.button.*
import com.linkedplanet.uikit.wrapper.atlaskit.calendar.Calendar
import com.linkedplanet.uikit.wrapper.atlaskit.checkbox.Checkbox
import com.linkedplanet.uikit.wrapper.atlaskit.code.CodeBlock
import com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker.DateTimePicker
import com.linkedplanet.uikit.wrapper.atlaskit.dropdownmenu.*
import com.linkedplanet.uikit.wrapper.atlaskit.emptystate.EmptyState
import com.linkedplanet.uikit.wrapper.atlaskit.flag.Flag
import com.linkedplanet.uikit.wrapper.atlaskit.icon.*
import com.linkedplanet.uikit.wrapper.atlaskit.lozenge.Lozenge
import com.linkedplanet.uikit.wrapper.atlaskit.modal.*
import com.linkedplanet.uikit.wrapper.atlaskit.panel.PanelStateless
import com.linkedplanet.uikit.wrapper.atlaskit.popup.Popup
import com.linkedplanet.uikit.wrapper.atlaskit.select.*
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tab
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tabs
import com.linkedplanet.uikit.wrapper.atlaskit.table.*
import com.linkedplanet.uikit.wrapper.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.wrapper.atlaskit.taggroup.TagGroup
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.Textarea
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.Textfield
import com.linkedplanet.uikit.wrapper.atlaskit.toggle.Toggle
import com.linkedplanet.uikit.wrapper.joyride.Joyride
import com.linkedplanet.uikit.wrapper.joyride.JoyrideLocale
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltip
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltipOffset
import kotlinext.js.jsObject
import kotlinx.browser.window
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

external interface WrappersPageProps : Props

val WrappersPage = fc<WrappersPageProps> { props ->

    val (isLoading, setIsLoading) = useState(false)
    val (isCheckboxActive, setIsCheckboxActive) = useState(false)
    val (isModalActive, setIsModalActive) = useState(false)
    val (isPopupActive, setIsPopupActive) = useState(false)
    val (isPanelActive, setIsPanelActive) = useState(false)
    val (isJoyrideActive, setIsJoyrideActive) = useState(false)

    div {
        h1 {
            +"Wrappers"
        }

        ShowcaseItem {
            name = "Avatar"
            packages =
                Package("@atlaskit/avatar", "https://atlassian.design/components/avatar/examples").toList()

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
            name = "Banner"
            packages =
                Package("@atlaskit/banner", "https://atlassian.design/components/banner/examples").toList()

            val example1 = createElement {
                Banner {
                    attrs.appearance = "announcement"
                    attrs.isOpen = true

                    span {
                        +"Content of the banner..."
                    }
                }
            }

            val example2 = createElement {
                Banner {
                    attrs.appearance = "warning"
                    attrs.icon = createElement {
                        WarningIcon {
                            attrs.secondaryColor = "var(--ds-background-warning-bold, #FFAB00)"
                        }
                    }
                    attrs.isOpen = true

                    span {
                        +"Content of the banner..."
                    }
                }
            }

            val example3 = createElement {
                Banner {
                    attrs.appearance = "error"
                    attrs.icon = createElement {
                        ErrorIcon {
                            attrs.secondaryColor = "var(--ds-background-danger-bold, #DE350B)"
                        }
                    }
                    attrs.isOpen = true

                    span {
                        +"Content of the banner..."
                    }
                }
            }

            examples = listOfNotNull(example1, example2, example3)
        }

        ShowcaseItem {
            name = "Button & Button-Group"
            packages =
                Package("@atlaskit/button", "https://atlassian.design/components/button/examples").toList()

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
            name = "Calendar"
            packages =
                Package("@atlaskit/calendar", "https://atlassian.design/components/calendar/examples").toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    Calendar {
                        attrs.locale = "de-DE"
                        attrs.weekStartDay = 1
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
            name = "Code block"
            packages =
                Package("@atlaskit/code", "https://atlassian.design/components/code/code-block/examples").toList()

            val example = createElement {
                CodeBlock {
                    attrs.language = "java"
                    attrs.text = """
                        class Hello {
                            public static void main(String args...) {
                                System.out.println("Hello World!)
                            }
                        }
                        """.trimIndent()
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
            name = "Empty state"
            packages = Package(
                "@atlaskit/empty-state",
                "https://atlassian.design/components/empty-state/examples"
            ).toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    EmptyState {
                        attrs.header = "Empty state"
                        attrs.description = createElement {
                            span {
                                +"Content of this state..."
                            }
                        }!!
                        attrs.primaryAction = createElement {
                            Button {
                                +"Dummy button"
                            }
                        }!!
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
            name = "Icon"
            packages =
                Package("@atlaskit/icon", "https://atlassian.design/components/icon/icon-explorer").toList()

            val example = createElement {
                ArrowDownIcon {}
                BulletListIcon {}
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

        ShowcaseItem {
            name = "Joyride"
            packages =
                Package("react-joyride", "https://docs.react-joyride.com/").toList()

            val example = createElement {
                ButtonGroup {
                    Button {
                        attrs.isSelected = isJoyrideActive
                        attrs.onClick = {
                            setIsJoyrideActive(true)
                        }
                        +"Start Tour"
                    }

                    Button {
                        attrs.className = "joyride-first"
                        +"First step"
                    }

                    Button {
                        attrs.className = "joyride-second"
                        +"Second step"
                    }

                    Button {
                        attrs.className = "joyride-third"
                        +"Third step"
                    }
                }

                Joyride {
                    attrs.run = isJoyrideActive
                    attrs.continuous = true
                    attrs.showProgress = true
                    attrs.disableScrolling = true
                    attrs.scrollToFirstStep = false
                    attrs.scrollOffset = 220
                    attrs.locale = JoyrideLocale(
                        "Zurück",
                        "Schließen",
                        "Fertig",
                        "Weiter",
                        "Öffnen",
                        "Überspringen"
                    )
                    attrs.callback = { joyrideState ->
                        when (joyrideState.action) {
                            "close", "reset" -> {
                                setIsJoyrideActive(false)
                            }
                        }
                    }
                    attrs.steps = arrayOf(
                        jsObject {
                            title = "First step title"
                            target = ".joyride-first"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElement {
                                span {
                                    +"First step content..."
                                }
                            }!!
                        },
                        jsObject {
                            title = "Second step title"
                            target = ".joyride-second"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElement {
                                span {
                                    +"Second step content..."
                                }
                            }!!
                        },
                        jsObject {
                            title = "Third step title"
                            target = ".joyride-third"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElement {
                                span {
                                    +"Third step content..."
                                }
                            }!!
                        }
                    )
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Lozenge"
            packages = Package(
                "@atlaskit/lozenge",
                "https://atlassian.design/components/lozenge/examples"
            ).toList()

            val example1 = createElement {
                Lozenge {
                    +"First lozenge"
                }
            }

            val example2 = createElement {
                Lozenge {
                    +"Colored lozenge"
                    attrs.appearance = "new"
                }
            }

            val example3 = createElement {
                Lozenge {
                    +"Colored bold lozenge"
                    attrs.appearance = "success"
                    attrs.isBold = true
                }
            }

            val example4 = createElement {
                Lozenge {
                    +"Colored non-bold lozenge"
                    attrs.appearance = "success"
                    attrs.isBold = false
                }
            }

            examples = listOfNotNull(example1, example2, example3, example4)
        }

        ShowcaseItem {
            name = "Modal"
            packages = Package(
                "@atlaskit/modal-dialog",
                "https://atlassian.design/components/modal-dialog/examples"
            ).toList()

            val example = createElement {
                Button {
                    +"Show modal"

                    attrs.onClick = {
                        setIsModalActive(true)
                    }
                }

                if (isModalActive) {
                    ModalTransition {
                        Modal {
                            attrs.onClose = {
                                setIsModalActive(false)
                            }

                            ModalHeader {
                                ModalTitle {
                                    +"Sample Modal"
                                }
                                Button {
                                    attrs.appearance = "link"
                                    attrs.onClick = {
                                        setIsModalActive(false)
                                    }

                                    CrossIcon {
                                        attrs.label = "Close popup"
                                        attrs.primaryColor = "#000"
                                    }
                                }
                            }
                            ModalFooter {
                                ButtonGroup {
                                    Button {
                                        // If not autofocused, the other button is focused with a border!
                                        attrs.autoFocus = true
                                        attrs.appearance = "primary"
                                        attrs.onClick = {
                                            setIsModalActive(false)
                                        }
                                        +"Close"
                                    }
                                }
                            }
                        }
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Panel"
            packages = Package(
                "@atlaskit/panel",
                "https://atlaskit.atlassian.com/packages/bitbucket/panel"
            ).toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize

                        // Attention: Additional style!
                        +ShowcaseStyles.showcaseItemExamplePanel
                    }
                    PanelStateless {
                        attrs.isExpanded = isPanelActive
                        attrs.onChange = {
                            setIsPanelActive(!isPanelActive)
                        }
                        attrs.header = createElement {
                            span {
                                +"Panel"
                            }
                        }!!

                        span {
                            +"Panel content..."
                        }
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Popup"
            packages =
                Package("@atlaskit/popup", "https://atlassian.design/components/popup/examples").toList()

            val example = createElement {
                Popup {
                    attrs.isOpen = isPopupActive
                    attrs.placement = "top"
                    attrs.onClose = {
                        setIsPopupActive(false)
                    }
                    attrs.content = {
                        createElement {
                            styledDiv {
                                css {
                                    +ShowcaseStyles.showcaseItemExamplePopup
                                }
                                span {
                                    +"Popup content"
                                }
                            }
                        }!!
                    }
                    attrs.trigger = {
                        createElement {
                            Button {
                                +"Open popup"
                                attrs.onClick = {
                                    setIsPopupActive(true)
                                }
                                attrs.isSelected = isPopupActive
                            }
                        }!!
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Select"
            packages =
                Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

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
                            GroupedSelectOptions(
                                "Second group",
                                arrayOf(SelectOption("Second option", "second"))
                            )
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
            name = "Dynamic table"
            packages = Package(
                "@atlaskit/dynamic-table",
                "https://atlassian.design/components/dynamic-table/examples"
            ).toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    DynamicTable {
                        attrs.caption = createElement { +"" }!!
                        attrs.head = DynamicTableHead(
                            arrayOf(
                                HeaderCell("first", "First col", isSortable = true),
                                HeaderCell("second", "Second col", isSortable = true),
                                HeaderCell("third", "Action col")
                            )
                        )
                        attrs.rowsPerPage = 3
                        attrs.rows = arrayOf(
                            DynamicTableRow(
                                "1stRow",
                                arrayOf(
                                    StringRowCell("1-1"),
                                    StringRowCell("1-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            ),
                            DynamicTableRow(
                                "2ndRow",
                                arrayOf(
                                    StringRowCell("2-1"),
                                    StringRowCell("2-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            ),
                            DynamicTableRow(
                                "3rdRow",
                                arrayOf(
                                    StringRowCell("3-1"),
                                    StringRowCell("3-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            ),
                            DynamicTableRow(
                                "4thRow",
                                arrayOf(
                                    StringRowCell("4-1"),
                                    StringRowCell("4-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            ),
                            DynamicTableRow(
                                "5thRow",
                                arrayOf(
                                    StringRowCell("5-1"),
                                    StringRowCell("5-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            ),
                            DynamicTableRow(
                                "6thRow",
                                arrayOf(
                                    StringRowCell("6-1"),
                                    StringRowCell("6-2"),
                                    ElementRowCell(createElement {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    }!!)
                                )
                            )
                        )
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Text area"
            packages =
                Package("@atlaskit/textarea", "https://atlassian.design/components/textarea/examples").toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    Textarea {
                        attrs.defaultValue = "Content of text area..."
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Text field"
            packages =
                Package("@atlaskit/textfield", "https://atlassian.design/components/textfield/examples").toList()

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    Textfield {
                        attrs.defaultValue = "Content of text field..."
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Toggle"
            packages =
                Package("@atlaskit/toggle", "https://atlassian.design/components/toggle/examples").toList()

            val example = createElement {
                Toggle {
                    attrs.isChecked = isCheckboxActive
                    attrs.onChange = {
                        setIsCheckboxActive(!isCheckboxActive)
                    }
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseItem {
            name = "Tooltip"
            packages =
                Package("react-tooltip", "https://github.com/wwayne/react-tooltip").toList()

            val key = "tooltip-1"

            val example = createElement {
                div {
                    attrs["data-tip"] = "true"
                    attrs["data-for"] = key
                    SearchIcon {}
                }

                ReactTooltip {
                    attrs.id = key
                    attrs.className = "tooltip"
                    attrs.place = "right"
                    attrs.effect = "solid"
                    attrs.type = "light"
                    attrs.border = true
                    attrs.borderColor = "rgb(222, 225, 231)"
                    attrs.offset = ReactTooltipOffset(0, 0, -5, 0)

                    span {
                        +"I'm a tooltip..."
                    }
                }
            }

            examples = listOfNotNull(example)
        }
    }
}
