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

import com.linkedplanet.uikit.wrapper.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.Content
import com.linkedplanet.uikit.wrapper.atlaskit.pagelayout.PageLayout
import react.*

external interface ShowcaseProps : Props {
    var name: String
}

interface ShowcaseState : State

class Showcase(props: ShowcaseProps) : RComponent<ShowcaseProps, ShowcaseState>(props) {

    override fun RBuilder.render() {
        CssReset

        PageLayout {

            ShowcaseTopNavigation {}

            Content {

                ShowcaseLeftSidebar {}

                ShowcaseMain {}
            }
        }
    }
}
