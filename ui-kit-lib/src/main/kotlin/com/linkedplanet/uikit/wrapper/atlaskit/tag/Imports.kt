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
@file:JsModule("@atlaskit/tag")

package com.linkedplanet.uikit.wrapper.atlaskit.tag

import react.ComponentClass
import react.Props

@JsName("SimpleTag")
external val SimpleTag: ComponentClass<SimpleTagProps>

external interface SimpleTagProps : Props {
    var text: String
    var color: String
}


