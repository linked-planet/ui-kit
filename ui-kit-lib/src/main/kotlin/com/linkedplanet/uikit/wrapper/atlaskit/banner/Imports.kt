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
@file:JsModule("@atlaskit/banner")

package com.linkedplanet.uikit.wrapper.atlaskit.banner

import react.*

@JsName("default")
external val Banner: ComponentClass<BannerProps>

external interface BannerProps : Props {

    /**
     * Visual style to be used for the banner, one of ["warning", "error", "announcement"].
     */
    var appearance: String

    /**
     * Icon to be shown left of the main content. Typically an Atlaskit @atlaskit/icon.
     */
    var icon: ReactNode?

    /**
     * Defines whether the banner is shown. An animation is used when the value is changed.
     * Default: false
     */
    var isOpen: Boolean
}
