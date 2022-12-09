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
package com.linkedplanet.uikit.page

import com.linkedplanet.uikit.component.showcase.wrapper.*
import com.linkedplanet.uikit.util.Async
import com.linkedplanet.uikit.util.RequestUtil
import kotlinx.coroutines.await
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1

external interface WrappersPageProps : Props

val WrappersPage = fc<WrappersPageProps> { _ ->

    val (overallSourceCode, setOverallSourceCode) = useState("")


    // Retrieve source code
    Async.complete(
        taskName = "fetch-showcase-code-${this.hashCode()}",
        taskFun = {
            RequestUtil.requestAndHandle(
                url = "./showcase-sources.txt",
                handler = {
                    it.text().await()
                }
            )
        },
        completeFun = { sourceCode ->
            setOverallSourceCode(sourceCode)
        },
        catchFun = {
            console.error("Couldn't load source code...", it)
        }
    )

    div {
        h1 {
            +"Wrappers"
        }

        AvatarShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        AwesomeSliderShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        BadgeShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        ButtonShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        BannerShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        CalendarShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        CheckboxShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        CodeBlockShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        DateTimePickerShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        DateTimeRangePickerShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        DropdownMenuShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        EmptyStateShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        FlagShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        FormShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        IconShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        JoyrideShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        LozengeShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        ModalShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        PaginationShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        PanelShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        PopupShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        SelectShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        TabsShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        TagShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        DynamicTableShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        TableTreeShowcase {
            attrs.overallSourceCode = overallSourceCode
        }


        TextAreaShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        TextFieldShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        ToggleShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        TooltipShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        LPEditorShowcase {
            attrs.overallSourceCode = overallSourceCode
        }

        MenuShowcase {
            attrs.overallSourceCode = overallSourceCode
        }
    }
}
