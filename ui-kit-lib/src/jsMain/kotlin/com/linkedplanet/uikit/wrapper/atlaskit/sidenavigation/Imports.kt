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

@JsName("ButtonItem")
external val ButtonItem: ComponentClass<ButtonItemProps>

external interface ButtonItemProps : Props {
    var iconBefore: ReactElement
    var iconAfter: ReactElement
    var onClick: (Event) -> Unit
}

@JsName("NestingItem")
external val NestingItem: ComponentClass<NestingItemProps>

external interface NestingItemProps : Props {
    var id: String
    var title: String
    var iconBefore: ReactElement
    var iconAfter: ReactElement
    var onClick: (Event) -> Unit
}