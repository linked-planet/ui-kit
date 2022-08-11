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
package com.linkedplanet.uikit.wrapper.lpeditor

import kotlinx.js.Object
import kotlinx.js.ReadonlyArray
import react.*
import kotlin.js.Json
import kotlin.js.json

/**
 * Enables Syntax highlighting in the editor for a json object hierarchy provided through objectString
 *
 * @property objectString: a single Json Object as String used for auto-completion and highlighting in the editor
 */
external interface LPEditorProps : EditorProps {
    var objectString: String
    var highlightColor: String?
    var fontStyle: String?
}

val LPEditor = fc<LPEditorProps> { props ->

    val modelRef: MutableRefObject<dynamic> = useRef(null)
    fun forceReloadTokens() {
        val model = modelRef.current ?: return
        model.setValue(model.getValue()) // https://stackoverflow.com/questions/69664000/monaco-editor-how-to-recompute-and-redraw-semantic-highlighting-data
    }

    // do not use items directly, use itemsRef or updateItems
    val (_items, _setItems) = useState(itemsFromObjectString(props.objectString))
    val itemsRef = useRef(_items)
    fun updateItems(items: List<Item>) {
        itemsRef.current = items
        _setItems(items)
        forceReloadTokens()
    }

    useEffect(props.objectString) {
        updateItems(itemsFromObjectString(props.objectString))
    }

    fun suggestions(jsonObject: String): Array<Suggestion> {

        fun suggestionForItem(lbl: Item): Suggestion {
            fun escape(value: String) = MarkdownString.escapeMarkdownSyntaxTokens(value)
            return Suggestion(
                label = lbl.key,
                kind = CompletionItemKind.Text,
                documentation = MarkdownString("${lbl.valueType}: **${escape(lbl.key)}** = ${escape(lbl.value)}"),
                insertText = lbl.key
            )
        }

        val currentItems = itemsRef.current ?: return emptyArray()
        return currentItems
            .filter { it.parent == jsonObject }
            .map { suggestionForItem(it) }.toTypedArray()
    }

    /**
     * see https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItemProvider.html#provideCompletionItems
     */
    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideCompletionItems(model: dynamic, position: Position, token: dynamic): Json {
        val textUntilPosition: String =
            model.getValueInRange(MonacoRange(position.lineNumber, position.lineNumber, 1, position.column)) as String

        var jsonObject = "\$" + textUntilPosition.substringAfterLast("\$", missingDelimiterValue = "")

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
                        "foreground" to (props.highlightColor ?: "#0052CC"),
                        "fontStyle" to (props.fontStyle ?: "bold"),
                    )
                )
            )
        )
    }

    /**
     * If the hierarchy is a prefix found inside a flatObject Item return the length of the Item
     *
     * @param matchResult: a json key hierarchy (found by the regex) $object.Name,
     */
    fun lengthOfLongestMatchingItem(matchResult: MatchResult): Int =
        itemsRef.current!!
            .map { it.parent + it.key }
            .filter { fullPrefix -> matchResult.value.startsWith(fullPrefix) }
            .map { it.length }
            .maxOrNull() ?: 0

    /**
     * matches $object $object.name $a.b.c but stops at whitespace or the html open bracket "<"
     */
    val matchesJsonKeyHierarchiesStartingWithDollar = Regex("""\$([^=;:?"]*\.)*([^=;:?."]*)""")

    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
    fun provideDocumentSemanticTokens(model: dynamic, lastResultId: String?, token: dynamic): Json {
        modelRef.current = model // remember the model reference for forceUpdate
        val lineContents = model.getLinesContent() as Array<String>

        return lineContents.foldIndexed(SemanticTokenArrayAccumulator()) { lineNumber, allTokens, lineContent: String ->
            matchesJsonKeyHierarchiesStartingWithDollar
                .findAll(lineContent)
                .fold(allTokens) { lineTokens, matchResult ->
                    val hitLength = lengthOfLongestMatchingItem(matchResult)
                    if (hitLength > 0) {
                        val hitPos = matchResult.range.first
                        lineTokens.addTokenWithAbsolutePosition(lineNumber, hitPos, hitLength, 0)
                    }
                    return@fold lineTokens
                }
            return@foldIndexed allTokens
        }.dataAsJsonObject()
    }


    fun registerDocumentSemanticTokensProvider(monaco: Monaco): IDisposable {
        return monaco.languages.registerDocumentSemanticTokensProvider("html", json(
            "getLegend" to { TokenLegend(emptyList(/*no modifiers*/), arrayOf("lp-editor-token-type")) },
            "provideDocumentSemanticTokens" to ::provideDocumentSemanticTokens,
            "releaseDocumentSemanticTokens" to { resultId: String -> /*doNothing*/ }
        ))
    }

    fun registerCompletionItemProvider(monaco: Monaco): IDisposable {
        return monaco.languages.registerCompletionItemProvider(
            "html", json(
                "provideCompletionItems" to ::provideCompletionItems
                //optional  "resolveCompletionItem" to ...
            )
        )
    }

    val (disposeProviders, setDisposeProviders) = useState<IDisposable>()

    fun init(editor: dynamic, monaco: Monaco) {
        val completionDisposable = registerCompletionItemProvider(monaco)
        val tokenDisposable = registerDocumentSemanticTokensProvider(monaco)
        prepareEditorTheme(editor) // overrides the theme, so no dispose necessary

        setDisposeProviders(IDisposable {
            completionDisposable.dispose()
            tokenDisposable.dispose()
        })
    }

    useEffect(disposeProviders) {// will be called when component is dismounted
        disposeProviders?.let { this.cleanup { it.dispose() } }
    }

    Editor {
        attrs.height = props.height
        attrs.width = props.width
        attrs.defaultLanguage = props.defaultLanguage
        attrs.defaultValue = props.defaultValue
        attrs.value = props.value
        attrs.onMount = props.onMount
        attrs.onChange = props.onChange
        attrs.beforeMount = { monaco -> init(monaco.editor, monaco) }
        attrs.options = json("semanticHighlighting.enabled" to true)
        attrs.theme = "lp-editor-theme"
    }
}

