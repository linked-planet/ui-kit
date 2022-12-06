package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.checkbox.Checkbox
import react.fc
import react.useState

val CheckboxShowcase = fc<ShowcaseProps> { props ->
    val (isCheckboxActive, setIsCheckboxActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Checkbox"
        packages =
            Package("@atlaskit/checkbox", "https://atlassian.design/components/checkbox/example").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "checkbox"

        val example = createElementNullSafe {
            // region:checkbox
            Checkbox {
                attrs.isChecked = isCheckboxActive
                attrs.onChange = {
                    setIsCheckboxActive(!isCheckboxActive)
                }
            }
            // endregion:checkbox
        }

        examples = listOfNotNull(example)
    }
}
