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
import com.linkedplanet.uikit.wrapper.atlaskit.checkbox.Checkbox
import react.fc
import react.useState

val CheckboxShowcase = fc<ShowcaseProps> { props ->
    val (isCheckboxActive, setIsCheckboxActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Checkbox"
        packages =
            Package("@atlaskit/checkbox", "https://atlassian.design/components/checkbox/example").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "checkbox"

        val example = createElementNullSafe {
            // region:checkbox
            Checkbox {
                attrs.isChecked = isCheckboxActive
                attrs.onChange = {
                    setIsCheckboxActive(!isCheckboxActive)
                }
            }
            // endregion:checkbox
        }

        examples = listOfNotNull(example)
    }
}
