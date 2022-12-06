package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.calendar.Calendar
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val CalendarShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Calendar"
        packages =
            Package("@atlaskit/calendar", "https://atlassian.design/components/calendar/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "calendar"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }

                // region:calendar
                Calendar {
                    attrs.locale = "de-DE"
                    attrs.weekStartDay = 1
                }
                // endregion:calendar
            }
        }

        examples = listOfNotNull(example)
    }
}
