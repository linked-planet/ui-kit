package com.linkedplanet.uikit.atlaskit.icon

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
