package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.textfield.TextField
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val TextFieldShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Text field"
        packages =
            Package("@atlaskit/textfield", "https://atlassian.design/components/textfield/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "textfield"

        // region:textfield
        val example1 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                TextField {
                    attrs.defaultValue = "Content of text field..."
                }
            }
        }

        val example2 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                TextField {
                    attrs.defaultValue = "Password"
                    attrs.type = "password"
                }
            }
        }
        // endregion:textfield

        examples = listOfNotNull(example1, example2)
    }
}
