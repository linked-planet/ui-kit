/**
 * Copyright 2022 linked-planet GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    var menuPosition: String
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
