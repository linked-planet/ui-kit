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
package com.linkedplanet.uikit.wrapper.atlaskit.icon

import org.w3c.dom.events.MouseEvent
import react.Props

interface IconProps : Props {
    /**
     * Primary color for the icon. Inherits the current font color by default.
     */
    var primaryColor: String

    /**
     * Secondary color for the icon. Defaults to the page background for an icon that supports two colors.
     */
    var secondaryColor: String

    /**
     * Control the size of the icon.
     * One of ['small', 'medium', 'large', 'xlarge']
     */
    var size: String

    /**
     * Text used to describe what the icon is in context. A label is needed when there is no pairing visible text next
     * to the icon. An empty string marks the icon as presentation only.
     */
    var label: String

    var onClick: (MouseEvent) -> Unit
}
