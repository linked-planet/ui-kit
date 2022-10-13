@file:JsModule("react-awesome-slider")

package com.linkedplanet.uikit.wrapper.awesomeslider

import react.*

@JsName("default")
external val AwesomeSlider: ComponentClass<AwesomeSliderProps>

external interface AwesomeSliderProps : PropsWithChildren {
    var bullets: Boolean
}
