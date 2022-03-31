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
@file:JsModule("@atlaskit/datetime-picker")

package com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker

import react.ComponentClass
import react.Props

@JsName("DateTimePicker")
external val DateTimePicker: ComponentClass<DateTimePickerProps>

external interface DateTimePickerProps : Props {

    /**
     * Set the appearance of the picker. "subtle" will remove the borders and background.
     * One of
     * - "default",
     * - "subtle"
     */
    var appearance: String

    /**
     * The ISO time that should be used as the input value.
     */
    var value: String

    /**
     * Props applied to the DatePicker.
     */
    var datePickerProps: DatePickerProps

    /**
     * Props applied to the TimePicker.
     */
    var timePickerProps: TimePickerProps

    /**
     * The times shown by the TimePicker.
     */
    var times: Array<String>

    /**
     * Called when the value changes and the date / time is a complete value, or empty.
     * The only value is an ISO string or empty string.
     */
    var onChange: (value: String) -> Unit

    /**
     * Locale used for formatting dates and times. See DateTimeFormat.
     */
    var locale: String

    /**
     * The spacing for the select control. Compact is gridSize() * 4, default is gridSize() * 5.
     * One of
     * - "compact",
     * - "default"
     */
    var spacing: String

    /**
     * Set if users can edit the input, allowing them to add custom times.
     */
    var timeIsEditable: Boolean
}