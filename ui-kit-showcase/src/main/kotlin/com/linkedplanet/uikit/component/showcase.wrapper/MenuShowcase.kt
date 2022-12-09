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
import com.linkedplanet.uikit.wrapper.atlaskit.badge.Badge
import com.linkedplanet.uikit.wrapper.atlaskit.menu.HeadingItem
import com.linkedplanet.uikit.wrapper.atlaskit.menu.MenuGroup
import com.linkedplanet.uikit.wrapper.atlaskit.tag.SimpleTag
import com.linkedplanet.uikit.wrapper.lpeditor.LPEditor
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h4
import react.fc
import react.useState

val MenuShowcase = fc<ShowcaseProps> { props ->

    var items by useState(listOf("Menu A", "Menu B", "Menu C"))
    var selectedItems by useState(listOf("Menu A"))

    ShowcaseWrapperItem {
        name = "Menu"
        packages =
            Package("@atlaskit/menu", "https://atlassian.design/components/menu/examples").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "menu"

        val example = createElementNullSafe {
            div {
                // region:menu
                /**
                 * States
                 * var items by useState(listOf("Menu A", "Menu B", "Menu C"))
                 * var selectedItems by useState(listOf("Menu A"))
                 */
                MenuGroup{

                    HeadingItem{
                        h4{+"Filter"}
                    }

                    items.forEach {currentItem ->
                        a {
                            attrs.onClick = {
                                val newList = if(selectedItems.contains(currentItem)) {
                                    selectedItems.minus(currentItem)
                                } else {
                                    selectedItems.plus(currentItem)
                                }
                                selectedItems = newList
                            }
                            SimpleTag {
                                if (selectedItems.contains(currentItem)){
                                    attrs.color = "grey"
                                }
                                attrs.text = currentItem
                            }
                            Badge {
                                attrs.appearance = "default"
                                +"0"
                            }
                        }
                    }
                }
                // endregion:menu
            }
        }

        examples = listOfNotNull(example)
    }
}
