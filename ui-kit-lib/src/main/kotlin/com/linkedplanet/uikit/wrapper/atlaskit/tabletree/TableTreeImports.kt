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
@file:JsModule("@atlaskit/table-tree")

package com.linkedplanet.uikit.wrapper.atlaskit.tabletree

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props
import react.ReactNode

@JsName("default")
external val TableTree: ComponentClass<TableTreeProps<dynamic, TableTreeBasicUseCaseItem<dynamic>>>

external interface TableTreeProps<ContentType, TableTreeItemDescendantType : TableTreeBasicUseCaseItem<ContentType>> : Props {

    /**
     * The contents of the table, used when composing Cell, Header, Headers, Row, and Rows components.
     * For basic usage, you can instead specify table contents with the items prop.
     */
    var children: ReactNode

    /**
     * Each column component is used to render the cells in that column.
     * A cell's content value, specified in the data passed to items, is provided as props.
     */
    var columns: Array<dynamic>

    /**
     * The widths of the respective columns in the table.
     */
    var columnWidths: Array<Number>

    /**
     * The header text of the respective columns of the table.
     */
    var headers: Array<String>

    /**
     * The data used to render the table.
     * In addition to these props, any other data can be added,
     * and it will be provided as props when rendering each cell.
     */
    var items: Array<out TableTreeItemDescendantType>
}

@JsName("Headers")
external val TTHeaders: ComponentClass<Props>

@JsName("Header")
external val TTHeader: ComponentClass<TTHeaderProps>

external interface TTHeaderProps : Props {
    /**
     * Width of the header item.
     */
    var width: String

    /**
     * Handler to be called on click.
     */
    var onClick: (Event) -> Unit
}

@JsName("Rows")
external val TTRows: ComponentClass<TTRowsProps<dynamic>>

external interface TTRowsProps<TableTreeItemDescendantType : TableTreeItem> : Props {
    var items: Array<TableTreeItemDescendantType>
    var render: (TableTreeItemDescendantType) -> ReactNode
}


@JsName("Row")
external val TTRow: ComponentClass<TTRowProps<dynamic>>

external interface TTRowProps<TableTreeItemDescendantType : TableTreeItem> : Props {
    /**
     * ID for the row item
     */
    var itemId: String

    /**
     * The data used to render the row and descendants. Pass down from children render prop.
     *
     * In addition to these props, any other data can be added to the object, and it will be provided as props when rendering each cell.
     */
    var items: dynamic

    /**
     * Whether the row has children
     */
    var hasChildren: Boolean

    /**
     * Children contained in the row. Should be one or more Cell components.
     */
    var children: ReactNode

    /**
     * Sets the default expanded state of the row.
     * Default: false
     */
    var isDefaultExpanded: Boolean

    /**
     * Callback called when row expands.
     */
    var onExpand: (TableTreeItemDescendantType) -> Unit

    /**
     * Callback called when row collapses.
     */
    var onCollapse: (TableTreeItemDescendantType) -> Unit

    /**
     * Controls the expanded state of the row programmatically
     * Can not be changed by the user.
     */
    var isExpanded: Boolean

    /**
     * aria-label attached to the expand chevron button
     */
    var expandLabel: String

    /**
     * aria-label attached to the collapse chevron button
     */
    var collapseLabel: String
}

@JsName("Cell")
external val TTCell: ComponentClass<TTCellProps>

external interface TTCellProps : Props {
    /**
     * Whether the cell contents should wrap or display on a single line and be concatenated.
     */
    var singleLine: Boolean?

    /**
     * Indent level for the cell. Each indent level adds 25px to the left padding.
     */
    var indentLevel: Number?

    /**
     * Width of the header item. Takes a string or a number representing the width in pixels.
     */
    var width: String?

    /**
     * Class name to apply to cell.
     */
    var className: String?

    var onClick: (Event) -> Unit
}
