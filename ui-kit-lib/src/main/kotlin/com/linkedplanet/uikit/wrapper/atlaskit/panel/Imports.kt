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
@file:JsModule("@atlaskit/panel")

package com.linkedplanet.uikit.wrapper.atlaskit.panel

import react.*

@JsName("PanelStateless")
external val PanelStateless: ComponentClass<PanelStatelessProps>

external interface PanelStatelessProps : Props {

    /**
     * Header to render on the panel. Clicking the header expands and collapses the panel.
     */
    var header: ReactNode

    /**
     * Defines whether the panel is expanded by default (default: false).
     */
    var isExpanded: Boolean

    /**
     * This callback is called when panel is expanded/collapsed
     */
    var onChange: (Boolean) -> Unit
}
