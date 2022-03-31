@file:JsModule("react-tooltip")

package com.linkedplanet.uikit.tooltip

import react.ComponentClass
import react.Props

@JsName("default")
external val ReactTooltip: ComponentClass<ReactTooltipProps>

external interface ReactTooltipProps : Props {

    /**
     * Id of the tooltip.
     */
    var id: String

    /**
     * Behaviour of tooltip. One of:
     * - float
     * - solid
     */
    var effect: String

    /**
     * Placement of the tooltip. One of:
     * - top,
     * - right,
     * - bottom,
     * - left
     */
    var place: String

    /**
     * Theme of the tooltip. One of:
     * - top,
     * - right,
     * - bottom,
     * - left
     */
    var type: String

    /**
     * Offset of the tooltip.
     */
    var offset: ReactTooltipOffset

    /**
     * Extra custom class, can use !important to overwrite react-tooltip's default class.
     */
    var className: String

    /**
     * Popup border- add one pixel border.
     */
    var border: Boolean

    /**
     * Popup border color - enabled by the border value.
     */
    var borderColor: String
}