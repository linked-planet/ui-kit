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
