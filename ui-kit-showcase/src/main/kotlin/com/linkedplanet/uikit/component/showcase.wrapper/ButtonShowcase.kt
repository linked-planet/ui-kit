package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.*
import kotlinx.browser.window
import react.fc
import react.useState

val ButtonShowcase = fc<ShowcaseProps> { props ->
    val (isLoading, setIsLoading) = useState(false)

    ShowcaseWrapperItem {
        name = "Button & Button-Group"
        packages =
            Package("@atlaskit/button", "https://atlassian.design/components/button/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "button"

        val example = createElementNullSafe {
            // region:button
            ButtonGroup {
                Button {
                    +"Normal button"
                    attrs.appearance = "primary"
                    attrs.onClick = {
                        console.log("Button pressed")
                    }
                }

                LoadingButton {
                    +"Loading button"
                    attrs.isLoading = isLoading
                    attrs.onClick = { _ ->
                        setIsLoading(true)
                        window.setTimeout({ setIsLoading(false) }, 5000)
                    }
                }
            }
            // endregion:button
        }

        examples = listOfNotNull(example)
    }
}
