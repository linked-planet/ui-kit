//package com.linkedplanet.uikit.operator
//
//import com.linkedplanet.uikit.imports.Position
//import com.linkedplanet.uikit.imports.loader
//import kotlin.js.Json
//import kotlin.js.Promise
//import kotlin.js.json
//
//object EditorOperator {
//
//    private var items: List<Item>? = null
//
////    init {
////        (loader.init() as Promise<dynamic>).then { monaco: dynamic ->
////            console.info("loader.init() promise resolved with monaco", monaco)
////            val editor = monaco.editor
////
////            prepareEditor(editor)
////            prepareMonaco(monaco)
////        }
////    }
//
//    fun init(editor: dynamic, monaco: dynamic) {
//        console.info("init from onMount", monaco)
//        if(editor == null && monaco == null) {
//            prepareEditor(editor)
//            prepareMonaco(monaco)
//        }
//    }
//
//    private fun prepareEditor(editor: dynamic) {
//        editor.defineTheme(
//            "lp-theme", json(
//                "base" to "vs",
//                "inherit" to true,
//                "colors" to json(
////                    "editor.background" to "#7CFC00",
//                ),
//                "rules" to arrayOf(
//                    json(
//                        "token" to "myTokenType",
//                        "foreground" to "#ff0000",
//                        "fontStyle" to "italic",
//                        "underline" to true,
//                    )
//                )
//            )
//        )
//    }
//
//    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
//    private fun suggestions(textFromLastJsonObject: String): Array<Json> {
//        val items: List<Item> = this.items ?: return emptyArray()
//
//        return items
//            .filter { it.parent == textFromLastJsonObject }
//            .map { suggestionForLabel(it.key) }.toTypedArray()
//    }
//
//    private fun suggestionForLabel(word: String) = json(
//        "label" to word, // label is shown in the gui
//        "kind" to "CompletionItemKind.Text", // https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.CompletionItemKind.html
//        "documentation" to "doc",
//        "insertText" to word // the text that inserted after selecting the shown label
//    //                "range" to "range"
//    )
//
//    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
//    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItemProvider.html#provideCompletionItems
//    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
//    private fun provideCompletionItems(model: dynamic, position: Position, token: dynamic): Json {
//        console.info("provideCompletionItems", position)
//
//        val textUntilPosition:String =
//            model.getValueInRange(range(position.lineNumber, position.lineNumber, 1, position.column)) as String
//        console.info("textUntilPosition", textUntilPosition)
//
//        var jsonObject = "\$" + textUntilPosition.substringAfterLast("\$", missingDelimiterValue = "")
//        console.info("textFromLastJsonObject", jsonObject)
//
//        if (jsonObject.contains(".")){
//            jsonObject = jsonObject.substringBeforeLast(".") + "."
//        }
//
//        if (jsonObject.isEmpty()) {
//            return json("suggestions" to arrayOf<Json>())
//        }
//
//        return json("suggestions" to suggestions(jsonObject))
//    }
//
//    private fun prepareMonaco(monaco: dynamic) {
//        console.info("prepareMonaco")
//
//        monaco.languages.registerCompletionItemProvider("html", json (
//            "provideCompletionItems" to this::provideCompletionItems
////optional  "resolveCompletionItem" to ...
//        ))
//
//        monaco.languages.registerDocumentHighlightProvider("html", json (
//            "provideDocumentHighlights" to this::provideDocumentHighlights // user selects something -> highlight other parts of the document
//        ))
//
//        monaco.languages.registerDocumentSemanticTokensProvider("html", json(
//            "getLegend" to this::getLegend,
//            "provideDocumentSemanticTokens" to this::provideDocumentSemanticTokens,
//            "releaseDocumentSemanticTokens" to { resultId: String -> /*doNothing*/ }
//        ))
//    }
//
//    private fun getLegend(): Json {
//        return json(
//            "tokenModifiers" to arrayOf<String>(),
//            "tokenTypes" to arrayOf("myTokenType") // We have only 1 Token with Index 0
//        )
//    }
//
//    class SemanticTokenArrayAccumulator(
//        private var previousLine: Int = 0, // result needs the differences between hits, so remember the line of the previous token
//        private var previousPos: Int = 0, // result needs the differences between hits, so remember the position of the previous token
//        private val tokens: MutableList<Int> = listOf<Int>().toMutableList()
//    ) {
//         fun addTokenWithAbsolutePosition(lineNumber: Int, hitPos: Int, hitLength: Int, tokenTypeIndex: Int, tokenModifierSet: Int = 0) {
//            val deltaPos = if (lineNumber == previousLine) hitPos - previousPos else hitPos
//            val deltaLine = lineNumber - previousLine
//            tokens.addAll(listOf(deltaLine, deltaPos, hitLength, tokenTypeIndex, tokenModifierSet))
//            previousLine = lineNumber
//            previousPos = hitPos
//        }
//
//        fun dataAsJsonObject(): Json {
//            val dataAsArray = tokens.toIntArray()
//            return json(
//                "data" to dataAsArray,  /*deltaLine*/ /*deltaStart*/ /*distance*/ /*tokenTypeIndex*/ /*tokenModifierSet*/
//                // 	const tokenCount = (tokens.data.length / 5) | 0;
//            )
//        }
//    }
//
//    private val matchesJsonKeyHierarchiesStartingWithDollar = Regex("""\$([^\s<]*\.)*([^\s<]*)""")
//
//    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
//    private fun provideDocumentSemanticTokens(model: dynamic, lastResultId: String?, token: dynamic ): Json {
//        val lineContents = model.getLinesContent() as Array<String>
//
//        return lineContents.foldIndexed(SemanticTokenArrayAccumulator()) { lineNumber, allTokens, lineContent: String ->
//            matchesJsonKeyHierarchiesStartingWithDollar
//                .findAll(lineContent)
//                .fold(allTokens) { lineTokens, matchResult ->
//                    if (areMatchesReallyKeys(matchResult)) {
//                        val hitPos = matchResult.range.first
//                        val hitLength = matchResult.range.last - hitPos + 1
//                        lineTokens.addTokenWithAbsolutePosition(lineNumber, hitPos, hitLength, 0)
//                    }
//                    return@fold lineTokens
//                }
//            return@foldIndexed allTokens
//        }.dataAsJsonObject()
//    }
//
//    /**
//     * The regex found some json variables e.g. $object.Name, lets see if the chain is actually defined in the json
//     */
//    private fun areMatchesReallyKeys(matchResult: MatchResult): Boolean {
//        val items: List<Item> = this.items ?: return false
//        console.info("matchResult:", matchResult)
//        console.info("items:", items)
//        if (items.any { it.parent + it.key == matchResult.value }) return true
//        return false
//
//    }
//
//    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.DocumentHighlight.html
//    // https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.DocumentHighlightProvider.html
//    @Suppress("UNUSED_PARAMETER") // since they are available inside the js
//    private fun provideDocumentHighlights(model: dynamic, position: dynamic, token: dynamic): Array<Json> {
//        return createHighlights()
//    }
//
//    private fun createHighlights(): Array<Json> {
//        console.info("createHighlights")
//        return arrayOf(
//            json(
//                "kind" to "DocumentHighlightKind.Text", // https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.CompletionItemKind.html
//                "range" to range(1, 1, 3, 8)
//            )
//        )
//    }
//
//    private fun range(startLine: Int, endLine: Int, startColumn: Int, endColumn: Int): Json = json(
//        "startLineNumber" to startLine,
//        "endLineNumber" to endLine,
//        "startColumn" to startColumn,
//        "endColumn" to endColumn
//    )
//
//    data class Item(val parent: String, val key: String, val value: String)
//
//    fun setFlatObjects(items: List<Item>) {
//        this.items = items
//    }
//
//
//}