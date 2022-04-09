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
@file:JsModule("@atlaskit/avatar")

package com.linkedplanet.uikit.wrapper.atlaskit.avatar

import org.w3c.dom.events.Event
import react.*

@JsName("default")
external val Avatar: ComponentClass<AvatarProps>

external interface AvatarProps : Props {

    /**
     * An url to load an image from (this can also be a base64 encoded image).
     */
    var src: String

    /**
     * Name will be displayed in a tooltip, also used by screen readers as fallback content if the image fails to load.
     */
    var name: String

    /**
     * Defines the size of the avatar.
     * One of
     * - "xsmall",
     * - "small",
     * - "medium",
     * - "large",
     * - "xlarge",
     * - "xxlarge"
     */
    var size: String

    /**
     * ndicates a user's online status by showing a small icon on the avatar. Refer to presence values on the Presence
     * component. Alternatively accepts any React element. For best results, it is recommended to use square content
     * with height and width of 100%.
     * One of
     * - "online",
     * - "busy",
     * - "focus",
     * - "offline"
     */
    var presence: String
}

@JsName("AvatarItem")
external val AvatarItem: ComponentClass<AvatarItemProps>

external interface AvatarItemProps : Props {

    /**
     * Slot to place an avatar element. Use @atlaskit/avatar.
     */
    var avatar: ReactNode

    /**
     * Primary text.
     */
    var primaryText: String

    /**
     * Secondary text written underneath the primary text.
     */
    var secondaryText: String

    /**
     * Handler to be called on click.
     */
    var onClick: (Event) -> Unit
}
