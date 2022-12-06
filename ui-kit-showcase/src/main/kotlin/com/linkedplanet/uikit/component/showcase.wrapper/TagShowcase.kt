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
import com.linkedplanet.uikit.wrapper.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.wrapper.atlaskit.taggroup.TagGroup
import react.fc

val TagShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Tag & Tag-Group"
        packages = Package("@atlaskit/tag", "https://atlassian.design/components/tag/examples").toList()
            .plus(Package("@atlaskit/tag-group", "https://atlassian.design/components/tag-group/examples"))

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "tags"

        val example = createElementNullSafe {
            // region:tags
            TagGroup {
                SimpleTag {
                    attrs.text = "Simple Tag"
                }
                SimpleTag {
                    attrs.text = "Colored simple Tag"
                    attrs.color = "purple"
                }
            }
            // endregion:tags
        }

        examples = listOfNotNull(example)
    }
}
