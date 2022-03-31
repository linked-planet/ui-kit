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
@file:JsModule("react-tooltip")

package com.linkedplanet.uikit.wrapper.tooltip

import react.ComponentClass
import react.Props

@JsName("default")
external val ReactTooltip: ComponentClass<ReactTooltipProps>

external interface ReactTooltipProps : Props {

    /**
     * Id of the tooltip.
     */
    var id: String

    /**
     * Behaviour of tooltip. One of:
     * - float
     * - solid
     */
    var effect: String

    /**
     * Placement of the tooltip. One of:
     * - top,
     * - right,
     * - bottom,
     * - left
     */
    var place: String

    /**
     * Theme of the tooltip. One of:
     * - top,
     * - right,
     * - bottom,
     * - left
     */
    var type: String

    /**
     * Offset of the tooltip.
     */
    var offset: ReactTooltipOffset

    /**
     * Extra custom class, can use !important to overwrite react-tooltip's default class.
     */
    var className: String

    /**
     * Popup border- add one pixel border.
     */
    var border: Boolean

    /**
     * Popup border color - enabled by the border value.
     */
    var borderColor: String
}