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
package com.linkedplanet.uikit.extension.form

import com.linkedplanet.uikit.util.createElementNullSafe
import com.linkedplanet.uikit.util.createSpan
import com.linkedplanet.uikit.wrapper.atlaskit.checkbox.Checkbox
import com.linkedplanet.uikit.wrapper.atlaskit.checkbox.CheckboxProps
import com.linkedplanet.uikit.wrapper.atlaskit.form.*
import com.linkedplanet.uikit.wrapper.atlaskit.select.*
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextField
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextFieldProps
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.form
import react.dom.onSubmit
import kotlin.reflect.KProperty1

fun <T> RBuilder.ExtendedForm(
    onSubmit: (values: T, form: dynamic, callback: dynamic) -> Unit,
    children: Render
) {
    Form {
        attrs.onSubmit = onSubmit
        attrs.children = { props ->
            createElementNullSafe {
                form {
                    attrs.onSubmit = props.formProps.onSubmit
                    child(createElementNullSafe(children))
                }
            }
        }
    }
}

fun RBuilder.ExtendedFormHeader(title: String, description: String, children: Render? = null) {
    FormHeader {
        attrs.title = createSpan(title)
        attrs.description = createSpan(description)
        children?.let {
            attrs.children = createElementNullSafe(children)
        }
    }
}

fun RBuilder.ExtendedFormFooter(align: String? = null, children: Render) {
    FormFooter {
        attrs.children = createElementNullSafe(children)
        align?.let {
            attrs.align = align
        }
    }
}

fun RBuilder.ExtendedFieldset(legend: String? = null, children: Render) {
    Fieldset {
        legend?.let {
            attrs.legend = createSpan(legend)
        }
        attrs.children = createElementNullSafe(children)
    }
}

fun RBuilder.ExtendedFormSection(title: String, description: String, children: Render) {
    FormSection {
        attrs.title = createSpan(title)
        attrs.description = createSpan(description)
        attrs.children = createElementNullSafe(children)
    }
}

fun RBuilder.ExtendedFormField(
    name: String,
    label: String,
    defaultValue: dynamic = null,
    isRequired: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    createChildren: (FieldInitializationProps) -> ReactNode
) {
    Field {
        attrs.id = name
        attrs.name = name
        attrs.label = createSpan(label)
        attrs.defaultValue = defaultValue
        attrs.isRequired = isRequired
        attrs.validate = validate
        attrs.children = { props ->
            createElementNullSafe {
                child(createChildren(props))

                if (validationMapping != null) {
                    if (props.meta.dirty) {
                        if (props.error == null) {
                            ValidMessage { +validationMapping.validMessage }
                        } else {
                            validationMapping.errors
                                .firstOrNull { it.id == props.error }
                                ?.let { error -> ErrorMessage { +error.message } }
                                ?: ErrorMessage { +"Unknown error" }
                        }
                    } else {
                        HelperMessage { +validationMapping.helpMessage }
                    }
                }
            }
        }
    }
}

fun <T> RBuilder.ExtendedFormTextField(
    formProperty: KProperty1<T, String?>,
    label: String,
    placeholder: String? = null,
    defaultValue: dynamic = null,
    isRequired: Boolean = false,
    isReadOnly: Boolean = false,
    isDisabled: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (String) -> Unit = {}
) = ExtendedFormTextField(
    formProperty.name,
    label,
    placeholder,
    defaultValue,
    isRequired,
    isReadOnly,
    isDisabled,
    validationMapping,
    validate,
    onChange
)

@Deprecated("Use property variant instead.")
fun RBuilder.ExtendedFormTextField(
    name: String,
    label: String,
    placeholder: String? = null,
    defaultValue: dynamic = null,
    isRequired: Boolean = false,
    isReadOnly: Boolean = false,
    isDisabled: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (String) -> Unit = {}
) {
    ExtendedFormField(
        name,
        label,
        defaultValue,
        isRequired,
        validationMapping,
        validate
    ) { props ->
        val textFieldProps: TextFieldProps = props.fieldProps
        val origOnChange = textFieldProps.onChange
        textFieldProps.onChange = {
            val el = it.target as HTMLInputElement?
            if (el != null) {
                onChange(el.value)
            }
            origOnChange(it)
        }
        placeholder?.let { textFieldProps.placeholder = it }
        textFieldProps.isReadOnly = isReadOnly
        textFieldProps.isDisabled = isDisabled
        createElement(TextField, props.fieldProps)
    }
}

fun <T> RBuilder.ExtendedFormSelectField(
    formProperty: KProperty1<T, SelectOption?>,
    label: String,
    placeholder: String? = null,
    noOptionsMessage: ((searchString: String) -> String)? = null,
    value: SelectOption? = null,
    options: Array<SelectOption>,
    defaultValue: SelectOption? = null,
    isRequired: Boolean = false,
    isCompact: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (SelectOption) -> Unit = {},
    menuPosition: String = "fixed"
) = ExtendedFormSelectField(
    formProperty.name,
    label,
    placeholder,
    noOptionsMessage,
    value,
    options,
    defaultValue,
    isRequired,
    isCompact,
    validationMapping,
    validate,
    onChange,
    menuPosition
)

