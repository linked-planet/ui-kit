package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.atlaskit.button.Button
import com.linkedplanet.uikit.wrapper.atlaskit.button.ButtonGroup
import com.linkedplanet.uikit.wrapper.joyride.Joyride
import com.linkedplanet.uikit.wrapper.joyride.JoyrideLocale
import js.core.jso
import react.dom.html.ReactHTML.span
import react.fc
import react.useState

val JoyrideShowcase = fc<ShowcaseProps> { props ->
    val (isJoyrideActive, setIsJoyrideActive) = useState(false)

    ShowcaseWrapperItem {
        name = "Joyride"
        packages =
            Package("react-joyride", "https://docs.react-joyride.com/").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "joyride"

        val example = createElementNullSafe {
            // region:joyride
            ButtonGroup {
                Button {
                    attrs.isSelected = isJoyrideActive
                    attrs.onClick = {
                        setIsJoyrideActive(true)
                    }
                    +"Start Tour"
                }

                Button {
                    attrs.className = "joyride-first"
                    +"First step"
                }

                Button {
                    attrs.className = "joyride-second"
                    +"Second step"
                }

                Button {
                    attrs.className = "joyride-third"
                    +"Third step"
                }
            }

            Joyride {
                attrs.run = isJoyrideActive
                attrs.continuous = true
                attrs.showProgress = true
                attrs.disableScrolling = true
                attrs.scrollToFirstStep = false
                attrs.scrollOffset = 220
                attrs.locale = JoyrideLocale(
                    "Zurück",
                    "Schließen",
                    "Fertig",
                    "Weiter",
                    "Öffnen",
                    "Überspringen"
                )
                attrs.callback = { joyrideState ->
                    when (joyrideState.action) {
                        "close", "reset" -> {
                            setIsJoyrideActive(false)
                        }
                    }
                }
                attrs.steps = arrayOf(
                    jso {
                        title = "First step title"
                        target = ".joyride-first"
                        disableBeacon = true
                        showSkipButton = true
                        content = createElementNullSafe {
                            span {
                                +"First step content..."
                            }
                        }
                    },
                    jso {
                        title = "Second step title"
                        target = ".joyride-second"
                        disableBeacon = true
                        showSkipButton = true
                        content = createElementNullSafe {
                            span {
                                +"Second step content..."
                            }
                        }
                    },
                    jso {
                        title = "Third step title"
                        target = ".joyride-third"
                        disableBeacon = true
                        showSkipButton = true
                        content = createElementNullSafe {
                            span {
                                +"Third step content..."
                            }
                        }
                    }
                )
            }
            // endregion:joyride
        }

        examples = listOfNotNull(example)
    }
}
