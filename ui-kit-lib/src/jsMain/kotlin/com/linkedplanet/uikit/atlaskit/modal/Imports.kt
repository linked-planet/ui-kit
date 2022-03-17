@file:JsModule("@atlaskit/modal-dialog")

package com.linkedplanet.uikit.atlaskit.modal

import react.ComponentClass
import react.Props

@JsName("default")
external val Modal: ComponentClass<ModalCProps>

external interface ModalCProps : Props {
    /**
     * Buttons to render in the footer
     */
    var actions: Array<ModalAction>

    /**
     * Appearance of the primary action. Also adds an icon to the heading, if provided.
     * One of ["danger", "warning"]
     */
    var appearance: String

    /**
     * Boolean indicating content should be rendered on a transparent background.
     */
    var isChromeless: Boolean

    /**
     * Boolean indicating whether to focus on the first tabbable element inside the focus lock.
     */
    var autoFocus: Boolean

    /**
     * The modal title; rendered in the header.
     */
    var heading: String

    /**
     * Makes heading multiline. If false and heading is longer than one line overflow will be not displayed.
     */
    var isHeadingMultiline: Boolean

    /**
     * Function that will be called to initiate the exit transition.
     */
    var onClose: () -> Unit

    /**
     * Boolean indicating if clicking the overlay should close the modal.
     */
    var shouldCloseOnOverlayClick: Boolean

    /**
     * Boolean indicating if pressing the esc key should close the modal.
     */
    var shouldCloseOnEscapePress: Boolean

    /**
     * Object containing header, footer, body and container components.
     * Components here will be used instead of the defaults.
     */
    var components: ModalComponents

    var height: String
    var width: String
}

@JsName("ModalTransition")
external val ModalTransition: ComponentClass<Props>

@JsName("ModalFooter")
external val ModalFooter: ComponentClass<Props>
