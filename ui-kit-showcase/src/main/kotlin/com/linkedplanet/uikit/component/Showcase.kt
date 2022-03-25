package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.atlaskit.pagelayout.Content
import com.linkedplanet.uikit.atlaskit.pagelayout.PageLayout
import react.*

external interface ShowcaseProps : Props {
    var name: String
}

interface ShowcaseState : State

class Showcase(props: ShowcaseProps) : RComponent<ShowcaseProps, ShowcaseState>(props) {

    override fun RBuilder.render() {
        CssReset

        PageLayout {

            ShowcaseTopNavigation {}

            Content {

                ShowcaseLeftSidebar {}

                ShowcaseMain {}
            }
        }
    }
}
