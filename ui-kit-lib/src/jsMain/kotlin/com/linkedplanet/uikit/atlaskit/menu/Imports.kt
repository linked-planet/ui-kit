@file:JsModule("@atlaskit/menu")

package com.linkedplanet.uikit.atlaskit.menu

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props
import react.ReactElement

@JsName("MenuGroup")
external val MenuGroup: ComponentClass<MenuGroupCProps>

external interface MenuGroupCProps : Props {

    var children: ReactElement

}

@JsName("LinkItem")
external val LinkItem: ComponentClass<LinkItemCProps>

external interface LinkItemCProps : Props {

    var onClick: (Event) -> Unit

    var iconBefore: ReactElement

}
