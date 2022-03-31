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