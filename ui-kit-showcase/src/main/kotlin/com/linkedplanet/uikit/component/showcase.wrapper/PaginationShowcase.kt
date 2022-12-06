/**
 * Copyright 2022 linked-planet GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
