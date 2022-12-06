package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.flag.Flag
import com.linkedplanet.uikit.wrapper.atlaskit.icon.WarningIcon
import react.fc

val FlagShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Flag"
        packages = Package("@atlaskit/flag", "https://atlassian.design/components/flag/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "flag"

        val example = createElementNullSafe {
            // region:flag
            Flag {
                attrs.title = "Flag"
                attrs.icon = createElementNullSafe {
                    WarningIcon {}
                }
                attrs.description = "Description of flag."
            }
            // endregion:flag
        }

        examples = listOfNotNull(example)
    }
}
