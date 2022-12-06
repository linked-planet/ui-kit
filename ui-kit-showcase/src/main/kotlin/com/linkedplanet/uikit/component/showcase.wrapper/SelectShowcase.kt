package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.select.*
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val SelectShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Select"
        packages =
            Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "select"

        // region:select
        val example1 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                Select {
                    attrs.inputId = "select-1"
                    attrs.options = arrayOf(
                        SelectOption("First option", "first"),
                        SelectOption("Second option", "second")
                    )
                }
            }
        }

        val example2 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                SelectGroup {
                    attrs.inputId = "select-2"
                    attrs.options = arrayOf(
                        GroupedSelectOptions("First group", arrayOf(SelectOption("First option", "first"))),
                        GroupedSelectOptions(
                            "Second group",
                            arrayOf(SelectOption("Second option", "second"))
                        )
                    )
                }
            }
        }
        // endregion:select

        examples = listOfNotNull(example1, example2)
    }
}
