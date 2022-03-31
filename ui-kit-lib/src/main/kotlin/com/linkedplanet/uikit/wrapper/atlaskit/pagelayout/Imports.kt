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

import react.ComponentClass
import react.Props
import react.ReactElement

@JsName("PageLayout")
external val PageLayout: ComponentClass<PageLayoutCProps>

external interface PageLayoutCProps : Props {

    var id: String

    var children: ReactElement
}

@JsName("Banner")
external val Banner: ComponentClass<BanneProps>

external interface BanneProps : Props {

    var id: String

    var children: ReactElement

    var height: Int
}

@JsName("Main")
external val Main: ComponentClass<MainCProps>

external interface MainCProps : Props {

    var id: String

    var children: ReactElement

    var width: Int
}

@JsName("Content")
external val Content: ComponentClass<ContentCProps>

external interface ContentCProps : Props {

    var id: String

    var children: ReactElement

    var width: Int
}

@JsName("RightSidebar")
external val RightSidebar: ComponentClass<RightSidebaProps>

external interface RightSidebaProps : Props {

    var id: String

    var children: ReactElement

    var width: Int

    var isFixed: Boolean


}

@JsName("LeftSidebar")
external val LeftSidebar: ComponentClass<LeftSidebaProps>

external interface LeftSidebaProps : Props {

    var id: String

    var children: ReactElement

    var width: Int

    var isFixed: Boolean

    var onCollapse: () -> Unit

    var onExpand: () -> Unit

    var onResizeStart: () -> Unit

    var onResizeEnd: () -> Unit

    var onFlyoutExpand: () -> Unit

}

@JsName("TopNavigation")
external val TopNavigation: ComponentClass<TopNavigationCProps>

external interface TopNavigationCProps : Props {

    var id: String

    var children: ReactElement

    var height: Int

    var isFixed: Boolean
}
