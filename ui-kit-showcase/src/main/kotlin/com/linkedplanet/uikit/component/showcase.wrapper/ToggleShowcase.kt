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
import com.linkedplanet.uikit.wrapper.atlaskit.toggle.Toggle
import react.fc
import react.useState

val ToggleShowcase = fc<ShowcaseProps> { props ->
    val (isToggleActive, setIsToggleActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Toggle"
        packages =
            Package("@atlaskit/toggle", "https://atlassian.design/components/toggle/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "toggle"

        val example = createElementNullSafe {
            // region:toggle
            Toggle {
                attrs.isChecked = isToggleActive
                attrs.onChange = {
                    setIsToggleActive(!isToggleActive)
                }
            }
            // endregion:toggle
        }

        examples = listOfNotNull(example)
    }
}
