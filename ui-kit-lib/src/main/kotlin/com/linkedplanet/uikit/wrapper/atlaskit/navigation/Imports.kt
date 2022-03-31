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
@file:JsModule("@atlaskit/atlassian-navigation")

package com.linkedplanet.uikit.wrapper.atlaskit.navigation

import com.linkedplanet.uikit.wrapper.atlaskit.button.ButtonProps
import org.w3c.dom.events.MouseEvent
import react.*

@JsName("AtlassianNavigation")
external val AtlassianNavigation: ComponentClass<AtlassianNavigationProps>

external interface AtlassianNavigationProps : Props {

    var primaryItems: Array<ReactNode>

    var renderProductHome: () -> ReactElement

    var renderProfile: () -> ReactElement

}

@JsName("CustomProductHome")
external val CustomProductHome: ComponentClass<CustomProductHomeProps>

external interface CustomProductHomeProps : Props {

    var iconUrl: String

    var logoUrl: String

    var siteTitle: String?

}

@JsName("PrimaryButton")
external val PrimaryButton: ComponentClass<PrimaryButtonProps>

external interface PrimaryButtonProps : Props {

    var isHighlighted: Boolean

    var testId: String

    var tooltip: ReactElement

    var onClick: (MouseEvent) -> Unit

}

@JsName("Profile")
external val Profile: ComponentClass<ProfileProps>

external interface ProfileProps : ButtonProps {

    var icon: dynamic

}
