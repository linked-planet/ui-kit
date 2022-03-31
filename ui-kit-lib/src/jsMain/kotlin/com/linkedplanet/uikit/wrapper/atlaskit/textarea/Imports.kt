@file:JsModule("@atlaskit/textarea")

package com.linkedplanet.uikit.wrapper.atlaskit.textarea

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props

@JsName("default")
external val Textarea: ComponentClass<TextareaProps>

external interface TextareaProps : Props {

    /**
     * Controls the appearance of the field. subtle shows styling on hover. none hides all field styling.
     * One of <`standard`, `none`, `subtle`>
     */
    var appearance: String

    /**
     * Applies compact styling, making the field smaller
     */
    var isCompact: Boolean

    /**
     * Sets a default value as input value
     */
    var defaultValue: String?

    /**
     * Sets the field as uneditable, with a changed hover CState.
     */
    var isDisabled: Boolean

    /**
     * Sets styling to indicate that the input is invalid
     */
    var isInvalid: Boolean

    /**
     * Sets content text value to monospace
     */
    var isMonospaced: Boolean

    /**
     * If true, prevents the value of the input from being edited.
     */
    var isReadOnly: Boolean

    /**
     * Set required for form that the field is part of.
     */
    var isRequired: Boolean

    /**
     * The value of the input.
     */
    var value: String?

    /**
     * The placeholder within the textarea.
     */
    var placeholder: String

    /**
     * Handler to be called when the input changes.
     */
    var onChange: (Event) -> Unit

    /**
     * The minimum number of rows of text to display
     */
    var minimumRows: Int

    /**
     * The maxheight of the textarea
     */
    var maxHeight: String

    /**
     * Enables the resizing of the textarea:
     * - auto: both directions.
     * - horizontal: only along the x axis.
     * - vertical: only along the y axis.
     * - smart (default): vertically grows and shrinks the textarea automatically to wrap your input text.
     * - none: explicitly disallow resizing on the textarea.
     */
    var resize: String
}
