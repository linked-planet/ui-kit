package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.atlaskit.navigation.*
import com.linkedplanet.uikit.atlaskit.pagelayout.TopNavigation
import kotlinext.js.jsObject
import react.*
import react.dom.*

external interface ShowcaseTopNavigationProps : Props

val ShowcaseTopNavigation = fc<ShowcaseTopNavigationProps> { props ->

    TopNavigation {
        attrs.isFixed = true

        AtlassianNavigation {

            // Product Home
            attrs.renderProductHome = {
                createElement(CustomProductHome, jsObject {
                    iconUrl = "images/logo.png"
                    logoUrl = "images/logo.png"
                    siteTitle = "UI-KIT"
                })
            }

            // Menu items
            attrs.primaryItems = arrayOf(
                createElement(PrimaryButton, jsObject { }, createElement { span { +"First menu item" } }),
                createElement(PrimaryButton, jsObject { }, createElement { span { +"Second menu item" } })
            )

            // Profile
            val profileIcon = createElement {
                img {
                    attrs.src =
                        "https://w7.pngwing.com/pngs/7/618/png-transparent-man-illustration-avatar-icon-fashion-men-avatar-face-fashion-girl-heroes.png"
                    attrs.jsStyle {
                        borderRadius = "50%"
                        width = 32
                        height = 32
                    }
                }
            }

            attrs.renderProfile = {
                createElement(Profile, jsObject {
                    icon = profileIcon
                })
            }
        }
    }
}

fun RBuilder.ShowcaseTopNavigation(handler: ShowcaseTopNavigationProps.() -> Unit) =
    child(ShowcaseTopNavigation) { attrs { handler() } }