@file:JsModule("@atlaskit/popup")

package com.linkedplanet.uikit.wrapper.atlaskit.popup

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import react.*

@JsName("Popup")
external val Popup: ComponentClass<PopupProps>

external interface PopupProps : Props {

    /**
     * Used to either show or hide the popup. When set to false popup will not render anything to the DOM.
     */
    var isOpen: Boolean

    /**
     * Render props used to anchor the popup to your content. Make this an interactive element, such as an
     * @atlaskit/button component.
     */
    var trigger: (Props) -> ReactElement

    /**
     * Render props for content that is displayed inside the popup.
     */
    var content: (Props) -> ReactElement

    /**
     * Id that is assigned to the popup container element.
     */
    var id: String

    /**
     * Distance the popup should be offset from the reference in the format of [along, away] (units in px). Defaults to
     * [0, 8] - which means the popup will be 8px away from the edge of the reference specified by the placement prop.
     */
    var offset: String

    /**
     * Placement of where the popup should be displayed relative to the trigger element. Defaults to "auto".
     */
    var placement: String

    /**
     * Allows the Popup to be placed on the opposite side of its trigger if it does not fit in the viewport. Defaults to
     * true.
     */
    var shouldFlip: Boolean

    /**
     * Handler that is called when the popup wants to close itself. Generally this will be either when clicking away
     * from the popup or pressing the escape key. You'll want to use this to set open state accordingly and then pump it
     * back into the isOpen prop.
     */
    var onClose: () -> Unit

    /**
     * The element that is shown when isOpen prop is true. The result of the content prop will be placed as children
     * here. Defaults to an element with an elevation of e200 with no padding.
     */
    var popupComponent: () -> ReactElement

    /**
     * Z-index that the popup should be displayed in. This is passed to the portal component. Defaults to layers.layer()
     * from @atlaskit/theme.
     */
    var zIndex: Int

    /**
     * Controls whether the popup takes focus when opening. This changes the popupComponent component tabIndex to null.
     * Defaults to true.
     */
    var autoFocus: Boolean
}
