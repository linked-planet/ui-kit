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
@file:JsModule("@atlaskit/button")

package com.linkedplanet.uikit.wrapper.atlaskit.button

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
    var iconBefore: ReactNode

    /**
     * Places an icon within the button, after the button's text.
     */
    var iconAfter: ReactNode

    /**
     * Add a classname to the button.
     */
    var className: String

    /**
     * Handler to be called on click.
     */
    var onClick: (Event) -> Unit

    /**
     * Set the button to autofocus on mount
     */
    var autoFocus: Boolean

    /**
     * Type of the button (e.g. "submit").
     */
    var type: String
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
