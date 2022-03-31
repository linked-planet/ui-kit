package com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker

data class DatePickerProps(
    var weekStartDay: Number = 1,
    var minDate: String = "",
    var maxDate: String = ""
)

data class TimePickerProps(
    var isDisabled: Boolean = false,
    var selectProps: SelectProps
)

data class SelectProps(
    var isSearchable: Boolean = false,
    var classNamePrefix: String
)