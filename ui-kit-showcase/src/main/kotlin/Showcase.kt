import com.linkedplanet.uikit.atlaskit.button.Button
import com.linkedplanet.uikit.atlaskit.icon.*
import com.linkedplanet.uikit.atlaskit.menu.LinkItem
import com.linkedplanet.uikit.atlaskit.menu.MenuGroup
import com.linkedplanet.uikit.atlaskit.navigation.*
import com.linkedplanet.uikit.atlaskit.pagelayout.*
import com.linkedplanet.uikit.atlaskit.popup.Popup
import com.linkedplanet.uikit.atlaskit.sidenavigation.*
import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.browser.document
import kotlinx.css.margin
import kotlinx.css.px
import react.*
import react.dom.a
import react.dom.div
import react.dom.img
import react.dom.jsStyle
import styled.css
import styled.styledDiv
import kotlin.js.json

external interface ShowcaseProps : Props {
    var name: String
}

interface ShowcaseState : State {
    var name: String
    var profilePopupIsOpen: Boolean
}

@kotlin.js.ExperimentalJsExport
@JsExport
class Showcase(props: ShowcaseProps) : RComponent<ShowcaseProps, ShowcaseState>(props) {

    init {
        state.name = ""
        state.profilePopupIsOpen = false
    }

    override fun componentWillReceiveProps(nextProps: ShowcaseProps) {
        if(state.name != nextProps.name){
            setState {
                name = nextProps.name
            }
        }
    }

    override fun RBuilder.render() {
        PageLayout {

            TopNavigation {
                attrs.isFixed = true
                attrs.id = "TopNavigationElement"
                AtlassianNavigation {
                    attrs.label = "Test"
                    attrs.renderProductHome = {
                        createElement(CustomProductHome, jsObject {
                            iconAlt = "https://www.linked-planet.com/shared/img/loader.png"
                            iconUrl = "https://www.linked-planet.com/shared/img/loader.png"
                            logoAlt = "https://www.linked-planet.com/shared/img/loader.png"
                            logoUrl = "https://www.linked-planet.com/shared/img/loader.png"
                            siteTitle = "UI-KIT"
                        })
                    }
                    attrs.label = "My Label"

                    val iconIn = img {
                        attrs.src =
                            "https://w7.pngwing.com/pngs/7/618/png-transparent-man-illustration-avatar-icon-fashion-men-avatar-face-fashion-girl-heroes.png"
                        attrs.jsStyle {
                            borderRadius = "50%"
                            width = 32
                            height = 32
                        }
                    }

                    attrs.primaryItems = listOf(
                        createElement(PrimaryButton, jsObject { +"Menu 1" }),
                        createElement(PrimaryButton, jsObject { +"Menu 2" }),
                        createElement(PrimaryButton, jsObject { +"Menu 3" })
                    ).toTypedArray()

                    attrs.renderProfile = {
                        createElement(Popup, jsObject {
                            isOpen = state.profilePopupIsOpen
                            onClose = {
                                setState { profilePopupIsOpen = false }
                            }
                            placement = "bottom-start"
                            trigger = { triggerProps ->
                                createElement(Profile, jsObject {
                                    Object.keys(triggerProps).forEach { key ->
                                        val descriptor = Object.getOwnPropertyDescriptor<RProps>(triggerProps, key)
                                        Object.defineProperty(attrs, key, descriptor)
                                    }
                                    icon = iconIn
                                    onClick = {
                                        setState { profilePopupIsOpen = !profilePopupIsOpen }
                                    }
                                })
                            }
                            content = { _ ->
                                createElement(MenuGroup, jsObject {
                                    LinkItem {
                                        +"Profile"
                                        attrs.iconBefore = createElement<IconProps>(PersonIcon, jsObject{})
                                        attrs.onClick = {
                                            setState { profilePopupIsOpen = false }
                                        }
                                    }
                                    LinkItem {
                                        +"Logout"
                                        attrs.iconBefore = createElement<IconProps>(SelectClearIcon, jsObject{})
                                        attrs.onClick = {
                                            setState { profilePopupIsOpen = false }
                                        }
                                    }
                                })
                            }
                        })
                    }
                }
            }

            Content {

                LeftSidebar {

                    SideNavigation {

                        NavigationHeader {
                            Header {
                                +document.title.uppercase()
                                attrs.description = "linked-planet"
                            }
                        }

                        NestableNavigationContent {
                            val fileIcon = createElement<IconProps>(FileIcon, jsObject{})
                            val travelIcon = createElement<IconProps>(EmojiTravelIcon, jsObject{})
                            Section {
                                ButtonItem {
                                    attrs.iconBefore = travelIcon
                                    +"My Flights"
                                    attrs.onClick = {
                                        console.log("My Flights clicked")
                                    }
                                }
                                NestingItem {

                                    attrs.id = "nesting-item-id"
                                    attrs.title = "nesting item"
                                    attrs.iconBefore = fileIcon
                                    Section {
                                        ButtonItem {
                                            +"Do something"
                                        }
                                    }
                                    Section {
                                        attrs.title = "Do something 2"
                                        ButtonItem {
                                            +"Do something 2"
                                        }
                                    }
                                }
                            }
                        }

                        NavigationFooter {
                            Footer {
                                +"Made with ❤ by "
                                a(href = "https://www.linked-planet.com/") {
                                    +"linked-planet"
                                }
                            }
                        }
                    }
                }

                Main {

                    div {
                        attrs["style"] = json(
                            "margin" to "30px"
                        )
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
