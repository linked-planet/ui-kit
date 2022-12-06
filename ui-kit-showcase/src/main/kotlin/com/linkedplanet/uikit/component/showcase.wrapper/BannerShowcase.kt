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
import com.linkedplanet.uikit.wrapper.atlaskit.banner.Banner
import com.linkedplanet.uikit.wrapper.atlaskit.icon.ErrorIcon
import com.linkedplanet.uikit.wrapper.atlaskit.icon.WarningIcon
import react.dom.html.ReactHTML.span
import react.fc

val BannerShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Banner"
        packages =
            Package("@atlaskit/banner", "https://atlassian.design/components/banner/examples").toList()


        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "banner"

        // region:banner
        val example1 = createElementNullSafe {
            Banner {
                attrs.appearance = "announcement"
                attrs.isOpen = true

                span {
                    +"Content of the banner..."
                }
            }
        }

        val example2 = createElementNullSafe {
            Banner {
                attrs.appearance = "warning"
                attrs.icon = createElementNullSafe {
                    WarningIcon {
                        attrs.secondaryColor = "var(--ds-background-warning-bold, #FFAB00)"
                    }
                }
                attrs.isOpen = true

                span {
                    +"Content of the banner..."
                }
            }
        }

        val example3 = createElementNullSafe {
            Banner {
                attrs.appearance = "error"
                attrs.icon = createElementNullSafe {
                    ErrorIcon {
                        attrs.secondaryColor = "var(--ds-background-danger-bold, #DE350B)"
                    }
                }
                attrs.isOpen = true

                span {
                    +"Content of the banner..."
                }
            }
        }
        // endregion:banner

        examples = listOfNotNull(example1, example2, example3)
    }
}
