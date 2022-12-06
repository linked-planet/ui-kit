package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.datetimepicker.DateTimePicker
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val DateTimePickerShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Date time picker"
        packages = Package(
            "@atlaskit/datetime-picker",
            "https://atlassian.design/components/datetime-picker/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "datetime-picker"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:datetime-picker
                DateTimePicker {}
                // endregion:datetime-picker
            }
        }

        examples = listOfNotNull(example)
    }
}
