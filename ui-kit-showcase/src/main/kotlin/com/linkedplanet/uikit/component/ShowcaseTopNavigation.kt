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
package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.wrapper.atlaskit.navigation.*
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.TopNavigation
import kotlinext.js.jsObject
import kotlinx.browser.window
import react.*
import react.dom.*

external interface ShowcaseTopNavigationProps : Props

val ShowcaseTopNavigation = fc<ShowcaseTopNavigationProps> { _ ->

    TopNavigation {
        attrs.isFixed = true

        AtlassianNavigation {

            // Product Home
            attrs.renderProductHome = {
                createElement(CustomProductHome, jsObject {
                    onClick = { window.location.href = "/#/intro" }
                    iconUrl = "images/logo.png"
                    logoUrl = "images/logo.png"
                    siteTitle = "UI-Kit"
                })
            }

            // Menu items
            attrs.primaryItems = arrayOf(
                createElement(
                    PrimaryButton,
                    jsObject { onClick = { window.location.href = "./#/intro" } },
                    createElement { span { +"Intro" } }
                ),
                createElement(
                    PrimaryButton,
                    jsObject { onClick = { window.location.href = "./#/wrappers" } },
                    createElement { span { +"Wrappers" } }
                ),
                createElement(
                    PrimaryButton,
                    jsObject { onClick = { window.location.href = "./#/utils" } },
                    createElement { span { +"Utils" } }
                )
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