package com.linkedplanet.uikit.component

import CompletionItemKind
import MonacoRange
import Suggestion
import TokenLegend
import com.linkedplanet.uikit.imports.Editor
import com.linkedplanet.uikit.imports.Position
import kotlinx.js.Object
import kotlinx.js.ReadonlyArray
import react.*
import react.dom.div
import kotlin.js.Json
import kotlin.js.json

/**
 * Enables Syntax highlighting in the editor for a json object hierarchy provided through objectString
 */
external interface LPEditorProps : Props {
    var editorString: String
    var objectString: String
    var onChange: (String) -> Unit
}

val LPEditor = fc<LPEditorProps> { props ->

    console.info("LPEditor with editorString:${props.editorString}  objectString:${props.objectString}")

    val (editorString, setEditorString) = useState(props.editorString)

    // do not use items directly, use itemsRef or updateItems
    val (_items, _setItems) = useState(itemsFromObjectString(props.objectString))

    val itemsRef = useRef(_items)

    fun updateItems(items : List<Item>){
        itemsRef.current = items
        _setItems(items)
    }

    useEffect(props.objectString) {
        console.info("UseEffect for ObjectString")
        updateItems(itemsFromObjectString(props.objectString))
        //TODO: somehow force monaco to refresh SemanticTokens
    }

    useEffect(arrayOf(editorString)) {
        console.info("useEffect editorString", editorString)
        props.onChange(editorString)
    }

    fun suggestions(jsonObject: String): Array<Suggestion> {
        fun suggestionForLabel(lbl: String) = Suggestion(lbl, CompletionItemKind.Text, lbl, lbl)

        val currentItems = itemsRef.current ?: return emptyArray()
        return currentItems
            .filter { it.parent == jsonObject }
            .map { suggestionForLabel(it.key) }.toTypedArray()
    }

    /**
     * see https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItemProvider.html#provideCompletionItems
     */
    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideCompletionItems(model: dynamic, position: Position, token: dynamic): Json {
        console.info("provideCompletionItems", position)

        val textUntilPosition: String =
            model.getValueInRange(MonacoRange(position.lineNumber, position.lineNumber, 1, position.column)) as String
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

    fun prepareEditorTheme(editor: dynamic) {
        editor.defineTheme(
            "lp-editor-theme", json(
                "base" to "vs",
                "inherit" to true,
                "colors" to json(
                    //"editor.background" to "#7CFC00"
                ),
                "rules" to arrayOf(
                    json(
                        "token" to "lp-editor-token-type",
                        "foreground" to "#00FF00",
                        "fontStyle" to "italic",
                        "underline" to true,
                    )
                )
            )
        )
    }

    /**
     * If the found key hierarchy is found inside a flatObject Item
     *
     * @param matchResult: a json key hierarchy (found by the regex) $object.Name,
     */
    fun areMatchesReallyKeys(matchResult: MatchResult): Boolean =
        (itemsRef.current?.any { it.parent + it.key == matchResult.value })
            .also { wasFound ->
                console.info("matchResult:", matchResult)
                console.info("items:", itemsRef.current)
                console.info("found any?", wasFound)
            } ?: false

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

    fun prepareMonaco(monaco: dynamic) {
        console.info("prepareMonaco")

        monaco.languages.registerCompletionItemProvider(
            "html", json(
                "provideCompletionItems" to ::provideCompletionItems
//optional  "resolveCompletionItem" to ...
            )
        )

        monaco.languages.registerDocumentSemanticTokensProvider("html", json(
            "getLegend" to { TokenLegend(emptyList(/*no modifiers*/), arrayOf("lp-editor-token-type")) },
            "provideDocumentSemanticTokens" to ::provideDocumentSemanticTokens,
            "releaseDocumentSemanticTokens" to { resultId: String -> /*doNothing*/ }
        ))
    }

    fun init(editor: dynamic, monaco: dynamic) {
        console.info("init: Preparing Monaco-Editor")
        console.info("items", itemsRef.current)
        console.info("Editor", editor)
        console.info("Monaco.Editor", monaco.editor)
        console.info("Monaco", monaco)
        prepareMonaco(monaco)
        prepareEditorTheme(editor) // does not work in init
    }

    div {
        attrs["style"] = json(
            "flex" to "1",
            "minHeight" to "250px",
            "width" to "100%"
        )

        console.info("Reload LPEditor withObjectString", props.objectString)

        // Editor
        Editor {
            attrs.height = "250px"
            attrs.value = editorString
            attrs.onChange = { value, _ -> setEditorString(value) }
            attrs.defaultLanguage = "html"
            attrs.beforeMount = { monaco -> init(monaco.editor, monaco) }
            attrs.options = json("semanticHighlighting.enabled" to true)
            attrs.theme = "lp-editor-theme"
        }
    }

}

/**
 * Represents a key inside a json hierarchy.
 * Parent contains the whole hierarchy up to the root object
 *
 * e.g. { parent: "$object.Name.", key: "First", value: "inception" }
 */
data class Item(val parent: String, val key: String, val value: String)

/**
 * Generates flat Items from nested Json objects, where parent contains the whole path to the root object.
 *
 * @param obj a dynamic json object e.g. { "object": { "Name": { "First" : "inception" } } }
 * @return a list of flat items e.g.:
 *         0: Object { parent: "$object.Name.", key: "First", value: "inception" }
 *         1: Object { parent: "$object.", key: "Name", value: "[object Object]" }
 *         2: Object { parent: "$", key: "object", value: "[object Object]" }
 */
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

/**
 * Allows to accumulate Semantic Tokens used for syntax highlighting
 * Each Token highlights a range of characters inside the editor
 *
 * In a sane world one would expect an Array<SemanticToken>, but Monaco uses a continuous array of Ints,
 * so the tokenCount is actually (tokens.data.length / 5) | 0;
 * To make it more complicated the Tokens are not stored with absolute values but each token contains
 * the distance from the last token. This class does handle all of this transparently.
 *
 * For more details please look into the vscode codebase in semanticTokensProviderStyling.ts toMultilineTokens2(...)
 */
class SemanticTokenArrayAccumulator(
    private var previousLine: Int = 0, // result needs the differences between hits, so remember the line of the previous token
    private var previousPos: Int = 0, // result needs the differences between hits, so remember the position of the previous token
    private val tokens: MutableList<Int> = listOf<Int>().toMutableList()
) {
    fun addTokenWithAbsolutePosition(lineNumber: Int, hitPos: Int, hitLength: Int, tokenTypeIndex: Int, tokenModifierSet: Int = 0) {
        console.info("addTokenWithAbsolutePosition lineNumber:$lineNumber hitPos$hitPos hitLength:$hitLength tokenTypeIndex:$tokenTypeIndex")
        val deltaPos = if (lineNumber == previousLine) hitPos - previousPos else hitPos
        val deltaLine = lineNumber - previousLine
        tokens.addAll(listOf(deltaLine, deltaPos, hitLength, tokenTypeIndex, tokenModifierSet))
        previousLine = lineNumber
        previousPos = hitPos
    }

    fun dataAsJsonObject(): Json {
        val dataAsArray = tokens.toIntArray()
        return json(
            "data" to dataAsArray,
        )
    }
}