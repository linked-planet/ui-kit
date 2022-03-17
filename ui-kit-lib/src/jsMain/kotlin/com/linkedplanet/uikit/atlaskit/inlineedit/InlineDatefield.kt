package com.linkedplanet.uikit.atlaskit.inlineedit

import com.linkedplanet.uikit.atlaskit.textfield.Textfield
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div

interface InlineDatefieldCProps : Props {
    var label: String
    var value: String
    var onSave: (dynamic) -> Unit
    var disabled: Boolean
}

interface InlineDatefieldCState : State {
    var value: String?
}

fun RBuilder.inlineDatefieldComponent(handler: InlineDatefieldCProps.() -> Unit) =
    child(InlineDatefield::class) {
        this.attrs(handler)
    }

class InlineDatefield(CProps: InlineDatefieldCProps) : RComponent<InlineDatefieldCProps, InlineDatefieldCState>(CProps) {

    override fun componentWillReceiveProps(nextCProps: InlineDatefieldCProps) {
        setState {
            value = nextCProps.value
        }
    }

    override fun componentWillUpdate(nextCProps: InlineDatefieldCProps, nextCState: InlineDatefieldCState) {
        if(state.value == null) {
            nextCState.value = nextCProps.value
        }
    }

    override fun RBuilder.render() {
        div {
            InlineEdit {
                attrs.defaultValue = props.value
                attrs.label = props.label
                attrs.hideActionButtons = props.disabled
                attrs.editView = { fieldCProps ->
                    if (props.disabled) {
                        div(classes = "inline-edit-read-view") {
                            +props.value
                        }
                    } else {
                        Textfield {
                            attrs.type = "date"
                            attrs.autoFocus = true
                            attrs.defaultValue = props.value
                            attrs.onChange = {
                                val target = it.target as HTMLInputElement
                                setState {
                                    value = target.value
                                }
                            }
                        }
                    }
                }
                attrs.readView = {
                    div(classes = "inline-edit-read-view") {
                        +props.value
                    }
                }
                attrs.onConfirm = { value ->
                    if (state.value != props.value) {
                        props.onSave(state.value)
                    }
                }
            }
        }
    }
}
