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
@file:JsModule("@atlaskit/page-layout")

package com.linkedplanet.uikit.wrapper.atlaskit.pagelayout

import react.*

@JsName("PageLayout")
external val PageLayout: ComponentClass<PageLayoutProps>

external interface PageLayoutProps : Props {

    /**
     * The id of the dom element.
     */
    var id: String

    /**
     * React children.
     */
    var children: ReactNode
}

@JsName("Banner")
external val Banner: ComponentClass<BannerProps>

external interface BannerProps : Props {

    /**
     * HTML id attribute. It also used as a target for skip links to land on. If missing, skip link for that Slot will
     * not be generated.
     */
    var id: String

    /**
     * React Children.
     */
    var children: ReactNode

    /**
     * Height of the element.
     */
    var height: Int
}

@JsName("Main")
external val Main: ComponentClass<MainProps>

external interface MainProps : Props {

    /**
     * HTML id attribute. It also used as a target for skip links to land on. If missing, skip link for that Slot will
     * not be generated
     */
    var id: String

    /**
     * React Children.
     */
    var children: ReactNode

    /**
     * Width of the element.
     */
    var width: Int
}

@JsName("Content")
external val Content: ComponentClass<ContentProps>

external interface ContentProps : Props {

    /**
     * HTML id attribute. It also used as a target for skip links to land on. If missing, skip link for that Slot will
     * not be generated.
     */
    var id: String

    /**
     * React children.
     */
    var children: ReactNode

    /**
     * Width of the element.
     */
    var width: Int
}

@JsName("RightSidebar")
external val RightSidebar: ComponentClass<RightSidebarProps>

external interface RightSidebarProps : Props {

    /**
     * HTML id attribute.
     */
    var id: String

    /**
     * React children.
     */
    var children: ReactNode

    /**
     * Width of the element.
     */
    var width: Int

    /**
     * Sets positon to fixed.
     */
    var isFixed: Boolean
}

@JsName("LeftSidebar")
external val LeftSidebar: ComponentClass<LeftSidebaProps>

external interface LeftSidebaProps : Props {

    /**
     * HTML id attribute. It also used as a target for skip links to land on. If missing, skip link for that Slot will
     * not be generated
     */
    var id: String

    /**
     * React children.
     */
    var children: ReactNode

    /**
     * Width of the element.
     */
    var width: Int

    /**
     * Sets positon to fixed.
     */
    var isFixed: Boolean

    /**
     * Called when left-sidebar is collapsed.
     */
    var onCollapse: () -> Unit

    /**
     * Called when left-sidebar is expanded.
     */
    var onExpand: () -> Unit

    /**
     * Called when left-sidebar resize starts using mouse or touch.
     */
    var onResizeStart: () -> Unit

    /**
     * Called when left-sidebar resize ends using mouse or touch.
     */
    var onResizeEnd: () -> Unit

    /**
     * Called after flyout delay when left-sidebar is collapsed and mouse enters the area.
     */
    var onFlyoutExpand: () -> Unit
}

@JsName("TopNavigation")
external val TopNavigation: ComponentClass<TopNavigationProps>

external interface TopNavigationProps : Props {

    /**
     * HTML id attribute.
     */
    var id: String

    /**
     * React Children.
     */
    var children: ReactNode

    /**
     * Height of the element.
     */
    var height: Int

    /**
     * Sets positon to fixed.
     */
    var isFixed: Boolean
}
