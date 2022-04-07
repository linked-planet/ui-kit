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
@file:JsModule("@atlaskit/form")

package com.linkedplanet.uikit.wrapper.atlaskit.form

import react.*

@JsName("default")
external val Form: ComponentClass<FormProps>

external interface FormProps : Props {

    /**
     * Event handler called when the form is submitted. Fields must be free of validation errors.
     */
    var onSubmit: (values: dynamic, form: dynamic, callback: dynamic) -> Unit

    /**
     * Sets the form and its fields as disabled. Users cannot edit or focus on the fields.
     */
    var isDisabled: Boolean

    /**
     * The contents rendered inside of the form. This is a function where the props will be passed from the form. The
     * function props you can access are dirty, submitting and disabled. You can read more about these props in
     * react-final form documentation.
     */
    var children: (FormInitializationProps) -> ReactNode
}

external interface FormInitializationProps {

    /**
     * Indicates if this form contains changed information.
     */
    var dirty: Boolean

    /**
     * Indicates if this form is currently submitting.
     */
    var submitting: Boolean

    /**
     * Indicates if this form is disabled.
     */
    var disabled: Boolean

    /**
     * Contains all necessary data for the child node.
     */
    var formProps: dynamic
}

@JsName("Field")
external val Field: ComponentClass<FieldProps>

external interface FieldProps : Props {

    /**
     * Passed to the ID attribute of the field. This is randomly generated if it is not specified.
     */
    var id: String

    /**
     * Sets the default value of the field. If a function is provided, it is called with the current default value of
     * the field.
     */
    var defaultValue: dynamic

    /**
     * Sets whether the field is required for submission. Required fields are marked with a red asterisk.
     */
    var isRequired: Boolean

    /**
     * Sets whether the field is disabled. Users cannot edit or focus on the fields. If the parent form component is
     * disabled, then the field will always be disabled.
     */
    var isDisabled: Boolean

    /**
     * Label displayed above the form field.
     */
    var label: ReactNode

    /**
     * Specifies the name of the field. This is important for referencing the form data.
     */
    var name: String

    /**
     * Checks whether the field input is valid. This is usually used to display a message relevant to the current value
     * using ErrorMessage, HelperMessage or ValidMessage.
     *
     * Returns null if the input is valid, otherwise a string with the error type name.
     */
    var validate: (value: dynamic, form: dynamic, fieldState: dynamic) -> String?

    /**
     * Content to render in the field. This is a function that is called with props for the field component and other
     * information about the field.
     */
    var children: (FieldInitializationProps) -> ReactNode
}

external interface FieldInitializationProps {

    /**
     * Contains all necessary data for the child node.
     */
    var fieldProps: dynamic

    /**
     * Indicates if the input for this field is valid.
     */
    var valid: Boolean

    /**
     * The error information given by the validation function.
     */
    var error: String?

    /**
     * Contains all form meta information.
     */
    var meta: FieldMetaInitializationProps
}

external interface FieldMetaInitializationProps {
    /**
     * Indicates if the form contains changed information.
     */
    var dirty: Boolean

    /**
     * Indicates if the form contains changed information since the user tried to submit it the last time.
     */
    var dirtySinceLastSubmit: Boolean

    /**
     * Indicates if this form has been touched by the user.
     */
    var touched: Boolean
}

@JsName("CheckboxField")
external val CheckboxField: ComponentClass<CheckboxFieldProps>

external interface CheckboxFieldProps : Props {

    /**
     * Sets the default state of the checkbox as checked.
     */
    var defaultIsChecked: Boolean

    /**
     * Sets whether the field is required for submission. Required fields are marked with a red asterisk.
     */
    var isRequired: Boolean

    /**
     * Sets whether the field is disabled. Users cannot edit or focus on the fields. If the parent form component is
     * disabled, then the field will always be disabled.
     */
    var isDisabled: Boolean

    /**
     * Label displayed beside the checkbox.
     */
    var label: ReactNode

    /**
     * Specifies the name of the field. This is important for referencing the form data.
     */
    var name: String

    /**
     * The value of the checkbox. This is the value used in the form state when the checkbox is checked.
     */
    var value: String

    /**
     * Content to render in the checkbox field. This is a function that is called with information about the field.
     */
    var children: (FieldInitializationProps) -> ReactNode
}

@JsName("Fieldset")
external val Fieldset: ComponentClass<FieldsetProps>

external interface FieldsetProps : Props {

    /**
     * Label describing the contents of the fieldset.
     */
    var legend: ReactNode

    /**
     * Content to render in the fieldset.
     */
    var children: ReactNode
}

@JsName("FormHeader")
external val FormHeader: ComponentClass<FormHeaderProps>

external interface FormHeaderProps : Props {

    /**
     * Title of the form. This is a header.
     */
    var title: ReactNode

    /**
     * Description or subtitle of the form.
     */
    var description: ReactNode

    /**
     * Child content to render in the form below the title and description.
     */
    var children: ReactNode
}

@JsName("FormFooter")
external val FormFooter: ComponentClass<FormFooterProps>

external interface FormFooterProps : Props {

    /**
     * Sets the alignment of the footer contents. This is often a button. This should be left-aligned in single-page
     * forms, flags, cards, and section messages. One of:
     * - "start"
     * - "end"
     */
    var align: String

    /**
     * Content to render in the footer of the form.
     */
    var children: ReactNode
}

@JsName("FormSection")
external val FormSection: ComponentClass<FormSectionProps>

external interface FormSectionProps : Props {

    /**
     * Title of the form. This is a header.
     */
    var title: ReactNode

    /**
     * Description or subtitle of the form.
     */
    var description: ReactNode

    /**
     * Content or components to render after the description.
     */
    var children: ReactNode
}

@JsName("ErrorMessage")
external val ErrorMessage: ComponentClass<Props>

@JsName("HelperMessage")
external val HelperMessage: ComponentClass<Props>

@JsName("ValidMessage")
external val ValidMessage: ComponentClass<Props>
