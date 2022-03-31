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
@file:JsModule("@atlaskit/dropdown-menu")

package com.linkedplanet.uikit.wrapper.atlaskit.dropdownmenu

import org.w3c.dom.events.Event
import react.ComponentClass
import react.Props

@JsName("default")
external val DropdownMenu: ComponentClass<DropdownMenuProps>

/**
 * A dropdown menu displays a list of actions or options to a user.
 */
external interface DropdownMenuProps : Props {

    /**
     * Called when the menu should be open/closed. Received an object with isOpen CState.
     */
    var onOpenChange: () -> Unit

    /**
     * Controls the appearance of the menu.
     *
     * Default menu has scroll after its height exceeds the pre-defined amount.
     *
     * Tall menu has no scroll until the height exceeds the height of the viewport.
     *
     * One of <"default", "tall">
     */
    var appearance: String

    /**
     * If true, a Spinner is rendered instead of the items
     */
    var isLoading: Boolean

    /**
     * Controls the open CState of the dropdown.
     */
    var isOpen: Boolean

    /**
     * Position of the menu.
     * One of
     * - "auto-start",
     * - "auto",
     * - "auto-end",
     * - "top-start",
     * - "top",
     * - "top-end",
     * - "right-start",
     * - "right",
     * - "right-end",
     * - "bottom-end",
     * - "bottom",
     * - "bottom-start",
     * - "left-end",
     * - "left",
     * - "left-start"
     */
    var placement: String

    /**
     * Allows the dropdown menu to be placed on the opposite side of its trigger if it does not fit in the viewport.
     */
    var shouldFlip: Boolean

    /**
     * Content which will trigger the dropdown menu to open and close.
     *
     * Use with triggerType to easily get a button trigger.
     *
     * One of <react.ReactNode, string>
     */
    var trigger: dynamic
}

/**
 * All items need to be wrapped in a group, there are three available for you to use depending what children it has.
 */
@JsName("DropdownItemGroup")
external val DropdownItemGroup: ComponentClass<DropdownItemGroupProps>

external interface DropdownItemGroupProps : Props {

    /**
     * Optional heading text to be shown above the items.
     */
    var title: String
}

/**
 * When wanting to present a user with groups that have a multiple selections.
 *
 * Every item should be inside a dropdown item group checkbox.
 */
@JsName("DropdownItemCheckbox")
external val DropdownItemCheckbox: ComponentClass<DropdownItemCheckboxProps>

external interface DropdownItemCheckboxProps : Props {

    /**
     * Unique identifier for the item, so that selection CState can be tracked when the dropdown is opened/closed.
     */
    var id: String

    /**
     * Set at mount to make the item appear checked.
     *
     * The user may interact with the item after mount.
     *
     * See isSelected if you want to control the item CState manually.
     */
    var defaultSelected: Boolean

    /**
     * Causes the item to appear visually checked.
     *
     * Can be set at mount time, and updated after mount.
     *
     * Changing the value will not cause onClick to be called.
     */
    var isSelected: Boolean

    /**
     * Event that is triggered when the checkbox is clicked.
     */
    var onClick: (Event) -> Unit

}

@JsName("DropdownItem")
external val DropdownItem: ComponentClass<DropdownItemProps>

external interface DropdownItemProps : Props {

    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly
     * increasing the height of the item.
     */
    var description: String

    /**
     * Makes the element appear disabled as well as removing interactivity.
     */
    var isDisabled: Boolean

    /**
     * Makes the element appear selected.
     */
    var isSelected: Boolean

    /**
     * Event that is triggered when the element is clicked.
     */
    var onClick: (Event) -> Unit

}
