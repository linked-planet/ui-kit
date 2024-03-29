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
@file:JsModule("@atlaskit/dynamic-table")

package com.linkedplanet.uikit.wrapper.atlaskit.table

import react.*

@JsName("default")
external val DynamicTable: ComponentClass<DynamicTableProps>

@JsName("DynamicTableStateless")
external val DynamicTableStateless: ComponentClass<DynamicTableProps>

external interface DynamicTableProps : Props {

    /**
     * Caption for the table styled as a heading.
     */
    var caption: ReactNode

    /**
     * Cells to be placed in the head of the table. Each element in the head creates a new column.
     */
    var head: DynamicTableHead

    /**
     * Cells to be placed in the head of the table.
     */
    var rows: Array<DynamicTableRow>

    /**
     * Default column sort key that the rows should be sorted by. Corresponds to the key's defined in the head prop.
     */
    var defaultSortKey: String

    /**
     * Default column sort order used when initially rendering. Defaults to "ASC".
     */
    var defaultSortOrder: String

    /**
     * Will highlight a row(s) of the table.
     */
    var highlightedRowIndex: Int

    /**
     * Callback fired when a column heading has been sorted, useful when wanting to control dynamic table.
     */
    var onSort: (options: SortOptions) -> Unit

    /**
     * Callback fired when the table page has changed, useful when wanting to control dynamic table.
     */
    var onSetPage: (pageNumber: Int) -> Unit

    /**
     * Controls how many rows should be diplayed per page.
     */
    var rowsPerPage: Int

    /**
     * Page the table should show. Useful when wanting to control dynamic table.
     */
    var page: Int

    /**
     * Column key that the rows should be sorted by. Corresponds to the key's defined in the head prop. Useful when wanting to control dynamic table.
     */
    var sortKey: String

    /**
     * Column sort order. Useful when wanting to control dynamic table. One of
     * - "ASC",
     * - "DESC"
     */
    var sortOrder: String
}

external interface SortOptions {
    var item: HeaderCell
    var key: String
    var sortOrder: String
}
