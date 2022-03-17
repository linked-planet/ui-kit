@file:JsModule("@atlaskit/button")

package com.linkedplanet.uikit.atlaskit.button

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props

@JsName("default")
external val Button: ComponentClass<ButtonProps>

external interface ButtonProps : Props {

    /**
     * Sets whether the checkbox is checked or unchecked.
     */
    var isHover: Boolean

    /**
     * Sets whether the checkbox is checked or unchecked.
     */
    var isFocus: Boolean

    /**
     * Sets whether the checkbox is checked or unchecked.
     */
    var isSelected: Boolean

    /**
     * Sets whether the checkbox is disabled.
     */
    var isDisabled: Boolean

    /**
     * Sets whether the checkbox is disabled.
     */
    var appearance: String

    /**
     * The value to be used in the checkbox input. This is the value that will be returned on form submission.
     */
    var value: String?

    var iconBefore: dynamic

    var className: String

    var onClick: (Event) -> Unit

}

@JsName("ButtonGroup")
external val ButtonGroup: ComponentClass<ButtonGroupProps>

external interface ButtonGroupProps : Props

@JsName("LoadingButton")
external val LoadingButton: ComponentClass<LoadingButtonProps>

external interface LoadingButtonProps : Props {

    var isDisabled: Boolean

    var title: String

    /**
     * Conditionally show a spinner over the top of a button.
     */
    var isLoading: Boolean

    /**
     * Sets whether the checkbox is disabled.
     */
    var appearance: String

    var iconBefore: dynamic

    var className: String

    /**
     * Handler to be called on click. The second argument can be used to track analytics data.
     */
    var onClick: (Event, dynamic) -> Unit
}
