package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.toggle.Toggle
import react.fc
import react.useState

val ToggleShowcase = fc<ShowcaseProps> { props ->
    val (isToggleActive, setIsToggleActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Toggle"
        packages =
            Package("@atlaskit/toggle", "https://atlassian.design/components/toggle/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "toggle"

        val example = createElementNullSafe {
            // region:toggle
            Toggle {
                attrs.isChecked = isToggleActive
                attrs.onChange = {
                    setIsToggleActive(!isToggleActive)
                }
            }
            // endregion:toggle
        }

        examples = listOfNotNull(example)
    }
}
