/**
 * Copyright 2022 linked-planet GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:JsModule("@atlaskit/modal-dialog")

package com.linkedplanet.uikit.wrapper.atlaskit.modal

import org.w3c.dom.events.Event
import react.*

@JsName("default")
external val Modal: ComponentClass<ModalProps>

external interface ModalProps : Props {


    /**
     * Focus is moved to the first interactive element inside the modal dialog when true. Pass an element ref to focus
     * on a specific element.
     */
    var autoFocus: Boolean

    /**
     * Function that will be called to initiate the exit transition.
     */
    var onClose: (Event) -> Unit

    /**
     * Callback function called when the modal dialog has finished opening.
     */
    var onOpenComplete: (ReactNode) -> Unit

    /**
     * Boolean indicating if clicking the overlay should close the modal.
     */
    var shouldCloseOnOverlayClick: Boolean

    /**
     * Boolean indicating if pressing the esc key should close the modal.
     */
    var shouldCloseOnEscapePress: Boolean

    /**
     * Height of the modal dialog. When unset the modal dialog will grow to fill the viewport and then start overflowing
     * its contents.
     */
    var height: String

    /**
     * Width of the modal dialog. The recommended way to specify modal width is using named size options.
     *
     * Might be absolute or one of
     * - "small",
     * - "medium",
     * - "large",
     * - "x-large"
     */
    var width: String
}

@JsName("ModalTransition")
external val ModalTransition: ComponentClass<Props>

@JsName("ModalHeader")
external val ModalHeader: ComponentClass<ModalHeaderProps>

external interface ModalHeaderProps : Props {

    /**
     * Appearance of the modal that changes the color of the primary action and adds an icon to the title.
     * One of
     * - "danger",
     * - "warning"
     */
    var appearance: String

    /**
     * When true will allow the title to span multiple lines. Defaults to true.
     */
    var isMultiline: Boolean
}

@JsName("ModalTitle")
external val ModalTitle: ComponentClass<Props>


@JsName("ModalFooter")
external val ModalFooter: ComponentClass<Props>
