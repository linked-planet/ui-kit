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
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.Avatar
import com.linkedplanet.uikit.wrapper.atlaskit.avatar.AvatarItem
import react.fc

val AvatarShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Avatar"
        packages =
            Package("@atlaskit/avatar", "https://atlassian.design/components/avatar/examples").toList()

        overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "avatar"

        // region:avatar
        val avatar = createElementNullSafe {
            Avatar {
                attrs.size = "large"
                attrs.presence = "online"
            }
        }

        val example1 = createElementNullSafe {
            AvatarItem {
                attrs.avatar = avatar
            }
        }

        val example2 = createElementNullSafe {
            AvatarItem {
                attrs.avatar = avatar
                attrs.primaryText = "Carl Coder"
                attrs.secondaryText = "Software Engineer"
            }
        }
        // endregion:avatar

        examples = listOfNotNull(example1, example2)
    }
}
