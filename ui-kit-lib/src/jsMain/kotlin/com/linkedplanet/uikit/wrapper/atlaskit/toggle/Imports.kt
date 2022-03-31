@file:JsModule("@atlaskit/toggle")

package com.linkedplanet.uikit.wrapper.atlaskit.toggle

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props

@JsName("default")
external val Toggle: ComponentClass<ToggleProps>

external interface ToggleProps : Props {

    /**
     * Use a pairing label with your toggle using id and htmlFor props to set the relationship. For more information see
     * labels on MDN web docs.
     */
    var id: String

    /**
     * Toggle size. One of:
     * - "regular",
     * - "large"
     */
    var size: String

    /**
     * Handler to be called when native 'change' event happens internally.
     */
    var onChange: (Event) -> Unit

    /**
     * If the toggle is disabled or not. This prevents any interaction.
     */
    var isDisabled: Boolean


    /**
     * Whether the toggle is initially checked or not After initial mount whether the component is checked or not is
     * controlled by the component.
     */
    var defaultChecked: Boolean

    /**
     * If defined it takes precedence over defaultChecked and Toggle acts.
     */
    var isChecked: Boolean

    /**
     * Descriptive name for value property to be submitted in a form.
     */
    var name: String

    /**
     * Text to be used as aria-label of toggle component. Use when there is not visible label that you can pair toggle
     * with.
     */
    var label: String
}