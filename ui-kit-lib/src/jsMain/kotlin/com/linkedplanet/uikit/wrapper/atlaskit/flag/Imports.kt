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
@file:JsModule("@atlaskit/flag")

package com.linkedplanet.uikit.wrapper.atlaskit.flag

import react.ComponentClass
import react.Props

@JsName("default")
external val Flag: ComponentClass<FlagCProps>

external interface FlagCProps : Props {
    /**
     * Array of clickable actions to be shown at the bottom of the flag.
     * For flags where appearance is 'normal', actions will be shown as links.
     * For all other appearance values, actions will shown as buttons.
     * If href is passed the action will be shown as a link with the passed href prop.
     */
    var actions: Array<FlagAction>

    /**
     * Makes the flag appearance bold. Setting this to anything other than 'normal' hides the dismiss button.
     * One of ["error", "info", "normal", "success", "warning"]
     */
    var appearance: String

    /**
     * The icon displayed in the top-left of the flag. Should be an instance of @atlaskit/icon.
     * Your icon will receive the appropriate default color, which you can override by wrapping the icon in a
     * containing element with CSS color set to your preferred icon color.
     */
    var icon: dynamic

    /**
     * The secondary content shown below the flag title
     */
    var description: String?

    /**
     * The bold text shown at the top of the flag.
     */
    var title: String

    /**
     * A unique identifier used for rendering and onDismissed callbacks.
     */
    var id: String
}

@JsName("AutoDismissFlag")
external val AutoDismissFlag: ComponentClass<FlagCProps>

@JsName("FlagGroup")
external val FlagGroup: ComponentClass<FlagGroupCProps>

external interface FlagGroupCProps : Props {
    var onDismissed: (String) -> Unit
}
