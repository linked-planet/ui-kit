package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.emptystate.EmptyState
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc

val EmptyStateShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Empty state"
        packages = Package(
            "@atlaskit/empty-state",
            "https://atlassian.design/components/empty-state/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "empty-state"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:empty-state
                EmptyState {
                    attrs.header = "Empty state"
                    attrs.description = createElementNullSafe {
                        span {
                            +"Content of this state..."
                        }
                    }
                    attrs.primaryAction = createElementNullSafe {
                        Button {
                            +"Dummy button"
                        }
                    }
                }
                // endregion:empty-state
            }
        }

        examples = listOfNotNull(example)
    }
}
