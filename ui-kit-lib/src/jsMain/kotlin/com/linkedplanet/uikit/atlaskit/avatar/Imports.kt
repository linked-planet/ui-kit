@file:JsModule("@atlaskit/avatar")

package com.linkedplanet.uikit.atlaskit.avatar

import org.w3c.dom.events.MouseEvent
import react.ComponentClass
import react.Props

@JsName("default")
external val Avatar: ComponentClass<AvataProps>

external interface AvataProps : Props {
    var src: String
    var name: String
    var size: String
    var presence: String
}

@JsName("AvatarItem")
external val AvatarItem: ComponentClass<AvatarItemCProps>

external interface AvatarItemCProps : Props {
    var avatar: dynamic
    var key: String
    var primaryText: String
    var secondaryText: String
    var onClick: (MouseEvent) -> Unit
}
