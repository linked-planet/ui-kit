package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.popup.Popup
import csstype.Padding
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc
import react.useState

val PopupShowcase = fc<ShowcaseProps> { props ->
    val (isPopupActive, setIsPopupActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Popup"
        packages =
            Package("@atlaskit/popup", "https://atlassian.design/components/popup/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "popup"

        val example = createElementNullSafe {
            // region:popup
            Popup {
                attrs.isOpen = isPopupActive
                attrs.placement = "top"
                attrs.onClose = {
                    setIsPopupActive(false)
                }
                attrs.content = {
                    createElementNullSafe {
                        div {
                            attrs.css {
                                padding = Padding(15.px, 15.px)
                            }
                            span {
                                +"Popup content"
                            }
                        }
                    }
                }
                attrs.trigger = {
                    createElementNullSafe {
                        Button {
                            +"Open popup"
                            attrs.onClick = {
                                setIsPopupActive(true)
                            }
                            attrs.isSelected = isPopupActive
                        }
                    }
                }
            }
            // endregion:popup
        }

        examples = listOfNotNull(example)
    }
}
