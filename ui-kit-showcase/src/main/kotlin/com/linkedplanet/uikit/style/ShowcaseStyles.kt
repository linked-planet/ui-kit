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
package com.linkedplanet.uikit.style

import kotlinx.css.*
import styled.StyleSheet

object ShowcaseStyles : StyleSheet("ShowcaseStyles") {
    val showcaseItemsContainer by css {
        margin(50.px)

        children {
            borderBottom = "1px solid #EBECF0"
        }

        lastChild {
            borderBottom = "none"
        }
    }

    val showcaseItem by css {
        padding(20.px)
    }

    val showcaseItemExamplesContainer by css {
        display = Display.flex
    }

    val showcaseItemExample by css {
        border = "1px solid #EBECF0"
        margin(5.px)
        padding(15.px)
        backgroundColor = Color("var(--ds-background-default,#FFFFFF)")
        backgroundImage = Image(
            "linear-gradient( 45deg,var(--ds-background-sunken,#f9f9fa) 25%,transparent 25% )," +
                    "linear-gradient( 135deg,var(--ds-background-sunken,#f9f9fa) 25%,transparent 25% )," +
                    "linear-gradient( 45deg,transparent 75%,var(--ds-background-sunken,#f9f9fa) 75% )," +
                    "linear-gradient( 135deg,transparent 75%,var(--ds-background-sunken,#f9f9fa) 75% )"
        )
        backgroundPosition = "0px 0px, 10px 0px, 10px -10px, 0px 10px"
        backgroundSize = "20px 20px"

        firstChild {
            marginLeft = 0.px
        }
    }

    val showcaseItemExampleMediumSize by css {
        minWidth = 300.px
    }

    val showcaseItemExamplePanel by css {
        paddingLeft = 24.px
    }

    val showcaseItemExamplePopup by css {
        padding(15.px)
    }
}