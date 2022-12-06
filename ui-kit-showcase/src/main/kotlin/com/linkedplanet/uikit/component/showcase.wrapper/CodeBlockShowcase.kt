package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.code.CodeBlock
import react.fc

val CodeBlockShowcase = fc<ShowcaseProps> { props ->
    ShowcaseWrapperItem {
        name = "Code block"
        packages =
            Package("@atlaskit/code", "https://atlassian.design/components/code/code-block/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "code-block"

        val example = createElementNullSafe {
            // region:code-block
            CodeBlock {
                attrs.language = "java"
                attrs.text = """
                        class Hello {
                            public static void main(String args...) {
                                System.out.println("Hello World!)
                            }
                        }
                        """.trimIndent()
            }
            // endregion:code-block
        }

        examples = listOfNotNull(example)
    }
}
