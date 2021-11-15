@file:JsModule("@atlaskit/side-navigation")

package com.linkedplanet.uikit.atlaskit.sidenavigation

import org.w3c.dom.events.Event
import react.*

@JsName("SideNavigation")
external val SideNavigation: RClass<SideNavigationProps>

external interface SideNavigationProps : RProps

@JsName("NavigationHeader")
external val NavigationHeader: RClass<NavigationHeaderProps>

external interface NavigationHeaderProps : RProps

@JsName("Header")
external val Header: RClass<HeaderProps>

external interface HeaderProps : RProps {
    var description: String
}

@JsName("NavigationContent")
external val NavigationContent: RClass<NavigationContentProps>

external interface NavigationContentProps : RProps

@JsName("NavigationFooter")
external val NavigationFooter: RClass<NavigationFooterProps>

external interface NavigationFooterProps : RProps

@JsName("Footer")
external val Footer: RClass<FooterProps>

external interface FooterProps : RProps

@JsName("HeadingItem")
external val HeadingItem: RClass<HeadingItemProps>

external interface HeadingItemProps : RProps

@JsName("Section")
external val Section: RClass<SectionProps>

external interface SectionProps : RProps {
    var title: String
}

@JsName("NestableNavigationContent")
external val NestableNavigationContent: RClass<NestableNavigationContentProps>

external interface NestableNavigationContentProps : RProps

@JsName("ButtonItem")
external val ButtonItem: RClass<ButtonItemProps>

external interface ButtonItemProps : RProps {
    var iconBefore: ReactElement
    var iconAfter: ReactElement
    var onClick: (Event) -> Unit
}

@JsName("NestingItem")
external val NestingItem: RClass<NestingItemProps>

external interface NestingItemProps : RProps {
    var id: String
    var title: String
    var iconBefore: ReactElement
    var iconAfter: ReactElement
    var onClick: (Event) -> Unit
}