@file:JsModule("@atlaskit/avatar")

package com.linkedplanet.uikit.atlaskit.avatar

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
    var avatar: ReactElement

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
