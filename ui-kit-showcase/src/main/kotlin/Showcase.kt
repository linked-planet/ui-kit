import com.linkedplanet.uikit.atlaskit.button.Button
import com.linkedplanet.uikit.atlaskit.cssreset.CssReset
import com.linkedplanet.uikit.atlaskit.icon.SelectClearIcon
import com.linkedplanet.uikit.atlaskit.navigation.AtlassianNavigation
import com.linkedplanet.uikit.atlaskit.navigation.CustomProductHome
import com.linkedplanet.uikit.atlaskit.navigation.PrimaryButton
import com.linkedplanet.uikit.atlaskit.navigation.Profile
import com.linkedplanet.uikit.atlaskit.pagelayout.*
import com.linkedplanet.uikit.atlaskit.sidenavigation.*
import imports.atlaskit.icon.EmojiTravelIcon
import imports.atlaskit.icon.FileIcon
import imports.atlaskit.icon.IconProps
import imports.atlaskit.icon.PersonIcon
import imports.atlaskit.menu.LinkItem
import imports.atlaskit.menu.MenuGroup
import imports.atlaskit.popup.Popup
import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.browser.document
import react.*
import react.dom.a
import react.dom.img
import react.dom.jsStyle
import styled.css
import styled.styledDiv

external interface ShowcaseProps : RProps {
    var name: String
}

interface ShowcaseState : RState {
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
        CssReset

        PageLayout {

            TopNavigation {
                attrs.isFixed = true
                attrs.id = "TopNavigationElement"
                AtlassianNavigation {
                    attrs.label = "Test"
                    attrs.renderProductHome = {
                        CustomProductHome {
                            attrs.iconAlt = "https://www.linked-planet.com/shared/img/loader.png"
                            attrs.iconUrl = "https://www.linked-planet.com/shared/img/loader.png"
                            attrs.logoAlt = "https://www.linked-planet.com/shared/img/loader.png"
                            attrs.logoUrl = "https://www.linked-planet.com/shared/img/loader.png"
                            attrs.siteTitle = "UI-KIT"
                        }
                    }
                    attrs.label = "My Label"

                    val icon = img {
                        attrs.src =
                            "https://w7.pngwing.com/pngs/7/618/png-transparent-man-illustration-avatar-icon-fashion-men-avatar-face-fashion-girl-heroes.png"
                        attrs.jsStyle {
                            borderRadius = "50%"
                            width = 32
                            height = 32
                        }
                    }

                    attrs.primaryItems = listOf(
                        PrimaryButton {
                            +"Menu 1"
                        },
                        PrimaryButton {
                            +"Menu 2"
                        },
                        PrimaryButton {
                            +"Menu 3"
                        }
                    ).toTypedArray()

                    attrs.renderProfile = {
                        Popup {
                            attrs.isOpen = state.profilePopupIsOpen
                            attrs.onClose = {
                                setState { profilePopupIsOpen = false }
                            }
                            attrs.placement = "bottom-start"
                            attrs.trigger = { triggerProps ->
                                Profile {
                                    Object.keys(triggerProps).forEach { key ->
                                        val descriptor = Object.getOwnPropertyDescriptor<RProps>(triggerProps, key)
                                        Object.defineProperty(attrs, key, descriptor)
                                    }
                                    attrs.icon = icon
                                    attrs.onClick = {
                                        setState { profilePopupIsOpen = !profilePopupIsOpen }
                                    }
                                }
                            }
                            attrs.content = { _ ->
                                MenuGroup {
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
                                }
                            }
                        }
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
