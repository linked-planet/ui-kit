@file:JsModule("@atlaskit/select")

package com.linkedplanet.uikit.atlaskit.select

import react.ComponentClass
import react.Props

@JsName("default")
external val Select: ComponentClass<SelectProps>

external interface SelectProps : Props {

    var inputId: String

    var options: Array<SelectOption>

    var value: SelectOption?

    var placeholder: String

    var spacing: String

    var className: String

    var classNamePrefix: String

    var onChange: (SelectOption) -> Unit

    var autoFocus: Boolean

    var isDisabled: Boolean

    var styles: SelectStyles

    var noOptionsMessage: (searchString: String) -> String
}
