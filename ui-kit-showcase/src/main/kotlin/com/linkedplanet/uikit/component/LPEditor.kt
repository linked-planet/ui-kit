package com.linkedplanet.uikit.component

import com.linkedplanet.uikit.imports.Editor
import com.linkedplanet.uikit.imports.Position
import com.linkedplanet.uikit.imports.loader
import kotlinx.js.Object
import kotlinx.js.ReadonlyArray
import react.*
import react.dom.div
import kotlin.js.Json
import kotlin.js.Promise
import kotlin.js.json

external interface LPEditorProps : Props {
    var editorString: String
    var objectString: String
    var onChange: (String) -> Unit
}

data class Item(val parent: String, val key: String, val value: String)




val LPEditor = fc<LPEditorProps> { props ->

    val (editorString, setEditorString) = useState(props.editorString)

    fun flatObject(parentKey: String, obj: dynamic): List<Item> {
        val keys: ReadonlyArray<String> = Object.keys(obj as Any)
        return keys.flatMap { key: String ->
            val value = obj[key]
            if (value == null || value == undefined) {
                listOf(Item(parentKey, key, ""))
            } else if (Object.keys(value as Any).isNotEmpty() && value !is String) { // assumes value is object
                flatObject("$parentKey$key.", value).plus(Item(parentKey, key, value.toString()))
            } else {
                listOf(Item(parentKey, key, value.toString()))
            }
        }
    }

    fun itemsFromObjectString(objectString: String): List<Item> {
        val jsonObject = JSON.parse<dynamic>(objectString)
        return flatObject("$", jsonObject)
    }

    val (items, setItems) = useState(itemsFromObjectString(props.objectString))

    useEffect(arrayOf(editorString)) {
        console.info("useEffect editorString XXXXXXXXXXXXX", editorString)
        props.onChange(editorString)
    }

    fun suggestionForLabel(word: String) = json(
        "label" to word, // label is shown in the gui
        "kind" to "CompletionItemKind.Text", // https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.CompletionItemKind.html
        "documentation" to "doc",
        "insertText" to word // the text that inserted after selecting the shown label
        //                "range" to "range"
    )

    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
    fun suggestions(textFromLastJsonObject: String): Array<Json> {
        return items
            .filter { it.parent == textFromLastJsonObject }
            .map { suggestionForLabel(it.key) }.toTypedArray()
    }

    fun range(startLine: Int, endLine: Int, startColumn: Int, endColumn: Int): Json = json(
        "startLineNumber" to startLine,
        "endLineNumber" to endLine,
        "startColumn" to startColumn,
        "endColumn" to endColumn
    )

    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItemProvider.html#provideCompletionItems
    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideCompletionItems(model: dynamic, position: Position, token: dynamic): Json {
        console.info("provideCompletionItems", position)

        val textUntilPosition: String =
            model.getValueInRange(range(position.lineNumber, position.lineNumber, 1, position.column)) as String
        console.info("textUntilPosition", textUntilPosition)

        var jsonObject = "\$" + textUntilPosition.substringAfterLast("\$", missingDelimiterValue = "")
        console.info("textFromLastJsonObject", jsonObject)

        if (jsonObject.contains(".")) {
            jsonObject = jsonObject.substringBeforeLast(".") + "."
        }

        if (jsonObject.isEmpty()) {
            return json("suggestions" to arrayOf<Json>())
        }

        return json("suggestions" to suggestions(jsonObject))
    }

    fun prepareEditor(editor: dynamic) {
        editor.defineTheme(
            "lp-theme", json(
                "base" to "vs",
                "inherit" to true,
                "colors" to json(
//                    "editor.background" to "#7CFC00",
                ),
                "rules" to arrayOf(
                    json(
                        "token" to "myTokenType",
                        "foreground" to "#ff0000",
                        "fontStyle" to "italic",
                        "underline" to true,
                    )
                )
            )
        )
    }

    fun getLegend(): Json {
        return json(
            "tokenModifiers" to arrayOf<String>(),
            "tokenTypes" to arrayOf("myTokenType") // We have only 1 Token with Index 0
        )
    }

    /**
     * If the found key hierarchy is found inside a flatObject Item
     *
     * @param matchResult: a json key hierarchy (found by the regex) $object.Name,
     */
    fun areMatchesReallyKeys(matchResult: MatchResult): Boolean =
        (items.any { it.parent + it.key == matchResult.value })
            .also { wasFound ->
                console.info("matchResult:", matchResult)
                console.info("items:", items)
                console.info("found any?", wasFound)
            }

    /**
     * Allows to accumulate Semantic Tokens used for syntax highlighting
     * Each Token highlights a range of characters inside the editor
     *
     * In a sane world one would expect an Array<SemanticToken>, but Monaco uses a continuous array of Ints,
     * so the tokenCount is actually (tokens.data.length / 5) | 0;
     * To make it more complicated the Tokens are not stored with absolute values but each token contains
     * the distance from the last token. This class does handle all of this transparently.
     */
    class SemanticTokenArrayAccumulator(
        private var previousLine: Int = 0, // result needs the differences between hits, so remember the line of the previous token
        private var previousPos: Int = 0, // result needs the differences between hits, so remember the position of the previous token
        private val tokens: MutableList<Int> = listOf<Int>().toMutableList()
    ) {
        fun addTokenWithAbsolutePosition(lineNumber: Int, hitPos: Int, hitLength: Int, tokenTypeIndex: Int, tokenModifierSet: Int = 0) {
            val deltaPos = if (lineNumber == previousLine) hitPos - previousPos else hitPos
            val deltaLine = lineNumber - previousLine
            tokens.addAll(listOf(deltaLine, deltaPos, hitLength, tokenTypeIndex, tokenModifierSet))
            previousLine = lineNumber
            previousPos = hitPos
        }

        fun dataAsJsonObject(): Json {
            val dataAsArray = tokens.toIntArray()
            return json(
                "data" to dataAsArray,  /*deltaLine*/ /*deltaStart*/ /*distance*/ /*tokenTypeIndex*/ /*tokenModifierSet*/
            )
        }
    }

    /**
     * matches $object $object.name $a.b.c but stops at whitespace or the html open bracket "<"
     */
    val matchesJsonKeyHierarchiesStartingWithDollar = Regex("""\$([^\s<]*\.)*([^\s<]*)""")

    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideDocumentSemanticTokens(model: dynamic, lastResultId: String?, token: dynamic): Json {
        val lineContents = model.getLinesContent() as Array<String>

        return lineContents.foldIndexed(SemanticTokenArrayAccumulator()) { lineNumber, allTokens, lineContent: String ->
            matchesJsonKeyHierarchiesStartingWithDollar
                .findAll(lineContent)
                .fold(allTokens) { lineTokens, matchResult ->
                    if (areMatchesReallyKeys(matchResult)) {
                        val hitPos = matchResult.range.first
                        val hitLength = matchResult.range.last - hitPos + 1
                        lineTokens.addTokenWithAbsolutePosition(lineNumber, hitPos, hitLength, 0)
                    }
                    return@fold lineTokens
                }
            return@foldIndexed allTokens
        }.dataAsJsonObject()
    }

    fun createHighlights(): Array<Json> {
        console.info("createHighlights")
        return arrayOf(
            json(
                "kind" to "DocumentHighlightKind.Text", // https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.CompletionItemKind.html
                "range" to range(1, 1, 3, 8)
            )
        )
    }

    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.DocumentHighlight.html
    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.DocumentHighlightProvider.html
    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideDocumentHighlights(model: dynamic, position: dynamic, token: dynamic): Array<Json> {
        return createHighlights()
    }

    fun prepareMonaco(monaco: dynamic) {
        console.info("prepareMonaco")

        monaco.languages.registerCompletionItemProvider(
            "html", json(
                "provideCompletionItems" to ::provideCompletionItems
//optional  "resolveCompletionItem" to ...
            )
        )

        monaco.languages.registerDocumentHighlightProvider(
            "html", json(
                "provideDocumentHighlights" to ::provideDocumentHighlights // user selects something -> highlight other parts of the document
            )
        )

        monaco.languages.registerDocumentSemanticTokensProvider("html", json(
            "getLegend" to ::getLegend,
            "provideDocumentSemanticTokens" to ::provideDocumentSemanticTokens,
            "releaseDocumentSemanticTokens" to { resultId: String -> /*doNothing*/ }
        ))
    }

    fun init(editor: dynamic, monaco: dynamic) {
        console.info("init from onMount", monaco, editor)
        console.info("items", items)
        if (editor != undefined && monaco != undefined) {
            console.info("Preparing Monaco-Editor")
            console.info("Editor", monaco.editor)
            console.info("Monaco", monaco)
            prepareEditor(monaco.editor)
            prepareMonaco(monaco)
        }
    }

    useEffectOnce {
        (loader.init() as Promise<dynamic>).then { monaco: dynamic ->
            val editor = monaco.editor
            console.info("loader.init() promise resolved with monaco.", monaco, editor)

            prepareEditor(editor)
            prepareMonaco(monaco)
        }

        console.info("useEffect setItems")
    }

    useEffect(props.objectString) {
        console.info("UseEffect for ObjectString")
        setItems(itemsFromObjectString(props.objectString))
    }

    useEffect(items) {
        console.info("UseEffect for Items")
        (loader.init() as Promise<dynamic>).then { monaco: dynamic ->
            val editor = monaco.editor
            console.info("loader.init() promise resolved with monaco.", monaco, editor)

            prepareEditor(editor)
            prepareMonaco(monaco)
        }
    }

    div {
        attrs["style"] = json(
            "flex" to "1",
            "minHeight" to "250px",
            "width" to "100%"
        )

        console.info("Reload LPEditor")
        console.info("withObjectString", props.objectString)

        // Editor
        Editor {
            attrs.height = "250px"
            attrs.value = editorString
            attrs.onChange = { value, _ -> setEditorString(value) }
            attrs.defaultLanguage = "html"
            attrs.onMount = { editor, monaco -> init(editor, monaco) }
            attrs.options = json("semanticHighlighting.enabled" to true)
            attrs.theme = "lp-theme"
        }
    }

}