@file:JsModule("@atlaskit/lozenge")

package com.linkedplanet.uikit.atlaskit.lozenge

import react.ComponentClass
import react.Props

@JsName("default")
external val Lozenge: ComponentClass<LozengeCProps>

external interface LozengeCProps : Props {

    /**
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
