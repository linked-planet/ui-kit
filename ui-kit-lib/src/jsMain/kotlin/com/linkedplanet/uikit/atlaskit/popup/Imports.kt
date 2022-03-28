@file:JsModule("@atlaskit/popup")

package com.linkedplanet.uikit.atlaskit.popup

import react.ComponentClass
import react.Props
import react.ReactElement

@JsName("Popup")
external val Popup: ComponentClass<PopupCProps>

external interface PopupCProps : Props {

    var isOpen: Boolean

    var trigger: (Props) -> ReactElement

    var content: (Props) -> ReactElement

    var boundariesElement: String

    var id: String

    var offset: String

    var placement: String

    var shouldFlip: Boolean

    var onClose: () -> Unit

    var popupComponent: () -> ReactElement

    var zIndex: Int

    var autoFocus: Boolean

}
