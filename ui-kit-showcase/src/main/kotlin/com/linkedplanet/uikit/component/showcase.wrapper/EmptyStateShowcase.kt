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
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.emptystate.EmptyState
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import react.fc

val EmptyStateShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Empty state"
        packages = Package(
            "@atlaskit/empty-state",
            "https://atlassian.design/components/empty-state/examples"
        ).toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "empty-state"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 300.px
                }
                // region:empty-state
                EmptyState {
                    attrs.header = "Empty state"
                    attrs.description = createElementNullSafe {
                        span {
                            +"Content of this state..."
                        }
                    }
                    attrs.primaryAction = createElementNullSafe {
                        Button {
                            +"Dummy button"
                        }
                    }
                }
                // endregion:empty-state
            }
        }

        examples = listOfNotNull(example)
    }
}
