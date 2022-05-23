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
@file:JsModule("@atlaskit/pagination")

package com.linkedplanet.uikit.wrapper.atlaskit.pagination

import org.w3c.dom.events.Event
import react.*

@JsName("default")
external val Pagination: ComponentClass<PaginationProps>

external interface PaginationProps : Props {

    /**
     * Replace the built-in Page, Previous, Next and/ or Ellipsis component.
     */
    var components: PaginationComponentsProps

    /**
     * Index of the page to be selected by default.
     */
    var defaultSelectedIndex: Int

    /**
     * Helper function to get text displayed on the page button. It is helpful in scenarios when page the page passed
     * in is an object.
     */
    var getPageLabel: (page: dynamic, pageIndex: Int) -> String

    /**
     * The aria-label for the next button.
     */
    var nextLabel: String

    /**
     * The aria-label for the previous button
     */
    var previousLabel: String

    /**
     * Maximum number of pages to be displayed in the pagination.
     */
    var max: Int

    /**
     * Array of the pages to display.
     */
    var pages: Array<Int>

    /**
     * Index of the selected page. This will make this pagination controlled.
     */
    var selectedIndex: Int

    /**
     * The onChange handler which is called when the page is changed.
     */
    var onChange: (Event, Int, Event) -> Unit

}

external interface PaginationComponentsProps {
    var Page: ReactNode
    var Previous: ReactNode
    var Next: ReactNode
}
