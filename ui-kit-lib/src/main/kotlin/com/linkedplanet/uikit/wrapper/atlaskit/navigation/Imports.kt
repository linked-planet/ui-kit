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

    /**
     * Slot for the primary actions. Refer to primary actions docs for more information.
     */
    var primaryItems: Array<ReactNode>

    /**
     * Slot for the product home logo, this will render your product brand. Refer to
     * [product home](atlassian-navigation/docs/product home) docs for more information.
     */
    var renderProductHome: () -> ReactNode

    /**
     * Slot for the profile button. Refer to secondary actions docs for more information.
     */
    var renderProfile: () -> ReactNode
}

@JsName("CustomProductHome")
external val CustomProductHome: ComponentClass<CustomProductHomeProps>

external interface CustomProductHomeProps : Props {

    /**
     * Url for the icon that is displayed on small viewports.
     */
    var iconUrl: String

    /**
     * Url for the icon that is displayed on large viewports.
     */
    var logoUrl: String

    /**
     * Name of the site that appears next to the logo.
     */
    var siteTitle: String?

    /**
     * Optional onClick handler.
     */
    var onClick: (MouseEvent) -> Unit

}

@JsName("PrimaryButton")
external val PrimaryButton: ComponentClass<PrimaryButtonProps>

external interface PrimaryButtonProps : Props {

    /**
     * Will set the appearance of the button to look highlighted.
     */
    var isHighlighted: Boolean

    /**
     * A testId prop is provided for specified elements, which is a unique string that appears as a data attribute
     * data-testid in the rendered code, serving as a hook for automated tests.
     */
    var testId: String

    /**
     * Optional text to show when the button is focused or hovered.
     */
    var tooltip: ReactNode

    /**
     * On click handler. See @atlaskit/analytics-next for analyticsEvent type information.
     */
    var onClick: (MouseEvent) -> Unit
}

@JsName("Profile")
external val Profile: ComponentClass<ProfileProps>

external interface ProfileProps : ButtonProps {

    /**
     * Icon for the button.
     */
    var icon: dynamic
}
