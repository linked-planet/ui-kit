package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.panel.PanelStateless
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc
import react.useState

val PanelShowcase = fc<ShowcaseProps> { props ->
    val (isPanelActive, setIsPanelActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Panel"
        packages = Package(
            "@atlaskit/panel",
            "https://atlaskit.atlassian.com/packages/bitbucket/panel"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "panel"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                    paddingLeft = 24.px
                }

                // region:panel
                PanelStateless {
                    attrs.isExpanded = isPanelActive
                    attrs.onChange = {
                        setIsPanelActive(!isPanelActive)
                    }
                    attrs.header = createElementNullSafe {
                        span {
                            +"Panel"
                        }
                    }

                    span {
                        +"Panel content..."
                    }
                }
                // endregion:panel
            }
        }

        examples = listOfNotNull(example)
    }
}