/**
 * Represents a key inside a json hierarchy.
 * Parent contains the whole hierarchy up to the root object
 *
 * e.g. { parent: "$object.Name.", key: "First", value: "inception" , valueType: String}
 */
data class Item(val parent: String, val key: String, val value: String, val valueType: String)

/**
 * Generates flat Items from nested Json objects, where parent contains the whole path to the root object.
 *
 * @param obj a dynamic json object e.g. { "object": { "Name": { "First" : "inception" } } }
 * @return a list of flat items e.g.:
 *         0: Object { parent: "$object.Name.", key: "First", value: "inception", valueType: String }
 *         1: Object { parent: "$object.", key: "Name", value: "First", "valueType": Object  }
 *         2: Object { parent: "$", key: "object", value: "Name", "valueType": Object  }
 */
fun flatObject(parentKey: String, obj: dynamic): List<Item> {
    val keys: ReadonlyArray<String> = Object.keys(obj as Any)
    return keys.flatMap { key: String ->
        val value = obj[key]
        if (value == null || value == undefined) {
            listOf(Item(parentKey, key, "", valueType = "None"))
        } else if (Object.keys(value as Any).isNotEmpty() && value !is String) {
            flatObject("$parentKey$key.", value)
                .plus(Item(parentKey, key, Object.keys(value).toString(), "Object"))
        } else {
            listOf(Item(parentKey, key, value.toString(), value::class.simpleName ?: ""))
        }
    }
}

fun itemsFromObjectString(objectString: String): List<Item> {
    try {
        val jsonObject = JSON.parse<dynamic>(objectString)
        return flatObject("$", jsonObject)
    } catch (exception: dynamic) {
        console.error("LPEditor received invalid JSON Object")
    }
    return emptyList()
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
    fun addTokenWithAbsolutePosition(
        lineNumber: Int,
        hitPos: Int,
        hitLength: Int,
        tokenTypeIndex: Int,
        tokenModifierSet: Int = 0
    ) {
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