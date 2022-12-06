package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.badge.Badge
import react.fc

val BadgeShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Badge"
        packages =
            Package("@atlaskit/badge", "https://atlassian.design/components/badge/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "badge"

        val badge = createElementNullSafe {
            // region:badge
            Badge {}
            Badge {
                attrs.appearance = "added"
            }
            Badge {
                attrs.appearance = "important"
            }
            // endregion:badge
        }

        examples = listOfNotNull(badge)
    }
}
