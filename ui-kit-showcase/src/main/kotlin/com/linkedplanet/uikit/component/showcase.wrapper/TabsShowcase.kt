package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tab
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tabs
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val TabsShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Tabs"
        packages = Package("@atlaskit/tabs", "https://atlassian.design/components/tabs/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "tabs"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:tabs
                Tabs {
                    attrs.tabs = arrayOf(
                        Tab("First tab", createSpan("First")),
                        Tab("Second tab", createSpan("Second")),
                    )
                }
                // endregion:tabs
            }
        }

        examples = listOfNotNull(example)
    }
}
