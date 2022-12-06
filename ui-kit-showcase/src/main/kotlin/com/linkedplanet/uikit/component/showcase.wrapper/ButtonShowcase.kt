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
