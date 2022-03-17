@file:JsModule("@atlaskit/tabs")

package com.linkedplanet.uikit.atlaskit.tab

import react.*

@JsName("default")
external val Tabs: ComponentClass<TabsProps>

external interface TabsProps : Props {
    var tabs: Array<Tab>
    var selected: Number
    var onSelect: (dynamic, Number, dynamic) -> Unit
}
