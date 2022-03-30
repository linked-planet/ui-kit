@file:JsModule("@atlaskit/button")

package com.linkedplanet.uikit.atlaskit.button

import org.w3c.dom.events.Event
import react.*

@JsName("default")
external val Button: ComponentClass<ButtonProps>

external interface ButtonProps : Props {

    /**
     * The base styling to apply to the button.
     * One of
     * - "default",
     * - "danger",
     * - "link",
     * - "primary",
     * - "subtle",
     * - "subtle-link",
     * - "warning"
     */
    var appearance: String

    /**
     * Change the style to indicate the button is selected.
     */
    var isSelected: Boolean

    /**
     * Set if the button is disabled.
     */
    var isDisabled: Boolean

    /**
     * Places an icon within the button, before the button's text.
     */
    var iconBefore: ReactElement

    /**
     * Add a classname to the button.
     */
    var className: String

    /**
     * Handler to be called on click.
     */
    var onClick: (Event) -> Unit
}

@JsName("ButtonGroup")
external val ButtonGroup: ComponentClass<ButtonGroupProps>

external interface ButtonGroupProps : Props

@JsName("LoadingButton")
external val LoadingButton: ComponentClass<LoadingButtonProps>

external interface LoadingButtonProps : ButtonProps {

    /**
     * Conditionally show a spinner over the top of a button.
     */
    var isLoading: Boolean
}
