package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.button.ButtonGroup
import com.linkedplanet.uikit.wrapper.atlaskit.icon.CrossIcon
import com.linkedplanet.uikit.wrapper.atlaskit.modal.*
import react.dom.html.ReactHTML.p
import react.fc
import react.useState

val ModalShowcase = fc<ShowcaseProps> { props ->
    val (isModalActive, setIsModalActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Modal"
        packages = Package(
            "@atlaskit/modal-dialog",
            "https://atlassian.design/components/modal-dialog/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "modal"

        val example = createElementNullSafe {
            // region:modal
            Button {
                +"Show modal"

                attrs.onClick = {
                    setIsModalActive(true)
                }
            }

            if (isModalActive) {
                ModalTransition {
                    Modal {
                        attrs.onClose = {
                            setIsModalActive(false)
                        }

                        ModalHeader {
                            ModalTitle {
                                +"Sample Modal"
                            }
                            Button {
                                attrs.appearance = "link"
                                attrs.onClick = {
                                    setIsModalActive(false)
                                }

                                CrossIcon {
                                    attrs.label = "Close popup"
                                    attrs.primaryColor = "#000"
                                }
                            }
                        }

                        ModalBody {
                            p {
                                +"This is the body of the modal."
                            }
                        }

                        ModalFooter {
                            ButtonGroup {
                                Button {
                                    // If not autofocused, the other button is focused with a border!
                                    attrs.autoFocus = true
                                    attrs.appearance = "primary"
                                    attrs.onClick = {
                                        setIsModalActive(false)
                                    }
                                    +"Close"
                                }
                            }
                        }
                    }
                }
            }
            // endregion:modal
        }

        examples = listOfNotNull(example)
    }
}
