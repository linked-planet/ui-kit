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

import com.linkedplanet.uikit.util.createElementNullSafe
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.emptystate.EmptyState
import kotlinx.browser.window
import react.Props
import react.dom.html.ReactHTML.span
import react.fc

external interface NotFoundPageProps : Props

val NotFoundPage = fc<NotFoundPageProps> { _ ->

    EmptyState {
        attrs.header = "404 - Not Found"
        attrs.description = createElementNullSafe {
            span {
                +"The page you were looking for doesn't exist..."
            }
        }

        attrs.primaryAction = createElementNullSafe {
            Button {
                attrs.onClick = {
                    window.location.href = "/"
                }
                attrs.appearance = "primary"
                +"Back to start"
            }
        }
    }
}
