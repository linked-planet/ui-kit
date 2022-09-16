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
import com.linkedplanet.uikit.extension.form.*
import com.linkedplanet.uikit.style.ShowcaseStyles
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.Avatar
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.AvatarItem
import com.linkedplanet.uikit.wrapper.atlaskit.badge.Badge
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
import com.linkedplanet.uikit.wrapper.atlaskit.pagination.Pagination
import com.linkedplanet.uikit.wrapper.atlaskit.panel.PanelStateless
import com.linkedplanet.uikit.wrapper.atlaskit.popup.Popup
import com.linkedplanet.uikit.wrapper.atlaskit.select.*
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tab
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tabs
import com.linkedplanet.uikit.wrapper.atlaskit.table.*
import com.linkedplanet.uikit.wrapper.atlaskit.tabletree.*
import com.linkedplanet.uikit.wrapper.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.wrapper.atlaskit.taggroup.TagGroup
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.TextArea
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextField
import com.linkedplanet.uikit.wrapper.atlaskit.toggle.Toggle
import com.linkedplanet.uikit.wrapper.joyride.Joyride
import com.linkedplanet.uikit.wrapper.joyride.JoyrideLocale
import com.linkedplanet.uikit.wrapper.lpeditor.LPEditor
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltip
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltipOffset
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.js.jso
import react.*
import react.dom.div
import react.dom.h1
import react.dom.html.ReactHTML.p
import react.dom.onClick
import react.dom.span
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
    val (formData, setFormData) = useState("")
    val (selectedPage, setSelectedPage) = useState(0)
    val (editorString, setEditorString) = useState("<h1>Hello \$object.Person</h1>")
    val (objectString, setObjectString) = useState("""{ "object": { "Person": { "First Name" : [{"k1":"v1"},{"k2":"v2"}], "Last Name": "2ndValue", "Age": 30, "VIP": true } } }""")
    val (formSelectOptions, setFormSelectOptions) = useState(
        listOf(
            SelectOption("Red", "red"),
            SelectOption("Blue", "blue")
        )
    )


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

            // region:avatar
            val avatar = createElementNullSafe {
                Avatar {
                    attrs.size = "large"
                    attrs.presence = "online"
                }
            }

            val example1 = createElementNullSafe {
                AvatarItem {
                    attrs.avatar = avatar
                }
            }

            val example2 = createElementNullSafe {
                AvatarItem {
                    attrs.avatar = avatar
                    attrs.primaryText = "Carl Coder"
                    attrs.secondaryText = "Software Engineer"
                }
            }
            // endregion:avatar

            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Badge"
            packages =
                Package("@atlaskit/badge", "https://atlassian.design/components/badge/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "badge"

            val badge = createElementNullSafe {
                // region:badge
                Badge {}
                Badge {
                    attrs.appearance = "added"
                }
                Badge {
                    attrs.appearance = "important"
                }
                // endregion:badge
            }

            examples = listOfNotNull(badge)
        }

        ShowcaseWrapperItem {
            name = "Button & Button-Group"
            packages =
                Package("@atlaskit/button", "https://atlassian.design/components/button/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "button"

            val example = createElementNullSafe {
                // region:button
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
                // endregion:button
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Banner"
            packages =
                Package("@atlaskit/banner", "https://atlassian.design/components/banner/examples").toList()


            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "banner"

            // region:banner
            val example1 = createElementNullSafe {
                Banner {
                    attrs.appearance = "announcement"
                    attrs.isOpen = true

                    span {
                        +"Content of the banner..."
                    }
                }
            }

            val example2 = createElementNullSafe {
                Banner {
                    attrs.appearance = "warning"
                    attrs.icon = createElementNullSafe {
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

            val example3 = createElementNullSafe {
                Banner {
                    attrs.appearance = "error"
                    attrs.icon = createElementNullSafe {
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
            // endregion:banner

            examples = listOfNotNull(example1, example2, example3)
        }

        ShowcaseWrapperItem {
            name = "Calendar"
            packages =
                Package("@atlaskit/calendar", "https://atlassian.design/components/calendar/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "calendar"

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:calendar
                    Calendar {
                        attrs.locale = "de-DE"
                        attrs.weekStartDay = 1
                    }
                    // endregion:calendar
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

            val example = createElementNullSafe {
                // region:checkbox
                Checkbox {
                    attrs.isChecked = isCheckboxActive
                    attrs.onChange = {
                        setIsCheckboxActive(!isCheckboxActive)
                    }
                }
                // endregion:checkbox
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Code block"
            packages =
                Package("@atlaskit/code", "https://atlassian.design/components/code/code-block/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "code-block"

            val example = createElementNullSafe {
                // region:code-block
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
                // endregion:code-block
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

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:datetime-picker
                    DateTimePicker {}
                    // endregion:datetime-picker
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

            val example = createElementNullSafe {
                // region:dropdown-menu
                DropdownMenu {
                    attrs.trigger = "Dropdown"

                    DropdownItemCheckbox { +"Dropdown Checkbox Item" }
                    DropdownItemGroup {
                        attrs.title = "Group"

                        DropdownItem { +"First dropdown Item" }
                        DropdownItem { +"First dropdown Item" }
                    }
                }
                // endregion:dropdown-menu
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

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:empty-state
                    EmptyState {
                        attrs.header = "Empty state"
                        attrs.description = createElementNullSafe {
                            span {
                                +"Content of this state..."
                            }
                        }
                        attrs.primaryAction = createElementNullSafe {
                            Button {
                                +"Dummy button"
                            }
                        }
                    }
                    // endregion:empty-state
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Flag"
            packages = Package("@atlaskit/flag", "https://atlassian.design/components/flag/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "flag"

            val example = createElementNullSafe {
                // region:flag
                Flag {
                    attrs.title = "Flag"
                    attrs.icon = createElementNullSafe {
                        WarningIcon {}
                    }
                    attrs.description = "Description of flag."
                }
                // endregion:flag
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Form"
            packages = Package("@atlaskit/form", "https://atlassian.design/components/form/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "form"

            val example = createElementNullSafe {
                // region:form
                data class FormData(
                    val name: String,
                    val surname: String,
                    val coder: Boolean,
                    val reactFan: Boolean,
                    val color: SelectOption,
                    val creatableColor: SelectOption
                )

                ExtendedForm<FormData>(
                    onSubmit = { values, _, _ ->
                        console.info("Value of name after form submission: ${values.name}")
                        setFormData(JSON.stringify(values))
                    }
                ) {
                    ExtendedFormHeader("Give me your input", "I describe this form.")

                    ExtendedFormSection("Your data", "I'm curious.") {
                        fun validateString(value: dynamic, form: dynamic, fieldState: dynamic): String? {
                            val strValue = value as String
                            return if (strValue.isEmpty()) {
                                "empty"
                            } else if (strValue.length > 10) {
                                "tooLong"
                            } else {
                                null
                            }
                        }

                        val stringValidationMapping = ValidationMapping(
                            "Help!",
                            "Valid - good job!",
                            listOf(
                                ValidationMappingEntry("tooLong", "Too long!"),
                                ValidationMappingEntry("empty", "Fill me!")
                            )
                        )

                        ExtendedFormTextField(
                            name = "name",
                            label = "Name",
                            defaultValue = "Carl",
                            validationMapping = stringValidationMapping,
                            validate = ::validateString,
                            onChange = { console.info("Value of name field: $it") }
                        )


                        ExtendedFormTextField(
                            name = "readOnlyField",
                            label = "Read-only Field",
                            defaultValue = "Read-only Field",
                            isReadOnly = true
                        )

                        ExtendedFormTextField(
                            name = "disabledField",
                            label = "Disabled Field",
                            defaultValue = "Disabled Field",
                            isDisabled = true
                        )

                        ExtendedFormTextField(
                            name = "surname",
                            label = "Surname",
                            defaultValue = "Coderrrr",
                            isRequired = true,
                            validationMapping = stringValidationMapping,
                            validate = ::validateString
                        )

                        ExtendedFieldset("Some more info") {
                            ExtendedFormCheckboxField(
                                name = "coder",
                                label = "Coder",
                                defaultIsChecked = true,
                                onChange = { console.info("Value of coder: $it") }
                            )

                            ExtendedFormCheckboxField(
                                name = "reactFan",
                                label = "React fan"
                            )
                        }
                    }

                    ExtendedFormSection("More data", "Tell me more.") {
                        ExtendedFormSelectField(
                            name = "color",
                            label = "Favorite Color",
                            options = formSelectOptions.toTypedArray(),
                            defaultValue = formSelectOptions[1],
                            onChange = { console.info("Value of color: ${it.label}") }
                        )

                        ExtendedFormCreatableSelectField(
                            name = "colorCreatable",
                            label = "Favorite Color (Creatable)",
                            options = formSelectOptions.toTypedArray(),
                            defaultValue = formSelectOptions[1],
                            onCreate = {
                                setFormSelectOptions(
                                    formSelectOptions.plus(
                                        SelectOption(
                                            it,
                                            it.lowercase()
                                        )
                                    )
                                )
                            },
                            onChange = { console.info("Value of colorCreatable: ${it.label}") }
                        )
                    }

                    ExtendedFormFooter {
                        ButtonGroup {
                            LoadingButton {
                                attrs.type = "submit"
                                attrs.appearance = "primary"
                                +"Speichern"
                            }
                        }
                    }
                }

                if (formData.isNotEmpty()) {
                    span { +"Transmitted form data:" }
                    CodeBlock {
                        attrs.language = "json"
                        attrs.text = formData
                    }
                }
                // endregion:form
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Icon"
            packages =
                Package("@atlaskit/icon", "https://atlassian.design/components/icon/icon-explorer").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "icon"

            val example = createElementNullSafe {
                // region:icon
                ArrowDownIcon {}
                BulletListIcon {}
                CheckCircleIcon {}
                LogIcon {}
                RefreshIcon {}
                SendIcon {}
                TrashIcon {
                    attrs.primaryColor = "red"
                }
                // endregion:icon
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Joyride"
            packages =
                Package("react-joyride", "https://docs.react-joyride.com/").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "joyride"

            val example = createElementNullSafe {
                // region:joyride
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
                        jso {
                            title = "First step title"
                            target = ".joyride-first"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElementNullSafe {
                                span {
                                    +"First step content..."
                                }
                            }
                        },
                        jso {
                            title = "Second step title"
                            target = ".joyride-second"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElementNullSafe {
                                span {
                                    +"Second step content..."
                                }
                            }
                        },
                        jso {
                            title = "Third step title"
                            target = ".joyride-third"
                            disableBeacon = true
                            showSkipButton = true
                            content = createElementNullSafe {
                                span {
                                    +"Third step content..."
                                }
                            }
                        }
                    )
                }
                // endregion:joyride
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

            // region:lozenge
            val example1 = createElementNullSafe {
                Lozenge {
                    +"First lozenge"
                }
            }

            val example2 = createElementNullSafe {
                Lozenge {
                    +"Colored lozenge"
                    attrs.appearance = "new"
                }
            }

            val example3 = createElementNullSafe {
                Lozenge {
                    +"Colored bold lozenge"
                    attrs.appearance = "success"
                    attrs.isBold = true
                }
            }

            val example4 = createElementNullSafe {
                Lozenge {
                    +"Colored non-bold lozenge"
                    attrs.appearance = "success"
                    attrs.isBold = false
                }
            }
            // endregion:lozenge

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

            val example = createElementNullSafe {
                // region:modal
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

                            ModalBody {
                                p {
                                    +"This is the body of the modal."
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
                // endregion:modal
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Pagination"
            packages = Package(
                "@atlaskit/pagination",
                "https://atlassian.design/components/pagination/examples"
            ).toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "pagination"

            val example = createElementNullSafe {
                // region:pagination
                Pagination {
                    attrs.pages = (1..10).toList().toTypedArray()
                    attrs.defaultSelectedIndex = 0
                    attrs.max = 10
                    attrs.selectedIndex = selectedPage
                    attrs.onChange = { _, pageLabel, _ ->
                        setSelectedPage(pageLabel - 1)
                    }
                }
                // endregion:pagination
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

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize

                        // Attention: Additional style!
                        +ShowcaseStyles.showcaseItemExamplePanel
                    }
                    // region:panel
                    PanelStateless {
                        attrs.isExpanded = isPanelActive
                        attrs.onChange = {
                            setIsPanelActive(!isPanelActive)
                        }
                        attrs.header = createElementNullSafe {
                            span {
                                +"Panel"
                            }
                        }

                        span {
                            +"Panel content..."
                        }
                    }
                    // endregion:panel
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

            val example = createElementNullSafe {
                // region:popup
                Popup {
                    attrs.isOpen = isPopupActive
                    attrs.placement = "top"
                    attrs.onClose = {
                        setIsPopupActive(false)
                    }
                    attrs.content = {
                        createElementNullSafe {
                            styledDiv {
                                css {
                                    +ShowcaseStyles.showcaseItemExamplePopup
                                }
                                span {
                                    +"Popup content"
                                }
                            }
                        }
                    }
                    attrs.trigger = {
                        createElementNullSafe {
                            Button {
                                +"Open popup"
                                attrs.onClick = {
                                    setIsPopupActive(true)
                                }
                                attrs.isSelected = isPopupActive
                            }
                        }
                    }
                }
                // endregion:popup
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Select"
            packages =
                Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "select"

            // region:select
            val example1 = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    Select {
                        attrs.inputId = "select-1"
                        attrs.options = arrayOf(
                            SelectOption("First option", "first"),
                            SelectOption("Second option", "second")
                        )
                    }
                }
            }

            val example2 = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    SelectGroup {
                        attrs.inputId = "select-2"
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
            // endregion:select

            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Tabs"
            packages = Package("@atlaskit/tabs", "https://atlassian.design/components/tabs/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "tabs"

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:tabs
                    Tabs {
                        attrs.tabs = arrayOf(
                            Tab("First tab", createSpan("First")),
                            Tab("Second tab", createSpan("Second")),
                        )
                    }
                    // endregion:tabs
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

            val example = createElementNullSafe {
                // region:tags
                TagGroup {
                    SimpleTag {
                        attrs.text = "Simple Tag"
                    }
                    SimpleTag {
                        attrs.text = "Colored simple Tag"
                        attrs.color = "purple"
                    }
                }
                // endregion:tags
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

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:table
                    DynamicTable {
                        attrs.caption = createElementNullSafe { +"" }
                        attrs.head = DynamicTableHead(
                            arrayOf(
                                StringHeaderCell("first", "First col", isSortable = true),
                                StringHeaderCell("second", "Second col", isSortable = true),
                                ElementHeaderCell("third", createElementNullSafe { span { +"Action col" } })
                            )
                        )
                        attrs.rowsPerPage = 3
                        attrs.rows = arrayOf(
                            DynamicTableRow(
                                "1stRow",
                                arrayOf(
                                    StringRowCell("1-1"),
                                    StringRowCell("1-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            ),
                            DynamicTableRow(
                                "2ndRow",
                                arrayOf(
                                    StringRowCell("2-1"),
                                    StringRowCell("2-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            ),
                            DynamicTableRow(
                                "3rdRow",
                                arrayOf(
                                    StringRowCell("3-1"),
                                    StringRowCell("3-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            ),
                            DynamicTableRow(
                                "4thRow",
                                arrayOf(
                                    StringRowCell("4-1"),
                                    StringRowCell("4-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            ),
                            DynamicTableRow(
                                "5thRow",
                                arrayOf(
                                    StringRowCell("5-1"),
                                    StringRowCell("5-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            ),
                            DynamicTableRow(
                                "6thRow",
                                arrayOf(
                                    StringRowCell("6-1"),
                                    StringRowCell("6-2"),
                                    ElementRowCell(createElementNullSafe {
                                        Button {
                                            +"Delete"
                                            attrs.appearance = "primary"
                                        }
                                    })
                                )
                            )
                        )
                        attrs.onSort = {
                            console.log(it)
                        }
                        attrs.onSetPage = {
                            console.log(it)
                        }
                    }
                    // endregion:table
                }
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "TableTree"
            packages = Package("@atlaskit/table-tree", "https://atlassian.design/components/table-tree").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "table_tree"

            @Suppress("NonExternalClassifierExtendingStateOrProps")
            // region:table_tree
            data class BookData(val title: String, val numbering: String) : Props
            data class BookTreeItem(
                override val id: String,
                override val content: BookData,
                override val children: Array<TableTreeItem>? = null,
                override val hasChildren: Boolean = children?.isNotEmpty() ?: false,
            ) : TableTreeBasicUseCaseItem<BookData>

            val bookDataTree = arrayOf(
                BookTreeItem(
                    "1", BookData("It is lonely at the top.", "1"), children = arrayOf(
                        BookTreeItem(
                            "1.1", BookData("Look at me! I am nested.", "1.1"), children = arrayOf(
                                BookTreeItem("1.1.1", BookData("I am deeply nested.", "1.1.1"))
                            )
                        )
                    )
                ),
                BookTreeItem("2", BookData("Kotlin is  fun", "2"))
            )

            val example1 = createElementNullSafe {
                val fcTitle = fc<BookData> { myprops -> span { +myprops.title } }
                val fcNumbering = fc<BookData> { myprops -> span { +myprops.numbering } }

                TableTree {
                    attrs.headers = arrayOf("Title", "Numbering")
                    attrs.columns = arrayOf(fcTitle, fcNumbering)
                    attrs.columnWidths = arrayOf(300, 100)

                    attrs.items = bookDataTree
                }
            }

            val example2 = createElementNullSafe {
                TableTree {
                    TTHeaders {
                        TTHeader {
                            attrs.width = "400px"
                            attrs.onClick = { _ ->
                                window.alert("OnClick Chapter Title Header")
                            }
                            +"Chapter Title (Click me)"
                        }
                        TTHeader {
                            attrs.width = "100px"
                            +"Numbering"
                        }
                    }
                    TTRows {
                        attrs.items = bookDataTree
                        attrs.render = { data: BookTreeItem ->
                            createElementNullSafe {
                                TTRow {
                                    attrs.itemId = data.content.numbering
                                    attrs.items = data.children
                                    attrs.hasChildren = data.hasChildren
                                    attrs.isDefaultExpanded = false

                                    TTCell {
                                        attrs.singleLine = true
                                        div {
                                            attrs.onClick = { _ -> window.alert("onClick:" + data.content.title) }
                                            +data.content.title
                                        }
                                    }
                                    TTCell {
                                        attrs.singleLine = true
                                        attrs.onClick = { _ -> window.alert("onClick:" + data.content.numbering) }
                                        +data.content.numbering
                                    }
                                }
                            }
                        }
                    }
                } // of TableTree
            }
            // endregion:table_tree
            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Text area"
            packages =
                Package("@atlaskit/textarea", "https://atlassian.design/components/textarea/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "textarea"

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    // region:textarea
                    TextArea {
                        attrs.defaultValue = "Content of text area..."
                    }
                    // endregion:textarea
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

            // region:textfield
            val example1 = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    TextField {
                        attrs.defaultValue = "Content of text field..."
                    }
                }
            }

            val example2 = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleMediumSize
                    }
                    TextField {
                        attrs.defaultValue = "Password"
                        attrs.type = "password"
                    }
                }
            }
            // endregion:textfield

            examples = listOfNotNull(example1, example2)
        }

        ShowcaseWrapperItem {
            name = "Toggle"
            packages =
                Package("@atlaskit/toggle", "https://atlassian.design/components/toggle/examples").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "toggle"

            val example = createElementNullSafe {
                // region:toggle
                Toggle {
                    attrs.isChecked = isCheckboxActive
                    attrs.onChange = {
                        setIsCheckboxActive(!isCheckboxActive)
                    }
                }
                // endregion:toggle
            }

            examples = listOfNotNull(example)
        }

        ShowcaseWrapperItem {
            name = "Tooltip"
            packages =
                Package("react-tooltip", "https://github.com/wwayne/react-tooltip").toList()


            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "tooltip"

            // region:tooltip
            val key = "tooltip-1"
            val example = createElementNullSafe {
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
            // endregion:tooltip
        }

        ShowcaseWrapperItem {
            name = "LPEditor"
            packages =
                Package("@monaco-editor/react", "https://github.com/suren-atoyan/monaco-react").toList()

            this.overallSourceCode = overallSourceCode
            sourceCodeExampleId = "lpeditor"

            val example = createElementNullSafe {
                styledDiv {
                    css {
                        +ShowcaseStyles.showcaseItemExampleLargeSize
                        +ShowcaseStyles.showcaseItemExampleMediumHeight
                    }
                    // region:lpeditor
                    LPEditor {
                        attrs.objectString = objectString
                        attrs.onChange = { value, event ->
                            setEditorString(value)
                            console.info("IntroPage OnChange for LPEditor was called:", value)
                        }
                        attrs.defaultLanguage = "html"
                        attrs.value = editorString
                        attrs.height = "300px"
                    }
                    // endregion:lpeditor
                }
            }

            examples = listOfNotNull(example)
        }
    }
}
