@file:JsModule("@atlaskit/panel")

package com.linkedplanet.uikit.atlaskit.panel

import react.*

@JsName("PanelStateless")
external val PanelStateless: ComponentClass<PanelStatelessProps>

external interface PanelStatelessProps : Props {

    var header: ReactElement

    var isExpanded: Boolean

    var onChange: (Boolean) -> Unit
}
