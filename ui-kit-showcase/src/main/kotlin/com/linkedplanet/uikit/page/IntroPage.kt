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

import react.Props
import react.dom.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.span
import react.fc

external interface IntroPageProps : Props

val IntroPage = fc<IntroPageProps> { props ->

    div {
        h1 {
            +"Welcome to UI-Kit"
        }

        h3 {
            +"Usage"
        }

        span {
            +"There will be more content soon..."
        }
    }
}
