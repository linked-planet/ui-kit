@file:JsModule("@atlaskit/side-navigation")

package com.linkedplanet.uikit.atlaskit.sidenavigation

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