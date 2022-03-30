@file:JsModule("@atlaskit/tag")

package com.linkedplanet.uikit.atlaskit.tag

import react.*

@JsName("SimpleTag")
external val SimpleTag: ComponentClass<SimpleTagProps>

external interface SimpleTagProps : Props {
    var text: String
    var color: String
}


