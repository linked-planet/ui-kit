@file:JsModule("@atlaskit/inline-edit")

package com.linkedplanet.uikit.atlaskit.inlineedit

import react.ComponentClass
import react.Props

@JsName("default")
external val InlineEdit: ComponentClass<InlineEditCProps>

external interface InlineEditCProps : Props {

    var label: String

    var readView: () -> Unit

    var editView: (Props) -> Unit

    var onConfirm: (dynamic) -> Unit

    var defaultValue: dynamic

    var startWithEditViewOpen: Boolean

    var hideActionButtons: Boolean
}
