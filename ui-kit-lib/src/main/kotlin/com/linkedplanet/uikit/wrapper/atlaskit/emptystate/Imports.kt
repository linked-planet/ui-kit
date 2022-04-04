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
@file:JsModule("@atlaskit/empty-state")

package com.linkedplanet.uikit.wrapper.atlaskit.emptystate

import react.*

@JsName("default")
external val EmptyState: ComponentClass<EmptyStateProps>

external interface EmptyStateProps : Props {

    /**
     * Title that briefly describes the page to the user.
     */
    var header: String

    /**
     * The main block of text that holds additional supporting information.
     */
    var description: ReactNode

    /**
     * Controls how much horizontal space the component fills. Defaults to "wide".
     * One of:
     * - "narrow",
     * - "wide"
     */
    var width: String

    /**
     * The url of image that will be shown above the title, fed directly into the src prop of an  element. Note, this
     * image will be constrained by the maxWidth and maxHeight props.
     */
    var imageUrl: String

    /**
     * Primary action button for the page, usually it will be something like "Create" (or "Retry" for error pages).
     */
    var primaryAction: ReactNode

    /**
     * Secondary action button for the page.
     */
    var secondaryAction: ReactNode

    /**
     * Button with link to some external resource like documentation or tutorial, it will be opened in a new tab.
     */
    var tertiaryAction: ReactNode

    /**
     * Used to indicate a loading state. Will show a spinner next to the action buttons when true.
     */
    var isLoading: Boolean
}