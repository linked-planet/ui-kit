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
@file:JsModule("@atlaskit/side-navigation")

package com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation

import GoBackItemRenderer
import org.w3c.dom.events.Event
import react.*

@JsName("SideNavigation")
external val SideNavigation: ComponentClass<SideNavigationProps>

external interface SideNavigationProps : Props

@JsName("NavigationHeader")
external val NavigationHeader: ComponentClass<NavigationHeaderProps>

external interface NavigationHeaderProps : Props

@JsName("Header")
external val Header: ComponentClass<HeaderProps>

external interface HeaderProps : Props {
    var description: String
}

@JsName("NavigationContent")
external val NavigationContent: ComponentClass<NavigationContentProps>

external interface NavigationContentProps : Props

@JsName("NavigationFooter")
external val NavigationFooter: ComponentClass<NavigationFooterProps>

external interface NavigationFooterProps : Props

@JsName("Footer")
external val Footer: ComponentClass<FooterProps>

external interface FooterProps : Props

@JsName("HeadingItem")
external val HeadingItem: ComponentClass<HeadingItemProps>

external interface HeadingItemProps : Props

@JsName("Section")
external val Section: ComponentClass<SectionProps>

external interface SectionProps : Props {
    var title: String
}

@JsName("NestableNavigationContent")
external val NestableNavigationContent: ComponentClass<NestableNavigationContentProps>

external interface NestableNavigationContentProps : Props

@JsName("NestingItemOverrides")
external interface NestingItemOverrides {
    var GoBackItem: GoBackItemRenderer
}

@JsName("GoBackItem")
external val GoBackItem: ComponentClass<ButtonItemProps>

@JsName("ButtonItem")
external val ButtonItem: ComponentClass<ButtonItemProps>

external interface ButtonItemProps : Props {
    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly
     * increasing the height of the item.
     */
    var description: String

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
     * Makes the element appear selected.
     */
    var isSelected: Boolean

    /**
     * Makes the element appear disabled as well as removing interactivity.
     */
    var isDisabled: Boolean
}

@JsName("NestingItem")
external val NestingItem: ComponentClass<NestingItemProps>

external interface NestingItemProps : Props {
    var id: String
    var title: String

    /**
     * Description of the item. This will render smaller text below the primary text of the item as well as slightly
     * increasing the height of the item.
     */
    var description: String
    var iconBefore: ReactNode
    var iconAfter: ReactNode
    var onClick: (Event) -> Unit
    var isSelected: Boolean
    var isDisabled: Boolean

    /**
     * Custom overrides to change the GoBackItem
     */
    var overrides: NestingItemOverrides?
}

/**
 * A custom item can be used to a
 *  - Provide a link to an external page
 *  - Contain a custom functional component with arbitrary content
 *  Not wrapping custom components in CustomItem leads to reappearing components in nested menus.
 *
 */
@JsName("CustomItem")
external val CustomItem: ComponentClass<CustomItemProps>

external interface CustomItemProps : Props {
    var id: String

    var component: dynamic
}