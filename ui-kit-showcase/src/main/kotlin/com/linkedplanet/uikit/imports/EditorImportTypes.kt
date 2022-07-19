/**
 *
 * A Suggestion for autocompletion
 * https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
 *
 * @param label label is shown in the gui as a selection option
 * @param insertText whats inserted after selecting the shown label
 */
data class Suggestion(
    val label: String,
    val kind: CompletionItemKind,
    val documentation: String,
    val insertText: String // the text that
//    val range: String
)

/**
 * Used to tell monaco all available tokenTypes
 */
data class TokenLegend(
    val tokenModifiers: List<String>,
    val tokenTypes: Array<String>
)

/**
 * see https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.CompletionItemKind.html
 */
enum class CompletionItemKind(val jsString: String) {
    Text("CompletionItemKind.Text")
}

data class MonacoRange(
    val startLineNumber: Int,
    val endLineNumber: Int,
    val startColumn: Int,
    val endColumn: Int,
)

