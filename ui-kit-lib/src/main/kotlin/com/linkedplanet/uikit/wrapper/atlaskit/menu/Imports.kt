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
@file:JsModule("@atlaskit/menu")

package com.linkedplanet.uikit.wrapper.atlaskit.menu

import org.w3c.dom.events.Event
import react.*
import react.dom.events.MouseEventHandler

@JsName("MenuGroup")
external val MenuGroup: ComponentClass<MenuGroupCProps>

external interface MenuGroupCProps : Props {
    /**
     * Children of the menu group, should generally be Section components.
     */
    var children: ReactNode

    /**
     * Used to override the accessibility role for the element.
     */
    var role: String

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String

    /**
     * Handler called when clicking on this element, or any children elements. Useful when needing to stop propagation of child events.
     */
    var onClick: (Event) -> Unit

    /**
     * Useful to constrain the menu group minimum height to a specific value.
     */
    var minHeight: String

    /**
     * Useful to constrain the menu groups height to a specific value. Needs to be set when wanting to have scrollable sections.
     */
    var maxHeight: String

    /**
     *
     * Useful to constrain the menu group minimum width to a specific value.
     */
    var minWidth: String

    /**
     * Useful to constrain the menu group width to a specific value.
     */
    var maxWidth: String
}

@JsName("HeadingItem")
external val HeadingItem: ComponentClass<HeadingItemProps>

external interface HeadingItemProps : Props {

    /**
     * The text of the heading.
     */
    var children: ReactNode

    /**
     * A unique identifier that can be referenced in the labelledby prop of a section to allow screen readers to announce the name of groups.
     */
    var id: String

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String
}

@JsName("Section")
external val Section: ComponentClass<SectionProps>

external interface SectionProps : Props {

    /**
     * Children of the section, should generally be Item or Heading components, but can also be EmptyStates when wanting to render errors.
     */
    var children: ReactNode

    /**
     * Unique identifier for the element.
     */
    var id: String

    /**
     *
     * Enables scrolling within the section. Make sure to set maxHeight on the parent MenuGroup component else it will not work.
     */
    var isScrollable: Boolean

    /**
     * Will render a border at the top of the section.
     */
    var hasSeparator: Boolean

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String

    /**
     * The text passed into the internal HeadingItem. If a title is not provided, the HeadingItem will not be rendered, and this component acts as a regular Section
     */
    var title: String
}

@JsName("LinkItem")
external val LinkItem: ComponentClass<LinkItemCProps>

external interface LinkItemCProps : Props {

    /**
     * Link to another page.
     */
    var href: String

    /**
     * Where to display the linked URL, see anchor information on mdn for more information.
     */
    var target: String

    /**
     *
     * The relationship of the linked URL as space-separated link types. Generally you'll want to set this to "noopener noreferrer" when target is "_blank".
     */
    var rel: String

    /**
     * Used to override the accessibility role for the element.
     */
    var role: String

    /**
     * Element to render before the item text. Generally should be an icon component.
     */
    var iconBefore: ReactNode


    /**
     * Element to render after the item text. Generally should be an icon component.
     */
    var iconAfter: ReactNode

    /**
     * Event that is triggered when the element is clicked.
     */
    var onClick: (Event) -> Unit

    /**
     * Event that is triggered when the element has been pressed.
     */
    var onMouseDown: MouseEventHandler<*>

    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly increasing the height of the item.
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
     * Primary content for the item.
     */
    var children: ReactNode

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String

    /**
     * When true the title of the item will wrap multiple lines if it's long enough.
     */
    var shouldTitleWrap: Boolean

    /**
     * When true the description of the item will wrap multiple lines if it's long enough.
     */
    var shouldDescriptionWrap: Boolean
}

@JsName("ButtonItem")
external val ButtonItem: ComponentClass<ButtonItemCProps>

external interface ButtonItemCProps : Props {

    /**
     * Unique identifier for the element.
     */
    var id: String

    /**
     * Used to override the accessibility role for the element.
     */
    var role: String

    /**
     * A function that overrides the styles of the component. It receives the current styles and state and expects a styles object.
     * This API is deprecated and will be removed in a future release. See DSP-2676 for more information.
     */
    var iconBefore: ReactNode

    /**
     * Element to render before the item text. Generally should be an icon component.
     */
    var iconAfter: ReactNode

    /**
     * Element to render after the item text. Generally should be an icon component.
     */
    var onClick: (Event) -> Unit

    /**
     * Event that is triggered when the element is clicked.
     */
    var onMouseDown: MouseEventHandler<*>

    /**
     * Event that is triggered when the element has been pressed.
     */
    var description: String

    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly increasing the height of the item.
     */
    var isDisabled: Boolean

    /**
     * Makes the element appear disabled as well as removing interactivity.
     */
    var isSelected: Boolean

    /**
     * Makes the element appear selected.
     */
    var children: ReactNode

    /**
     * Primary content for the item.
     */
    var testId: String

    /**
     * When true the title of the item will wrap multiple lines if it's long enough.
     */
    var shouldTitleWrap: Boolean

    /**
     * When true the description of the item will wrap multiple lines if it's long enough.
     */
    var shouldDescriptionWrap: Boolean
}

@JsName("CustomItem")
external val CustomItem: ComponentClass<CustomItemProps>

external interface CustomItemProps : Props {

    /**
     * Element to render before the item text. Generally should be an icon component.
     */
    var iconBefore: ReactNode

    /**
     * Element to render after the item text. Generally should be an icon component.
     */
    var iconAfter: ReactNode

    /**
     * Event that is triggered when the element is clicked.
     */
    var onClick: (Event) -> Unit

    /**
     * Event that is triggered when the element has been pressed.
     */
    var onMouseDown: MouseEventHandler<*>

    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly increasing the height of the item.
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
     * Primary content for the item.
     */
    var children: ReactNode

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String

    /**
     * When true the title of the item will wrap multiple lines if it's long enough.
     */
    var shouldTitleWrap: Boolean

    /**
     * When true the description of the item will wrap multiple lines if it's long enough.
     */
    var shouldDescriptionWrap: Boolean
}
