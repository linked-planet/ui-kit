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

import react.ElementType
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.useShouldNestedElementRender
import react.Props
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.ButtonItemProps
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.GoBackItem
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.NestingItemOverrides
import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.NestingItemProps
import react.ChildrenBuilder
import react.FC
import react.ReactNode
import react.createElement

interface GoBackItemRenderer {
    val render: (ButtonItemProps) -> ReactNode
}

/**
 * Helper to add a GoBackItem to the overrides attribute.
 */
fun NestingItemProps.overrideBackButton(block: ChildrenBuilder.(props: ButtonItemProps) -> Unit) {
    data class GoBackItemRenderImpl(override val render: (ButtonItemProps) -> ReactNode) : GoBackItemRenderer
    data class Overrides(override var GoBackItem: GoBackItemRenderer) : NestingItemOverrides

    overrides = Overrides(GoBackItemRenderImpl { props ->
        createElement(FC { originalButtonProps ->
            GoBackItem {
                onClick = originalButtonProps.onClick
                this.block(this)
            }
        }, props)
    })
}

// region NestableNavigationContent Helpers

/**
 * Convenience wrapper around a functional component
 * that will only render at the correct hierarchy inside a NestableNavigationContent
 */
fun <P : Props> nestingFC(
    block: ChildrenBuilder.(props: P) -> Unit,
): FC<P> = FC {
    if (useShouldNestedElementRender().shouldRender) {
        block(this, it)
    }
}

/**
 * Creates a nestable Element for an ElementType
 * that will only render at the correct hierarchy inside a NestableNavigationContent
 */
fun <PropsType : Props> ElementType<PropsType>.nesting(): FC<PropsType> = nestingFC {
    +createElement(type = this@nesting, props = it)
}

// endregion NestableNavigationContent Helpers