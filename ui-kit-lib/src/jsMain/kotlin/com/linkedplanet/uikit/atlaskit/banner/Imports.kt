@file:JsModule("@atlaskit/banner")

package com.linkedplanet.uikit.atlaskit.banner

import react.*

@JsName("default")
external val Banner: ComponentClass<BannerProps>

external interface BannerProps : Props {

    /**
     * Visual style to be used for the banner, one of ["warning", "error", "announcement"].
     */
    var appearance: String

    /**
     * Icon to be shown left of the main content. Typically an Atlaskit @atlaskit/icon.
     */
    var icon: ReactElement?

    /**
     * Defines whether the banner is shown. An animation is used when the value is changed.
     * Default: false
     */
    var isOpen: Boolean
}
