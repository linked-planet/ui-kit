@file:JsModule("@atlaskit/page-layout")

package com.linkedplanet.uikit.atlaskit.pagelayout

import react.*

@JsName("PageLayout")
external val PageLayout: RClass<PageLayoutProps>

external interface PageLayoutProps : RProps

@JsName("Main")
external val Main: RClass<MainProps>

external interface MainProps : RProps

@JsName("Content")
external val Content: RClass<ContentProps>

external interface ContentProps : RProps

@JsName("LeftSidebar")
external val LeftSidebar: RClass<LeftSidebarProps>

external interface LeftSidebarProps : RProps
