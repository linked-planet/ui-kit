@file:JsModule("@atlaskit/dynamic-table")

package com.linkedplanet.uikit.atlaskit.table

import react.ComponentClass
import react.Props

@JsName("default")
external val DynamicTable: ComponentClass<DynamicTableCProps>

external interface DynamicTableCProps : Props {

    /**
     * Sets the caption (Node or String)
     */
    var caption: dynamic

    /**
     * Sets the head
     */
    var head: DynamicTableHead

    var rows: Array<DynamicTableRow>

    var defaultSortKey: String

    var defaultSortOrder: String

    var highlightedRowIndex: Int

    var testId: String

    var onSort: () -> Unit
    var onSetPage: () -> Unit

}
