package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.lozenge.Lozenge
import react.fc

val LozengeShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Lozenge"
        packages = Package(
            "@atlaskit/lozenge",
            "https://atlassian.design/components/lozenge/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "lozenge"

        // region:lozenge
        val example1 = createElementNullSafe {
            Lozenge {
                +"First lozenge"
            }
        }

        val example2 = createElementNullSafe {
            Lozenge {
                +"Colored lozenge"
                attrs.appearance = "new"
            }
        }

        val example3 = createElementNullSafe {
            Lozenge {
                +"Colored bold lozenge"
                attrs.appearance = "success"
                attrs.isBold = true
            }
        }

        val example4 = createElementNullSafe {
            Lozenge {
                +"Colored non-bold lozenge"
                attrs.appearance = "success"
                attrs.isBold = false
            }
        }
        // endregion:lozenge

        examples = listOfNotNull(example1, example2, example3, example4)
    }
}
