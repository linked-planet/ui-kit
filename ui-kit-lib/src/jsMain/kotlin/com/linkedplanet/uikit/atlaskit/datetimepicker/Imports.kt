@file:JsModule("@atlaskit/datetime-picker")

package com.linkedplanet.uikit.atlaskit.datetimepicker

import react.ComponentClass
import react.Props

@JsName("DateTimePicker")
external val DateTimePicker: ComponentClass<DateTimePickerProps>

external interface DateTimePickerProps : Props {
    var value: String
    var datePickerProps: DatePickerProps
    var timePickerProps: TimePickerProps
    var times: Array<String>
    var onChange: (value: String) -> Unit
    var locale: String
    var spacing: String
    var timeIsEditable: Boolean
}