import com.linkedplanet.uikit.wrapper.atlaskit.sidenavigation.ButtonItemProps
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
 * You probably want to set the onClick attribute to props.onClick for the back button to work
 */
fun NestingItemProps.overrideBackButton(block: ChildrenBuilder.(props: ButtonItemProps) -> Unit) {
    data class GoBackItemRenderImpl(override val render: (ButtonItemProps) -> ReactNode) : GoBackItemRenderer
    data class Overrides(override var GoBackItem: GoBackItemRenderer) : NestingItemOverrides
    this.overrides = Overrides(GoBackItemRenderImpl { props ->
        createElement(FC(block), props)
    })
}
