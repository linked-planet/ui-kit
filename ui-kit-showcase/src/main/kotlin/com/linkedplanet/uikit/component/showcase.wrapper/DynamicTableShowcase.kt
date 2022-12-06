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
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.table.*
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc

val DynamicTableShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Dynamic table"
        packages = Package(
            "@atlaskit/dynamic-table",
            "https://atlassian.design/components/dynamic-table/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "table"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:table
                DynamicTable {
                    attrs.caption = createElementNullSafe { +"" }
                    attrs.head = DynamicTableHead(
                        arrayOf(
                            StringHeaderCell("first", "First col", isSortable = true),
                            StringHeaderCell("second", "Second col", isSortable = true),
                            ElementHeaderCell("third", createElementNullSafe { span { +"Action col" } })
                        )
                    )
                    attrs.rowsPerPage = 3
                    attrs.rows = arrayOf(
                        DynamicTableRow(
                            "1stRow",
                            arrayOf(
                                StringRowCell("1-1"),
                                StringRowCell("1-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        ),
                        DynamicTableRow(
                            "2ndRow",
                            arrayOf(
                                StringRowCell("2-1"),
                                StringRowCell("2-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        ),
                        DynamicTableRow(
                            "3rdRow",
                            arrayOf(
                                StringRowCell("3-1"),
                                StringRowCell("3-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        ),
                        DynamicTableRow(
                            "4thRow",
                            arrayOf(
                                StringRowCell("4-1"),
                                StringRowCell("4-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        ),
                        DynamicTableRow(
                            "5thRow",
                            arrayOf(
                                StringRowCell("5-1"),
                                StringRowCell("5-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        ),
                        DynamicTableRow(
                            "6thRow",
                            arrayOf(
                                StringRowCell("6-1"),
                                StringRowCell("6-2"),
                                ElementRowCell(createElementNullSafe {
                                    Button {
                                        +"Delete"
                                        attrs.appearance = "primary"
                                    }
                                })
                            )
                        )
                    )
                    attrs.onSort = {
                        console.log(it)
                    }
                    attrs.onSetPage = {
                        console.log(it)
                    }
                }
                // endregion:table
            }
        }

        examples = listOfNotNull(example)
    }
}
