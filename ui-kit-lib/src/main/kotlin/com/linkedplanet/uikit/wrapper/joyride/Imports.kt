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
@file:JsModule("react-joyride")

package com.linkedplanet.uikit.wrapper.joyride

import react.*

@JsName("default")
external val Joyride: ComponentClass<JoyrideProperties>

external interface JoyrideProperties : Props {
    /**
     * Run/stop the tour.
     */
    var run: Boolean

    /**
     * The tour's steps.
     */
    var steps: Array<JoyrideStep>

    /**
     * The tour is played sequentially with the Next button.
     */
    var continuous: Boolean

    /**
     * Log Joyride's actions to the console (default: false).
     */
    var debug: Boolean

    /**
     * Display the tour progress in the next button _e.g. 2/5 _in continuous tours (default: false).
     */
    var showProgress: Boolean

    /**
     * The strings used in the tooltip.
     */
    var locale: JoyrideLocale

    /**
     * The padding of the spotlight.
     */
    var spotlightPadding: Int

    /**
     * Disable auto scrolling between steps (default: false).
     */
    var disableScrolling: Boolean

    /**
     * Disable the fix to handle "unused" overflow parents.
     */
    var disableScrollParentFix: Boolean

    /**
     * Don't close the tooltip when clicking the overlay (default: false).
     */
    var disableOverlayClose: Boolean

    /**
     * Scroll the page for the first step (default: false).
     */
    var scrollToFirstStep: Boolean

    /**
     * The scroll distance from the element scrollTop value.
     */
    var scrollOffset: Int

    /**
     * Override the styling of the Tooltip globally.
     */
    var styles: dynamic

    /**
     * Options to be passed to react-floater.
     */
    var floaterProps: dynamic

    /**
     * Display a button to skip the tour.
     */
    var showSkipButton: Boolean

    /**
     * It will be called when Joyride's state changes. it returns a single parameter with the state.
     */
    var callback: (JoyrideState) -> Unit
}

@JsName("Step")
external interface JoyrideStep {
    /**
     * The tooltip's title.
     */
    var title: String

    /**
     * The target for the step identified by a CSS selector.
     */
    var target: String

    /**
     * The tooltip's body.
     */
    var content: ReactNode

    /**
     * The placement of the beacon and tooltip (default: bottom). It will re-position itself if there's no space
     * available. It can be:
     * - top, top-start, top-end
     * - bottom, bottom-start, bottom-end
     * - left, left-start, left-end
     * - right, right-start, right-end
     * - auto (it will choose the best position)
     * - center (set the target to body)
     */
    var placement: String

    /**
     * Don't show the Beacon before the tooltip (default: false).
     */
    var disableBeacon: Boolean

    /**
     * The distance from the target to the tooltip.
     */
    var offset: Int

    /**
     * Display a button to skip the tour.
     */
    var showSkipButton: Boolean
}

external interface JoyrideState {
    var action: String
    var controlled: Boolean
    var index: Int
    var lifecycle: String
    var size: Int
    var status: String
    var step: JoyrideStep
    var type: String
}
