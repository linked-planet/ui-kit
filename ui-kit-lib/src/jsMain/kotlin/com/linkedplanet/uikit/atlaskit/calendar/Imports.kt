@file:JsModule("@atlaskit/calendar")

package com.linkedplanet.uikit.atlaskit.calendar

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props

@JsName("default")
external val Calendar: ComponentClass<CalendarProps>

external interface CalendarProps : Props {

    /**
     * The number of the day currently focused. Places border around the date. 0 highlights no date.
     */
    var day: Number

    /**
     * The number of the month (from 1 to 12) which the calendar should be on.
     */
    var month: Number

    /**
     * Year to display the calendar for.
     */
    var year: Number

    /**
     * Value of current day, as a string in the format 'YYYY-MM-DD'.
     */
    var today: String

    /**
     * Default for day.
     */
    var defaultDay: Number

    /**
     * Default for month.
     */
    var defaultMonth: Number

    /**
     * Default for year.
     */
    var defaultYear: Number

    /**
     * Default for previouslySelected.
     */
    var defaultPreviouslySelected: Array<String>

    /**
     * Takes an array of dates as string in the format 'YYYY-MM-DD'. All dates provided are greyed out. This does not
     * prevent these dates being selected.
     */
    var disabled: Array<String>

    /**
     * The earliest enabled date.
     */
    var minDate: String

    /**
     * The latest enabled date.
     */
    var maxDate: String

    /**
     * Called when the calendar is navigated. This can be triggered by the keyboard, or by clicking the navigational
     * buttons. The 'interface' property indicates the the direction the calendar was navigated whereas the 'iso'
     * property is a string of the format YYYY-MM-DD.
     */
    var onChange: (Event, Event) -> Unit

    /**
     * Function called when a day is clicked on. Calls with an object that has a day, month and year property as
     * numbers, representing the date just clicked. It also has an 'iso' property, which is a string of the selected
     * date in the format YYYY-MM-DD.
     */
    var onSelect: (DateSelectedEvent, Event) -> Unit

    /**
     * Takes an array of dates as string in the format 'YYYY-MM-DD'. All dates provided are given a background color.
     */
    var previouslySelected: Array<String>

    /**
     * Takes an array of dates as string in the format 'YYYY-MM-DD'. All dates provided are given a background color.
     */
    var selected: Array<String>

    /**
     * BCP 47 language tag (e.g. ja-JP) that ensures dates are in the official format for the locale.
     */
    var locale: String

    /**
     * Start day of the week for the calendar:
     * - 0 sunday (default value)
     * - 1 monday
     * - 2 tuesday
     * - 3 wednesday
     * - 4 thursday
     * - 5 friday
     * - 6 saturday
     */
    var weekStartDay: Number

    /**
     * Class name to apply to the calendar
     */
    var className: String
}

external interface DateSelectedEvent {
    var day: Number
    var month: Number
    var year: Number
    var iso: String
}
