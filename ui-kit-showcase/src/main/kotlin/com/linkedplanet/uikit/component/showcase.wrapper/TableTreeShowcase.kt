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
import com.linkedplanet.uikit.wrapper.atlaskit.modal.*
import com.linkedplanet.uikit.wrapper.atlaskit.tabletree.*
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span

val TableTreeShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "TableTree"
        packages = Package("@atlaskit/table-tree", "https://atlassian.design/components/table-tree").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "table_tree"

        @Suppress("NonExternalClassifierExtendingStateOrProps")
        // region:table_tree
        data class BookData(override var key: Key?, val title: String, val numbering: String) : Props

        data class BookTreeItem(
            override val id: String,
            override val content: BookData,
            override val children: Array<TableTreeItem>? = null,
            override val hasChildren: Boolean = children?.isNotEmpty() ?: false,
        ) : TableTreeBasicUseCaseItem<BookData>

        val bookDataTree = arrayOf(
            BookTreeItem(
                "1", BookData("1", "It is lonely at the top.", "1"), children = arrayOf(
                    BookTreeItem(
                        "1.1", BookData("2", "Look at me! I am nested.", "1.1"), children = arrayOf(
                            BookTreeItem("1.1.1", BookData("3", "I am deeply nested.", "1.1.1"))
                        )
                    )
                )
            ),
            BookTreeItem("2", BookData("4", "Kotlin is  fun", "2"))
        )

        val example1 = createElementNullSafe {
            val fcTitle = fc<BookData> { myprops -> span { +myprops.title } }
            val fcNumbering = fc<BookData> { myprops -> span { +myprops.numbering } }

            TableTree {
                attrs.headers = arrayOf("Title", "Numbering")
                attrs.columns = arrayOf(fcTitle, fcNumbering)
                attrs.columnWidths = arrayOf(300, 100)

                attrs.items = bookDataTree
            }
        }

        val example2 = createElementNullSafe {
            TableTree {
                TTHeaders {
                    TTHeader {
                        attrs.width = "400px"
                        attrs.onClick = { _ ->
                            window.alert("OnClick Chapter Title Header")
                        }
                        +"Chapter Title (Click me)"
                    }
                    TTHeader {
                        attrs.width = "100px"
                        +"Numbering"
                    }
                }
                TTRows {
                    attrs.items = bookDataTree
                    attrs.render = { data: BookTreeItem ->
                        createElementNullSafe {
                            TTRow {
                                attrs.itemId = data.content.numbering
                                attrs.items = data.children
                                attrs.hasChildren = data.hasChildren
                                attrs.isDefaultExpanded = false

                                TTCell {
                                    attrs.singleLine = true
                                    div {
                                        attrs.onClick = { _ -> window.alert("onClick:" + data.content.title) }
                                        +data.content.title
                                    }
                                }
                                TTCell {
                                    attrs.singleLine = true
                                    attrs.onClick = { _ -> window.alert("onClick:" + data.content.numbering) }
                                    +data.content.numbering
                                }
                            }
                        }
                    }
                }
            } // of TableTree
        }
        // endregion:table_tree
        examples = listOfNotNull(example1, example2)
    }
}
