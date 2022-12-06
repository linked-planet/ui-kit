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
import com.linkedplanet.uikit.wrapper.atlaskit.select.*
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc

val SelectShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Select"
        packages =
            Package("@atlaskit/select", "https://atlassian.design/components/select/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "select"

        // region:select
        val example1 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                Select {
                    attrs.inputId = "select-1"
                    attrs.options = arrayOf(
                        SelectOption("First option", "first"),
                        SelectOption("Second option", "second")
                    )
                }
            }
        }

        val example2 = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                SelectGroup {
                    attrs.inputId = "select-2"
                    attrs.options = arrayOf(
                        GroupedSelectOptions("First group", arrayOf(SelectOption("First option", "first"))),
                        GroupedSelectOptions(
                            "Second group",
                            arrayOf(SelectOption("Second option", "second"))
                        )
                    )
                }
            }
        }
        // endregion:select

        examples = listOfNotNull(example1, example2)
    }
}
