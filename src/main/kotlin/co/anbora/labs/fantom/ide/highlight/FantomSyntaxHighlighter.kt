package co.anbora.labs.fantom.ide.highlight

import co.anbora.labs.fantom.lang.lexer.FantomLexer
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class FantomSyntaxHighlighter: SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = FantomLexer()

    override fun getTokenHighlights(
        tokenType: IElementType?
    ): Array<TextAttributesKey> = pack(tokenType.textAttributesKey())
}
