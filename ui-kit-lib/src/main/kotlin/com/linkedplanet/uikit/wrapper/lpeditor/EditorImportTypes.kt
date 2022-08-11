package com.linkedplanet.uikit.wrapper.lpeditor

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
/**
 *
 * A com.linkedplanet.uikit.wrapper.lpeditor.Suggestion for autocompletion
 * https://microsoft.github.io/monaco-editor/api/interfaces/monaco.languages.CompletionItem.html
 *
 * @param label label is shown in the gui as a selection option
 * @param insertText what's inserted after selecting the shown label
 * @param documentation what's displayed when show more is clicked
 */
data class Suggestion(
    val label: String,
    val kind: CompletionItemKind,
    val documentation: MarkdownString, /*IMarkdownString*/
    val insertText: String // the text that
//    val range: String
)

/**
 * @param value a markdown string
 * https://microsoft.github.io/monaco-editor/api/interfaces/monaco.IMarkdownString.html
 */
data class MarkdownString(val value: String) {
    companion object {
        fun escapeMarkdownSyntaxTokens(text: String): String {
            // see vscode htmlContent.ts escapeMarkdownSyntaxTokens
            // escape markdown syntax tokens: http://daringfireball.net/projects/markdown/syntax#backslash
            return text.replace(Regex("[`*_{}\\[\\]()#+\\-!]")) { match -> "\\" + match.value };
        }
    }
}

/**
 * Used to tell monaco all available tokenTypes
 */
data class TokenLegend(
    val tokenModifiers: List<String>,
    val tokenTypes: Array<String>
)

/**
 * see https://microsoft.github.io/monaco-editor/api/enums/monaco.languages.com.linkedplanet.uikit.wrapper.lpeditor.CompletionItemKind.html
 */
enum class CompletionItemKind(val jsString: String) {
    Text("com.linkedplanet.uikit.wrapper.lpeditor.CompletionItemKind.Text")
}

data class MonacoRange(
    val startLineNumber: Int,
    val endLineNumber: Int,
    val startColumn: Int,
    val endColumn: Int,
)

fun interface IDisposable {
    fun dispose()
}