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
import com.linkedplanet.uikit.wrapper.atlaskit.icon.SearchIcon
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltip
import com.linkedplanet.uikit.wrapper.tooltip.ReactTooltipOffset
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc

val TooltipShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Tooltip"
        packages =
            Package("react-tooltip", "https://github.com/wwayne/react-tooltip").toList()


        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "tooltip"

        // region:tooltip
        val key = "tooltip-1"
        val example = createElementNullSafe {
            div {
                val dynAttrs = attrs.asDynamic()
                dynAttrs["data-tip"] = "true"
                dynAttrs["data-for"] = key
                SearchIcon {}
            }

            ReactTooltip {
                attrs.id = key
                attrs.className = "tooltip"
                attrs.place = "right"
                attrs.effect = "solid"
                attrs.type = "light"
                attrs.border = true
                attrs.borderColor = "rgb(222, 225, 231)"
                attrs.offset = ReactTooltipOffset(0, 0, -5, 0)

                span {
                    +"I'm a tooltip..."
                }
            }
        }
        examples = listOfNotNull(example)
        // endregion:tooltip
    }
}
