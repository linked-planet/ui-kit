@file:JsModule("@atlaskit/checkbox")

package com.linkedplanet.uikit.wrapper.atlaskit.checkbox

import org.w3c.dom.events.Event
import react.*

@JsName("Checkbox")
external val Checkbox: ComponentClass<CheckboxProps>

external interface CheckboxProps : Props {

    /**
     * Id assigned to input.
     */
    var id: String

    /**
     * Sets whether the checkbox is checked or unchecked.
     */
    var isChecked: Boolean

    /**
     * Sets whether the checkbox is disabled.
     */
    var isDisabled: Boolean

    /**
     * Marks the field as invalid. Changes style of unchecked component.
     */
    var isInvalid: Boolean

    /**
     * Marks the field as required & changes the label style.
     */
    var isRequired: Boolean

    /**
     * The label to be displayed to the right of the checkbox.
     * It is part of the clickable element to select the checkbox.
     */
    var label: ReactElement

    /**
     * The value to be used in the checkbox input. This is the value that will be returned on form submission.
     */
    var value: String?

    /**
     * Function that is called whenever the state of the checkbox changes. It will be called with an object containing
     * the react synthetic event. Use currentTarget to get value, name and checked.
     */
    var onChange: (Event) -> Unit

}
