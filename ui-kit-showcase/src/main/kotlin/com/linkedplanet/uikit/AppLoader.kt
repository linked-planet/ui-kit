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
package com.linkedplanet.uikit

import browser.document
import browser.window
import react.createElement
import react.dom.client.createRoot
import react.react

@ExperimentalJsExport
fun main() {
    window.onload = {
        document.getElementById("root")
            ?.let { doc ->
                val root = createRoot(doc)
                val app = createElement(ShowcaseApp::class.react)
                root.render(app)
            }
            ?: console.error("No element with id 'root' found")
    }
}
