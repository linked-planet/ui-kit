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
@file:JsModule("@atlaskit/textfield")

package com.linkedplanet.uikit.wrapper.atlaskit.textfield

import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import react.ComponentClass
import react.Props

@JsName("default")
external val Textfield: ComponentClass<TextfieldCProps>

external interface TextfieldCProps : Props {

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
     * Sets the field as uneditable, with a changed hover CState.
     */
    var isDisabled: Boolean

    /**
     * If true, prevents the value of the input from being edited.
     */
    var isReadOnly: Boolean

    /**
     * Set required for form that the field is part of.
     */
    var isRequired: Boolean

    /**
     * Sets styling to indicate that the input is invalid
     */
    var isInvalid: Boolean

    /**
     * Sets a default value as input value
     */
    var defaultValue: String?

    /**
     * Sets content text value to monospace
     */
    var isMonospaced: Boolean

    /**
     * Name of the input element.
     */
    var name: String

    /**
     * The value of the input.
     */
    var value: String?

    /**
     * Placeholder text to display in the text field whenever it is empty.
     */
    var placeholder: String

    /**
     * Handler called when the inputs value changes.
     */
    var onChange: (Event) -> Unit
}
