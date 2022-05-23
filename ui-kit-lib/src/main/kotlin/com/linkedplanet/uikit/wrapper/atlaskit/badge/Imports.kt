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
@file:JsModule("@atlaskit/badge")

package com.linkedplanet.uikit.wrapper.atlaskit.badge

import react.ComponentClass
import react.Props

@JsName("default")
external val Badge: ComponentClass<BadgeProps>

external interface BadgeProps : Props {

    /**
     * Affects the visual style of the badge.
     * One of
     * - "added",
     * - "default",
     * - "important",
     * - "primary",
     * - "primaryInverted",
     * - "removed"
     */
    var appearance: String

    /**
     * The maximum value to display. Defaults to 99. If the value is 100, and max is 50, "50+" will be displayed. This
     * value should be greater than 0.
     */
    var max: Number
}
