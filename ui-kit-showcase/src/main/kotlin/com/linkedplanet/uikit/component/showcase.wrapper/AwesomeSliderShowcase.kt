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
package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.awesomeslider.AwesomeSlider
import csstype.*
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.fc

val AwesomeSliderShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Awesome Slider"
        packages =
            Package("react-awesome-slider", "https://github.com/rcaferati/react-awesome-slider").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "awesome-slider"

        val slider = createElementNullSafe {
            // region:awesome-slider
            div {
                attrs.css {
                    minWidth = 600.px
                    display = Display.flex
                    height = 200.px
                    position = Position.relative
                    zIndex = integer(0)
                }

                kotlinext.js.require("react-awesome-slider/dist/styles.css")
                AwesomeSlider {
                    attrs.bullets = false

                    div {
                        img {
                            attrs {
                                css {
                                    objectFit = ObjectFit.scaleDown
                                }
                                src = "images/logo.png"
                                width = 100.0
                                height = 100.0
                            }
                        }
                    }

                    div {
                        img {
                            attrs {
                                css {
                                    objectFit = ObjectFit.scaleDown
                                }
                                src = "images/github-logo.png"
                                width = 100.0
                                height = 100.0
                            }
                        }
                    }
                }
            }
            // endregion:awesome-slider
        }

        examples = listOfNotNull(slider)
    }
}
