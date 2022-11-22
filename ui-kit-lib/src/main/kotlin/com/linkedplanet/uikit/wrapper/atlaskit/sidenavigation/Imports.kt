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
import react.ComponentClass
import react.Props
import react.ReactNode

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

external interface NestableNavigationContentProps : Props {
    /**
     * Array of the initial stack you want to show. Useful when wanting to set the initial nested view but not wanting to opt into controlled state. Make sure to have all intermediate navigation pages line up.
     */
    var initialStack: Array<String>

    /**
     * Enables you to control the stack of navigation views you want to show. Do not jump between controlled and uncontrolled else undefined behaviour will occur. This means either using initialStack OR stack but not both. Make sure your stack array has a stable reference and does not change between renders.
     */
    var stack: Array<String>
}

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
 *   Sometimes you'll want to use components that aren't supplied by this library,
 *   and that's great!
 *   However, you'll need to know that when you do if you don't opt into our "should I render" hook -
 *   your element will be rendered on _every_ nested view,
 *   which may or may not be what you want to happen.
 *
 *   When writing a leaf node component, you'll want to conditionally return `null` (if shouldRender is false)
 *   When writing a wrapping component, you'll want to conditionally return `children` (if shouldRender is false)
 *
 */
@JsName("useShouldNestedElementRender")
external val useShouldNestedElementRender: () -> ShouldRender

external interface ShouldRender {
    val shouldRender: Boolean
}

/**
 * Useful when wanting to create a item using a your own component that inherits the look and feel of a menu item.
 * Use cases could include using your own router link component for example.
 *
 * The original component passes on props. The wrapper does not support this at this time.
 */
@JsName("CustomItem")
external val CustomItem: ComponentClass<CustomItemProps>

external interface CustomItemProps : Props {
    var id: String

    var component: dynamic
}