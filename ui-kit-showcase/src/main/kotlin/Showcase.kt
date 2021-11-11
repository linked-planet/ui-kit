import com.linkedplanet.uikit.atlaskit.button.Button
import com.linkedplanet.uikit.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.atlaskit.pagelayout.*
import com.linkedplanet.uikit.atlaskit.sidenavigation.*
import react.*
import styled.*

external interface ShowcaseProps : RProps {
    var name: String
}

data class ShowcaseState(val name: String) : RState

@kotlin.js.ExperimentalJsExport
@JsExport
class Showcase(props: ShowcaseProps) : RComponent<ShowcaseProps, ShowcaseState>(props) {

    init {
        state = ShowcaseState(props.name)
    }

    override fun RBuilder.render() {
        CssReset

        PageLayout {

            Content {

                LeftSidebar {

                    SideNavigation {

                        NavigationHeader {
                            Header { +"linked-planet UIKit Showcase" }
                        }

                        NavigationContent {
                            Section {

                            }
                        }

                        NavigationFooter {
                            Footer {
                                +"Made with ❤ by linked-planet"
                            }
                        }
                    }
                }

                Main {

                    styledDiv {
                        css {
                            +ShowcaseStyles.mainContainer
                        }
                        Button {
                            +"Test"
                            attrs.appearance = "primary"
                        }
                    }
                }
            }
        }
    }
}
