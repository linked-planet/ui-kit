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
