package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.extension.form.*
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.ButtonGroup
import com.linkedplanet.uikit.wrapper.atlaskit.button.LoadingButton
import com.linkedplanet.uikit.wrapper.atlaskit.code.CodeBlock
import com.linkedplanet.uikit.wrapper.atlaskit.select.SelectOption
import react.dom.html.ReactHTML.span
import react.fc
import react.useState

val FormShowcase = fc<ShowcaseProps> { props ->
    val (formData, setFormData) = useState("")
    val (formSelectOptions, setFormSelectOptions) = useState(
        listOf(
            SelectOption("Red", "red"),
            SelectOption("Blue", "blue")
        )
    )

    ShowcaseWrapperItem {
        name = "Form"
        packages = Package("@atlaskit/form", "https://atlassian.design/components/form/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "form"

        val example = createElementNullSafe {
            // region:form
            data class FormData(
                val name: String,
                val surname: String,
                val coder: Boolean,
                val reactFan: Boolean,
                val color: SelectOption,
                val creatableColor: SelectOption,
                val disabledField: String,
                val readOnlyField: String
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
                        formProperty = FormData::name,
                        label = "Name",
                        defaultValue = "Carl",
                        validationMapping = stringValidationMapping,
                        validate = ::validateString,
                        onChange = { console.info("Value of name field: $it") }
                    )

                    ExtendedFormTextField(
                        formProperty = FormData::readOnlyField,
                        label = "Read-only Field",
                        defaultValue = "Read-only Field",
                        isReadOnly = true
                    )

                    ExtendedFormTextField(
                        formProperty = FormData::disabledField,
                        label = "Disabled Field",
                        defaultValue = "Disabled Field",
                        isDisabled = true
                    )

                    ExtendedFormTextField(
                        formProperty = FormData::surname,
                        label = "Surname",
                        defaultValue = "Coderrrr",
                        isRequired = true,
                        validationMapping = stringValidationMapping,
                        validate = ::validateString
                    )

                    ExtendedFieldset("Some more info") {
                        ExtendedFormCheckboxField(
                            formProperty = FormData::coder,
                            label = "Coder",
                            defaultIsChecked = true,
                            onChange = { console.info("Value of coder: $it") }
                        )

                        ExtendedFormCheckboxField(
                            formProperty = FormData::reactFan,
                            label = "React fan"
                        )
                    }
                }

                ExtendedFormSection("More data", "Tell me more.") {
                    ExtendedFormSelectField(
                        formProperty = FormData::color,
                        label = "Favorite Color",
                        options = formSelectOptions.toTypedArray(),
                        defaultValue = formSelectOptions[1],
                        onChange = { console.info("Value of color: ${it.label}") }
                    )

                    ExtendedFormCreatableSelectField(
                        formProperty = FormData::creatableColor,
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
}
