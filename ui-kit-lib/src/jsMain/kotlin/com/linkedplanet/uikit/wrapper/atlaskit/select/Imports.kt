@file:JsModule("@atlaskit/select")

package com.linkedplanet.uikit.wrapper.atlaskit.select

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

@JsName("default")
external val SelectGroup: ComponentClass<SelectGroupProps>

external interface SelectGroupProps : Props {
    var inputId: String
    var options: Array<GroupedSelectOptions>
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