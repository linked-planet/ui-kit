package com.linkedplanet.uikit.atlaskit.inlineedit

import com.linkedplanet.uikit.atlaskit.textfield.Textfield
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div

interface InlineTextfieldCProps : Props {
    var label: String
    var value: String
    var onSave: (dynamic) -> Unit
    var disabled: Boolean
}

interface InlineTextfieldCState : State {
    var value: String?
}

fun RBuilder.inlineTextfieldComponent(handler: InlineTextfieldCProps.() -> Unit) =
    child(InlineTextfield::class) {
        this.attrs(handler)
    }

class InlineTextfield(CProps: InlineTextfieldCProps) : RComponent<InlineTextfieldCProps, InlineTextfieldCState>(CProps) {

    override fun componentWillReceiveProps(nextCProps: InlineTextfieldCProps) {
        setState {
            value = nextCProps.value
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
                        console.log("update: '$value'")
                        props.onSave(state.value)
                    }
                }
            }
        }
    }
}
