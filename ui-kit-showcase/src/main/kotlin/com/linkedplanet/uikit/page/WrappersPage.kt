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
import com.linkedplanet.uikit.component.ShowcaseWrapperItem
import com.linkedplanet.uikit.style.ShowcaseStyles
import com.linkedplanet.uikit.util.Async
import com.linkedplanet.uikit.util.RequestUtil
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
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.TextArea
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextField
import com.linkedplanet.uikit.wrapper.atlaskit.toggle.Toggle
import com.linkedplanet.uikit.wrapper.joyride.Joyride
import com.linkedplanet.uikit.wrapper.joyride.JoyrideLocale
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltip
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltipOffset
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.await
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

external interface WrappersPageProps : Props

val WrappersPage = fc<WrappersPageProps> { _ ->

    val (overallSourceCode, setOverallSourceCode) = useState("")

    val (isLoading, setIsLoading) = useState(false)
    val (isCheckboxActive, setIsCheckboxActive) = useState(false)
    val (isModalActive, setIsModalActive) = useState(false)
    val (isPopupActive, setIsPopupActive) = useState(false)
    val (isPanelActive, setIsPanelActive) = useState(false)
    val (isJoyrideActive, setIsJoyrideActive) = useState(false)

    // Retrieve source code
    Async.complete(
        taskName = "fetch-showcase-code-${this.hashCode()}",
        taskFun = {
            RequestUtil.requestAndHandle(
                url = "./showcase-sources.txt",
                handler = {
                    it.text().await()
                }
            )
        },
        completeFun = { sourceCode ->
            setOverallSourceCode(sourceCode)
        },
        catchFun = {
            console.error("Couldn't load source code...", it)
        }
    )

    div {
        h1 {
            +"Wrappers"
        }

        ShowcaseWrapperItem {
            name = "Avatar"
            packages =
                Package("@atlaskit/avatar", "https://atlassian.design/components/avatar/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "avatar"

            // START_EXAMPLE:avatar
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
            // END_EXAMPLE:avatar

            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Button & Button-Group"
            packages =
                Package("@atlaskit/button", "https://atlassian.design/components/button/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "button"

            val example = createElement {
                // START_EXAMPLE:button
                ButtonGroup {
                    Button {
                        +"Normal button"
                        attrs.appearance = "primary"
                        attrs.onClick = {
                            console.log("Button pressed")
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
                // END_EXAMPLE:button
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Banner"
            packages =
                Package("@atlaskit/banner", "https://atlassian.design/components/banner/examples").toList()


            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "banner"

            // START_EXAMPLE:banner
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
            // END_EXAMPLE:banner

            examples = listOfNotNull(example1, example2, example3)
        }

        ShowcaseWrapperItem {
            name = "Calendar"
            packages =
                Package("@atlaskit/calendar", "https://atlassian.design/components/calendar/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "calendar"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:calendar
                    Calendar {
                        attrs.locale = "de-DE"
                        attrs.weekStartDay = 1
                    }
                    // END_EXAMPLE:calendar
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Checkbox"
            packages =
                Package("@atlaskit/checkbox", "https://atlassian.design/components/checkbox/example").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "checkbox"

            val example = createElement {
                // START_EXAMPLE:checkbox
                Checkbox {
                    attrs.isChecked = isCheckboxActive
                    attrs.onChange = {
                        setIsCheckboxActive(!isCheckboxActive)
                    }
                }
                // END_EXAMPLE:checkbox
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Code block"
            packages =
                Package("@atlaskit/code", "https://atlassian.design/components/code/code-block/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "code-block"

            val example = createElement {
                // START_EXAMPLE:code-block
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
                // END_EXAMPLE:code-block
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Date time picker"
            packages = Package(
                "@atlaskit/datetime-picker",
                "https://atlassian.design/components/datetime-picker/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "datetime-picker"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:datetime-picker
                    DateTimePicker {}
                    // END_EXAMPLE:datetime-picker
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Dropdown menu"
            packages = Package(
                "@atlaskit/dropdown-menu",
                "https://atlassian.design/components/dropdown-menu/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "dropdown-menu"

            val example = createElement {
                // START_EXAMPLE:dropdown-menu
                DropdownMenu {
                    attrs.trigger = "Dropdown"

                    DropdownItemCheckbox { +"Dropdown Checkbox Item" }
                    DropdownItemGroup {
                        attrs.title = "Group"

                        DropdownItem { +"First dropdown Item" }
                        DropdownItem { +"First dropdown Item" }
                    }
                }
                // END_EXAMPLE:dropdown-menu
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Empty state"
            packages = Package(
                "@atlaskit/empty-state",
                "https://atlassian.design/components/empty-state/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "empty-state"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:empty-state
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
                    // END_EXAMPLE:empty-state
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Flag"
            packages = Package("@atlaskit/flag", "https://atlassian.design/components/flag/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "flag"

            val example = createElement {
                // START_EXAMPLE:flag
                Flag {
                    attrs.title = "Flag"
                    attrs.icon = createElement {
                        WarningIcon {}
                    }
                    attrs.description = "Description of flag."
                }
                // END_EXAMPLE:flag
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Icon"
            packages =
                Package("@atlaskit/icon", "https://atlassian.design/components/icon/icon-explorer").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "icon"

            val example = createElement {
                // START_EXAMPLE:icon
                ArrowDownIcon {}
                BulletListIcon {}
                CheckCircleIcon {}
                LogIcon {}
                RefreshIcon {}
                SendIcon {}
                TrashIcon {
                    attrs.primaryColor = "red"
                }
                // END_EXAMPLE:icon
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Joyride"
            packages =
                Package("react-joyride", "https://docs.react-joyride.com/").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "joyride"

            val example = createElement {
                // START_EXAMPLE:joyride
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
                // END_EXAMPLE:joyride
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Lozenge"
            packages = Package(
                "@atlaskit/lozenge",
                "https://atlassian.design/components/lozenge/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "lozenge"

            // START_EXAMPLE:lozenge
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
            // END_EXAMPLE:lozenge

            examples = listOfNotNull(example1, example2, example3, example4)
        }

        ShowcaseWrapperItem {
            name = "Modal"
            packages = Package(
                "@atlaskit/modal-dialog",
                "https://atlassian.design/components/modal-dialog/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "modal"

            val example = createElement {
                // START_EXAMPLE:modal
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
                // END_EXAMPLE:modal
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Panel"
            packages = Package(
                "@atlaskit/panel",
                "https://atlaskit.atlassian.com/packages/bitbucket/panel"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "panel"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize

                        // Attention: Additional style!
                        +ShowcaseStyles.showcaseItemExamplePanel
                    }
                    // START_EXAMPLE:panel
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
                    // END_EXAMPLE:panel
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Popup"
            packages =
                Package("@atlaskit/popup", "https://atlassian.design/components/popup/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "popup"

            val example = createElement {
                // START_EXAMPLE:popup
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
                // END_EXAMPLE:popup
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Select"
            packages =
                Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "select"

            // START_EXAMPLE:select
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
            // END_EXAMPLE:select

            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Tabs"
            packages = Package("@atlaskit/tabs", "https://atlassian.design/components/tabs/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "tabs"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:tabs
                    Tabs {
                        attrs.tabs = arrayOf(
                            Tab("First tab", createElement { span { +"First" } }!!),
                            Tab("Second tab", createElement { span { +"Second" } }!!),
                        )
                    }
                    // END_EXAMPLE:tabs
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Tag & Tag-Group"
            packages = Package("@atlaskit/tag", "https://atlassian.design/components/tag/examples").toList()
                .plus(Package("@atlaskit/tag-group", "https://atlassian.design/components/tag-group/examples"))

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "tags"

            val example = createElement {
                // START_EXAMPLE:tags
                TagGroup {
                    SimpleTag {
                        attrs.text = "Simple Tag"
                    }
                    SimpleTag {
                        attrs.text = "Colored simple Tag"
                        attrs.color = "purple"
                    }
                }
                // END_EXAMPLE:tags
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Dynamic table"
            packages = Package(
                "@atlaskit/dynamic-table",
                "https://atlassian.design/components/dynamic-table/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "table"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:table
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
                    // END_EXAMPLE:table
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Text area"
            packages =
                Package("@atlaskit/textarea", "https://atlassian.design/components/textarea/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "textarea"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:textarea
                    TextArea {
                        attrs.defaultValue = "Content of text area..."
                    }
                    // END_EXAMPLE:textarea
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Text field"
            packages =
                Package("@atlaskit/textfield", "https://atlassian.design/components/textfield/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "textfield"

            val example = createElement {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // START_EXAMPLE:textfield
                    TextField {
                        attrs.defaultValue = "Content of text field..."
                    }
                    // END_EXAMPLE:textfield
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Toggle"
            packages =
                Package("@atlaskit/toggle", "https://atlassian.design/components/toggle/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "toggle"

            val example = createElement {
                // START_EXAMPLE:toggle
                Toggle {
                    attrs.isChecked = isCheckboxActive
                    attrs.onChange = {
                        setIsCheckboxActive(!isCheckboxActive)
                    }
                }
                // END_EXAMPLE:toggle
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Tooltip"
            packages =
                Package("react-tooltip", "https://github.com/wwayne/react-tooltip").toList()


            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "tooltip"

            // START_EXAMPLE:tooltip
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

            // END_EXAMPLE:tooltip

            examples = listOfNotNull(example)
        }
    }
}
