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
