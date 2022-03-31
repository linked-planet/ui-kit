package com.linkedplanet.uikit.wrapper.atlaskit.table

import org.w3c.dom.events.MouseEvent
import react.ReactElement

data class DynamicTableHead(
    val cells: Array<HeaderCell>
)

data class HeaderCell(
    val key: String,
    val content: String,
    val isSortable: Boolean = false
)

data class DynamicTableRow(
    val key: String,
    val cells: Array<RowCell>,
    val onClick: (MouseEvent) -> Unit = {},
    val isHighlighted: Boolean = false
)

abstract class RowCell

data class StringRowCell(
    val content: String
): RowCell()

data class ElementRowCell(
    val content: ReactElement
): RowCell()

