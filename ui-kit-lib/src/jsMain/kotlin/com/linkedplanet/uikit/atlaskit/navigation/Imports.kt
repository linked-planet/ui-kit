@file:JsModule("@atlaskit/atlassian-navigation")

package com.linkedplanet.uikit.atlaskit.navigation

import com.linkedplanet.uikit.atlaskit.button.ButtonProps
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
