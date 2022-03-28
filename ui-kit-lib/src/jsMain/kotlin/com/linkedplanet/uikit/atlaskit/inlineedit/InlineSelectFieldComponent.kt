package com.linkedplanet.uikit.atlaskit.inlineedit

import com.linkedplanet.uikit.atlaskit.select.Select
import com.linkedplanet.uikit.atlaskit.select.SelectOption
import react.*
import react.dom.div

interface InlineSelectfieldCState : State {
    var value: String?
}

interface InlineSelectfieldCProps : Props {
    var label: String
    var options: List<String>
    var value: String
    var onSave: (dynamic) -> Unit
}

fun RBuilder.inlineSelectfieldComponent(handler: InlineSelectfieldCProps.() -> Unit) =
    child(InlineSelectfield::class) {
        this.attrs(handler)
    }

class InlineSelectfield(CProps: InlineSelectfieldCProps) :
    RComponent<InlineSelectfieldCProps, InlineSelectfieldCState>(CProps) {

    override fun componentWillReceiveProps(nextCProps: InlineSelectfieldCProps) {
        setState {
            value = nextCProps.value
        }
    }

    override fun RBuilder.render() {
        div {
            InlineEdit {
                attrs.defaultValue = props.value
                attrs.label = props.label
                attrs.readView = {
                    div(classes = "inline-edit-read-view") {
                        +props.value
                    }
                }
                attrs.editView = { fieldCProps ->
                    Select {
                        val options = props.options.map { SelectOption(it, it) }.toTypedArray()
                        attrs.autoFocus = true
                        attrs.options = options
                        attrs.value = options.firstOrNull { it.value == state.value }
                        attrs.onChange = {
                            val v = it.value
                            console.log("change CState to $v")
                            console.log(props.options.map { SelectOption(it, it) }.toTypedArray())
                            setState {
                                value = v
                            }
                        }
                    }
                }
                attrs.onConfirm = { value ->
                    props.onSave(state.value)
                }
            }
        }
    }
}
