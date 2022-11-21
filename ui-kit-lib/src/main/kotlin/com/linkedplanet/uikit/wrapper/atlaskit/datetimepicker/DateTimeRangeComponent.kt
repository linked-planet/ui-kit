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
package com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker

import com.linkedplanet.uikit.wrapper.atlaskit.calendar.Calendar
import moment.Moment
import moment.moment
import react.*


external interface DateTimeRangeProps: Props {
    /**
     * The earliest enabled date. Dates before this are disabled on the calendar. This does not affect what users can type into the picker.
     */
    var minDate: String?

    /**
     * The latest enabled date. Dates after this are disabled on the calendar. This does not affect what users can type into the picker.
     */
    var maxDate: String?

    /**
     * Takes an array of dates as string in the format 'YYYY-MM-DD'. All dates provided are greyed out. This does not prevent these dates being selected.
     */
    var disabledDates: Array<String>

    /**
     * The startDate for the calendar selection (YYYY-MM-DD).
     */
    var startDate: String?

    /**
     * The endDate for the calendar selection (YYYY-MM-DD).
     */
    var endDate: String?

    /**
     * Locale used to format the date and calendar. See DateTimeFormat.
     */
    var locale: String  // "de-de"

    /**
     * Start day of the week for the calendar.
            0 sunday (default value)
            1 monday
            2 tuesday
            3 wednesday
            4 thursday
            5 friday
            6 saturday
     */
    var weekStartDay: Int // 1

    /**
     * Called when the startDate and endDate is selected. The only argument is an ISO time or empty string.
     */
    var onChange: (String, String) -> Unit

    /**
     * Called when there are collisions detected.
     */
    var onCollision: () -> Unit
}

val DateTimeRange = fc<DateTimeRangeProps> { props ->

    val (dateSelectCounter, setDateSelectCounter) = useState(0)
    val (startDate, setStartDate) = useState(props.startDate)
    val (endDate, setEndDate) = useState(props.endDate)

    fun getDatesBetweenStartEnd(startDate: Moment, endDate: Moment): Set<Moment> {
        val daysDiff = endDate.diff(startDate, "day").toInt()
        return (0..daysDiff).map {
            moment(startDate).add("day", it)
        }.toSet()
    }

    fun Moment.toDateString(): String =
        this.format("yyyy-MM-DD")

    fun getSelectedDates(): Array<String> =
        when {
            startDate != null && endDate == null -> {
                listOf(startDate!!).toTypedArray()
            }
            startDate != null && endDate != null -> {
                getDatesBetweenStartEnd(moment(startDate), moment(endDate)).map { it.toDateString() }.toTypedArray()
            }
            else -> {
                listOf<String>().toTypedArray()
            }
        }

    fun checkCollisions(to: String): Boolean {
        val range = getDatesBetweenStartEnd(moment(startDate), moment(to)).map { it.toDateString() }.toSet()
        val intersections = range.intersect(props.disabledDates.toSet())
        val result = intersections.isNotEmpty()
        return result
    }

    fun onDateSelect(value: String) {
        if (dateSelectCounter == 0) {
            // select from
            setStartDate(value)
            setEndDate(null)
            setDateSelectCounter(1)
        } else if (dateSelectCounter == 1) {
            // select to
            if (checkCollisions(value)) {
                setDateSelectCounter(0)
                props.onCollision()
            } else if (moment(value).isBefore(moment(startDate))) {
                setDateSelectCounter(0)
                props.onCollision()
            } else {
                setDateSelectCounter(2)
                setEndDate(value)
                props.onChange(startDate!!, value)
            }
        } else {
            // select from
            setStartDate(value)
            setEndDate(null)
            setDateSelectCounter(1)
        }
    }

    Calendar {
        attrs.disabled = props.disabledDates
        attrs.selected = getSelectedDates()
        attrs.onSelect = { event1, _ ->
            val selectedDate = event1.iso
            onDateSelect(selectedDate)
        }
        if(props.minDate != null){
            attrs.minDate = props.minDate!!
        }
        if(props.maxDate != null){
            attrs.maxDate = props.maxDate!!
        }
        attrs.locale = props.locale
        attrs.weekStartDay = props.weekStartDay
    }

}