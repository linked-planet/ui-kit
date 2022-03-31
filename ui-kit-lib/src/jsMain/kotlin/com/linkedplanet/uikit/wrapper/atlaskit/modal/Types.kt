package com.linkedplanet.uikit.wrapper.atlaskit.modal

import react.Props

data class ModalAction(val text: String, val onClick: () -> Unit)

data class ModalComponents(
    var Header: dynamic = undefined,
    var Footer: dynamic = undefined,
    var Body: dynamic = undefined,
    var Container: dynamic = undefined
)

interface FooteProps : Props {
    var onClose: () -> Unit
}
