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
@file:JsModule("@atlaskit/lozenge")

package com.linkedplanet.uikit.wrapper.atlaskit.lozenge

import react.ComponentClass
import react.Props

@JsName("default")
external val Lozenge: ComponentClass<LozengeProps>

external interface LozengeProps : Props {

    /**
     * The appearance type.
     * One of "default", "inprogress", "moved", "new", "removed", "success"
     */
    var appearance: String

    /**
     * Determines whether to apply the bold style or not.
     */
    var isBold: Boolean

    /**
     * max-width of lozenge container. Default to 200px.
     */
    var maxWidth: String

}
