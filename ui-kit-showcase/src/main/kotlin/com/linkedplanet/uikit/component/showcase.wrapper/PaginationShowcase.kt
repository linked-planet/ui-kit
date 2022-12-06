package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.pagination.Pagination
import react.fc
import react.useState

val PaginationShowcase = fc<ShowcaseProps> { props ->
    val (selectedPage, setSelectedPage) = useState(0)

    ShowcaseWrapperItem {
        name = "Pagination"
        packages = Package(
            "@atlaskit/pagination",
            "https://atlassian.design/components/pagination/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "pagination"

        val example = createElementNullSafe {
            // region:pagination
            Pagination {
                attrs.pages = (1..10).toList().toTypedArray()
                attrs.defaultSelectedIndex = 0
                attrs.max = 10
                attrs.selectedIndex = selectedPage
                attrs.onChange = { _, pageLabel, _ ->
                    setSelectedPage(pageLabel - 1)
                }
            }
            // endregion:pagination
        }

        examples = listOfNotNull(example)
    }
}
