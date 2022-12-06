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

import com.linkedplanet.uikit.util.createElementNullSafe
import com.linkedplanet.uikit.util.createSpan
import com.linkedplanet.uikit.wrapper.atlaskit.navigation.*
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.TopNavigation
import csstype.pct
import csstype.px
import emotion.react.css
import js.core.jso
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.img

external interface ShowcaseTopNavigationProps : Props

val ShowcaseTopNavigation = fc<ShowcaseTopNavigationProps> { _ ->

    TopNavigation {
        attrs.isFixed = true

        AtlassianNavigation {

            // Product Home
            attrs.renderProductHome = {
                createElement(CustomProductHome, jso {
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
                    jso { onClick = { window.location.href = "./#/intro" } },
                    createSpan("Intro")
                ),
                createElement(
                    PrimaryButton,
                    jso { onClick = { window.location.href = "./#/wrappers" } },
                    createSpan("Wrappers")
                ),
                createElement(
                    PrimaryButton,
                    jso { onClick = { window.location.href = "./#/utils" } },
                    createSpan("Utils")
                )
            )

            // Profile
            val profileIcon = createElementNullSafe {
                img {
                    attrs.src = "images/github-logo.png"
                    attrs.css {
                        borderRadius = 50.pct
                        width = 32.px
                        height = 32.px
                    }
                }
            }

            attrs.renderProfile = {
                createElement(Profile, jso {
                    href = "https://github.com/linked-planet/ui-kit"
                    target = "_blank"
                    icon = profileIcon
                })
            }
        }
    }
}

fun RBuilder.ShowcaseTopNavigation(handler: ShowcaseTopNavigationProps.() -> Unit) =
    child(ShowcaseTopNavigation) { attrs { handler() } }
