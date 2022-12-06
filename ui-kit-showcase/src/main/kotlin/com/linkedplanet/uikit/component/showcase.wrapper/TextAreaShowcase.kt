package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.textarea.TextArea
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val TextAreaShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Text area"
        packages =
            Package("@atlaskit/textarea", "https://atlassian.design/components/textarea/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "textarea"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:textarea
                TextArea {
                    attrs.defaultValue = "Content of text area..."
                }
                // endregion:textarea
            }
        }

        examples = listOfNotNull(example)
    }
}
