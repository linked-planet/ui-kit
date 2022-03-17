@file:JsModule("@atlaskit/select")

package com.linkedplanet.uikit.atlaskit.select

import react.ComponentClass
import react.Props

@JsName("CreatableSelect")
external val CreatableSelect: ComponentClass<CreatableSelectProps>

external interface CreatableSelectProps : Props {

    var onCreateOption: (String) -> Unit

    var onChange: (SelectOption) -> Unit

    var inputId: String

    var options: Array<SelectOption>

    var value: SelectOption?

    var placeholder: String

    var isCompact: Boolean

    var isMulti: Boolean

    var className: String

    var classNamePrefix: String

    var autoFocus: Boolean

    var isDisabled: Boolean

    var styles: SelectStyles

    var spacing: String

    var formatCreateLabel: (String) -> String
}