@Deprecated("Use property variant instead.")
fun RBuilder.ExtendedFormSelectField(
    name: String,
    label: String,
    placeholder: String? = null,
    noOptionsMessage: ((searchString: String) -> String)? = null,
    value: SelectOption? = null,
    options: Array<SelectOption>,
    defaultValue: SelectOption? = null,
    isRequired: Boolean = false,
    isCompact: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (SelectOption) -> Unit = {},
    menuPosition: String = "fixed"
) {
    ExtendedFormField(
        name,
        label,
        defaultValue,
        isRequired,
        validationMapping,
        validate
    ) { props ->
        val selectProps: SelectProps = props.fieldProps
        selectProps.spacing = if (isCompact) "compact" else "default"
        selectProps.inputId = name
        selectProps.options = options
        selectProps.menuPosition = menuPosition
        value?.let { selectProps.value = value }
        placeholder?.let { selectProps.placeholder = it }
        noOptionsMessage?.let { selectProps.noOptionsMessage = it }
        val origOnChange = selectProps.onChange
        selectProps.onChange = {
            origOnChange(it)
            onChange(it)
        }
        createElement(Select, selectProps)
    }
}

fun <T> RBuilder.ExtendedFormCreatableSelectField(
    formProperty: KProperty1<T, SelectOption?>,
    label: String,
    placeholder: String? = null,
    formatCreateLabel: ((String) -> String)? = null,
    options: Array<SelectOption>,
    value: SelectOption? = null,
    onCreate: (String) -> Unit,
    defaultValue: SelectOption? = null,
    isRequired: Boolean = false,
    isCompact: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (SelectOption) -> Unit = {},
    menuPosition: String = "fixed"
) = ExtendedFormCreatableSelectField(
    formProperty.name,
    label,
    placeholder,
    formatCreateLabel,
    options,
    value,
    onCreate,
    defaultValue,
    isRequired,
    isCompact,
    validationMapping,
    validate,
    onChange,
    menuPosition
)

@Deprecated("Use property variant instead.")
fun RBuilder.ExtendedFormCreatableSelectField(
    name: String,
    label: String,
    placeholder: String? = null,
    formatCreateLabel: ((String) -> String)? = null,
    options: Array<SelectOption>,
    value: SelectOption? = null,
    onCreate: (String) -> Unit,
    defaultValue: SelectOption? = null,
    isRequired: Boolean = false,
    isCompact: Boolean = false,
    validationMapping: ValidationMapping? = null,
    validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String? = { _, _, _ -> "" },
    onChange: (SelectOption) -> Unit = {},
    menuPosition: String = "fixed"
) {
    ExtendedFormField(
        name,
        label,
        defaultValue,
        isRequired,
        validationMapping,
        validate
    ) { props ->
        val selectProps: CreatableSelectProps = props.fieldProps
        selectProps.spacing = if (isCompact) "compact" else "default"
        selectProps.inputId = name
        selectProps.options = options
        selectProps.onCreateOption = onCreate
        selectProps.menuPosition = menuPosition
        value?.let { selectProps.value = value }
        placeholder?.let { selectProps.placeholder = it }
        formatCreateLabel?.let { selectProps.formatCreateLabel = it }
        val origOnChange = selectProps.onChange
        selectProps.onChange = {
            origOnChange(it)
            onChange(it)
        }
        createElement(CreatableSelect, selectProps)
    }
}

fun <T> RBuilder.ExtendedFormCheckboxField(
    formProperty: KProperty1<T, Boolean>,
    label: String,
    defaultIsChecked: Boolean = false,
    isRequired: Boolean = false,
    isDisabled: Boolean = false,
    onChange: (Boolean) -> Unit = {}
) = ExtendedFormCheckboxField(
    formProperty.name,
    label,
    defaultIsChecked,
    isRequired,
    isDisabled,
    onChange
)

@Deprecated("Use property variant instead.")
fun RBuilder.ExtendedFormCheckboxField(
    name: String,
    label: String,
    defaultIsChecked: Boolean = false,
    isRequired: Boolean = false,
    isDisabled: Boolean = false,
    onChange: (Boolean) -> Unit = {}
) {
    CheckboxField {
        attrs.name = name
        attrs.defaultIsChecked = defaultIsChecked
        attrs.isDisabled = isDisabled
        attrs.isRequired = isRequired
        attrs.children = { props ->
            val checkboxProps: CheckboxProps = props.fieldProps
            checkboxProps.label = createSpan(label)
            val origOnChange = checkboxProps.onChange
            checkboxProps.onChange = {
                val el = it.target as HTMLInputElement?
                if (el != null) {
                    onChange(el.checked)
                }
                origOnChange(it)
            }
            createElement(Checkbox, checkboxProps)
        }
    }
}

data class ValidationMapping(
    val helpMessage: String,
    val validMessage: String,
    val errors: List<ValidationMappingEntry>
)

data class ValidationMappingEntry(
    val id: String,
    val message: String
)
