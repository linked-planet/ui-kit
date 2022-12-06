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
