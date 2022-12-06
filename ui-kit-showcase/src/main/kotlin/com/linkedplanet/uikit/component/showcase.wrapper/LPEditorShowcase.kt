package com.linkedplanet.uikit.component.showcase.wrapper

import com.linkedplanet.uikit.component.Package
import com.linkedplanet.uikit.util.*
import com.linkedplanet.uikit.wrapper.lpeditor.LPEditor
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.fc
import react.useState

val LPEditorShowcase = fc<ShowcaseProps> { props ->
    val (editorString, setEditorString) = useState("<h1>Hello \$object.Person</h1>")
    val (objectString, setObjectString) = useState("""{ "object": { "Person": { "First Name" : [{"k1":"v1"},{"k2":"v2"}], "Last Name": "2ndValue", "Age": 30, "VIP": true } } }""")

    ShowcaseWrapperItem {
        name = "LPEditor"
        packages =
            Package("@monaco-editor/react", "https://github.com/suren-atoyan/monaco-react").toList()

        this.overallSourceCode = props.overallSourceCode
        sourceCodeExampleId = "lpeditor"

        val example = createElementNullSafe {
            div {
                attrs.css {
                    minWidth = 600.px
                    minHeight = 300.px
                }

                // region:lpeditor
                LPEditor {
                    attrs.objectString = objectString
                    attrs.onChange = { value, event ->
                        setEditorString(value)
                        console.info("IntroPage OnChange for LPEditor was called:", value)
                    }
                    attrs.defaultLanguage = "html"
                    attrs.value = editorString
                    attrs.height = "300px"
                }
                // endregion:lpeditor
            }
        }

        examples = listOfNotNull(example)
    }
}
