package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.icon.*
import react.fc

val IconShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Icon"
        packages =
            Package("@atlaskit/icon", "https://atlassian.design/components/icon/icon-explorer").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "icon"

        val example = createElementNullSafe {
            // region:icon
            ArrowDownIcon {}
            BulletListIcon {}
            CheckCircleIcon {}
            LogIcon {}
            RefreshIcon {}
            SendIcon {}
            TrashIcon {
                attrs.primaryColor = "red"
            }
            // endregion:icon
        }

        examples = listOfNotNull(example)
    }
}
