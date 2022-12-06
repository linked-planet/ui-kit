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
package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.util.createElementNullSafe
import com.linkedplanet.uikit.wrapper.atlaskit.code.CodeBlock
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tab
import com.linkedplanet.uikit.wrapper.atlaskit.tab.Tabs
import csstype.*
import emotion.react.css
import react.*
import react.dom.html.AnchorTarget
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.span

data class Package(
    val name: String,
    val url: String
) {
    fun toList(): List<Package> = listOf(this)
}

external interface ShowcaseWrapperItemProps : PropsWithChildren {
    var name: String
    var packages: List<Package>

    /**
     * Identifies the code block used as source code for the example.
     *
     * Mark such code blocks in the source code using comments like this with the desired id (e.g. ex-1):
     * // region:ex-1
     * ...
     * // endregion:ex-1
     */
    var sourceCodeExampleId: String?
    var overallSourceCode: String?

    var examples: List<ReactNode>
}

val ShowcaseWrapperItem = fc<ShowcaseWrapperItemProps> { props ->

    // Extract code
    val code = props.overallSourceCode
        ?.takeIf { it.isNotEmpty() }
        ?.takeIf { props.sourceCodeExampleId != null }
        ?.takeIf { props.sourceCodeExampleId!!.isNotEmpty() }
        ?.let { extractSourceCodeExample(it, props.sourceCodeExampleId!!) }
        ?: ""

    // Render
    div {
        attrs.css {
            padding = Padding(20.px, 20.px)
        }

        h3 {
            +props.name
        }

        div {
            attrs.css {
                fontWeight = FontWeight.lighter
                fontSize = 0.8.rem
            }
            span {
                +"Packages: "
            }
            props.packages.forEachIndexed { index, pack ->
                a {
                    attrs.href = pack.url
                    attrs.target = AnchorTarget._blank
                    +pack.name
                }
                if (index != props.packages.size - 1) {
                    +", "
                }
            }
        }

        div {
            attrs.css {
                // Eliminate margin of tabs
                marginLeft = -8.px
            }
            Tabs {
                attrs.tabs = arrayOf(
                    Tab("Example", createElementNullSafe {
                        div {
                            attrs.css {
                                display = Display.flex
                            }
                            props.examples.forEach {
                                div {
                                    attrs.className = ClassName("example")
                                    +it
                                }
                            }
                        }
                    }),

                    Tab("Example source", createElementNullSafe {
                        if (code.isNotEmpty()) {
                            div {
                                attrs.css {
                                    width = 100.pct
                                }
                                CodeBlock {
                                    attrs.text = code
                                    attrs.language = "kotlin"
                                }
                            }
                        } else {
                            span { +"No sources found..." }
                        }
                    }),
                )
            }
        }
    }
}

private fun extractSourceCodeExample(overallSourceCode: String, sourceCodeExampleId: String): String {
    val exampleCodeStartMarker = "// region:$sourceCodeExampleId"
    val exampleCodeEndMarker = "// endregion:$sourceCodeExampleId"
    return if (overallSourceCode.contains(exampleCodeStartMarker) && overallSourceCode.contains(exampleCodeEndMarker)) {
        overallSourceCode
            .replaceBefore(exampleCodeStartMarker, "")
            .replace(exampleCodeStartMarker, "")
            .replaceAfter(exampleCodeEndMarker, "")
            .replace(exampleCodeEndMarker, "")
            .trimIndent()
    } else ""
}


