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
import com.linkedplanet.uikit.wrapper.atlaskit.lozenge.Lozenge
import react.fc

val LozengeShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Lozenge"
        packages = Package(
            "@atlaskit/lozenge",
            "https://atlassian.design/components/lozenge/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "lozenge"

        // region:lozenge
        val example1 = createElementNullSafe {
            Lozenge {
                +"First lozenge"
            }
        }

        val example2 = createElementNullSafe {
            Lozenge {
                +"Colored lozenge"
                attrs.appearance = "new"
            }
        }

        val example3 = createElementNullSafe {
            Lozenge {
                +"Colored bold lozenge"
                attrs.appearance = "success"
                attrs.isBold = true
            }
        }

        val example4 = createElementNullSafe {
            Lozenge {
                +"Colored non-bold lozenge"
                attrs.appearance = "success"
                attrs.isBold = false
            }
        }
        // endregion:lozenge

        examples = listOfNotNull(example1, example2, example3, example4)
    }
}
