package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.dropdownmenu.*
import react.fc

val DropdownMenuShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Dropdown menu"
        packages = Package(
            "@atlaskit/dropdown-menu",
            "https://atlassian.design/components/dropdown-menu/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "dropdown-menu"

        val example = createElementNullSafe {
            // region:dropdown-menu
            DropdownMenu {
                attrs.trigger = "Dropdown"

                DropdownItemCheckbox { +"Dropdown Checkbox Item" }
                DropdownItemGroup {
                    attrs.title = "Group"

                    DropdownItem { +"First dropdown Item" }
                    DropdownItem { +"First dropdown Item" }
                }
            }
            // endregion:dropdown-menu
        }

        examples = listOfNotNull(example)
    }
}
