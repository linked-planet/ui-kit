package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker.DateTimeRange
import csstype.px
import emotion.react.css
import moment.moment
import react.dom.html.ReactHTML.div
import react.fc

val DateTimeRangePickerShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Date time Range picker"
        packages = Package(
            "@atlaskit/datetime-picker",
            "https://atlassian.design/components/datetime-picker/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "datetime-range-picker"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:datetime-range-picker
                val today = moment().format("yyyy-MM-DD")
                val todayPlus2 = moment().add(2, "day").format("yyyy-MM-DD")
                val todayPlus10 = moment().add(10, "day").format("yyyy-MM-DD")
                DateTimeRange {
                    attrs.minDate = today
                    attrs.maxDate = todayPlus10
                    attrs.disabledDates = arrayOf(todayPlus2)
                    attrs.locale = "de-de"
                    attrs.onCollision = {
                        console.info("Collision detected")
                    }
                    attrs.onChange = { start, end ->
                        console.info("Selected Range: ($start) --> ($end)")
                    }
                }
                // endregion:datetime-range-picker
            }
        }

        examples = listOfNotNull(example)
    }
}
