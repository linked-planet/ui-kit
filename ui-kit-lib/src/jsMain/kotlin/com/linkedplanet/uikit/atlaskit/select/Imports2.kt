@file:JsModule("@atlaskit/select")

package com.linkedplanet.uikit.atlaskit.select

import react.ComponentClass
import react.Props

@JsName("default")
external val MultiSelect: ComponentClass<MultiSelectProps>

external interface MultiSelectProps : Props {

    var inputId: String

    var options: Array<SelectOption>

    var value: Array<SelectOption>

    var placeholder: String

    var isCompact: Boolean

    var isMulti: Boolean

    var className: String

    var classNamePrefix: String

    var onChange: (Array<SelectOption>) -> Unit

    var autoFocus: Boolean

    var isDisabled: Boolean

    var styles: SelectStyles

    var spacing: String

    var noOptionsMessage: (searchString: String) -> String
}
