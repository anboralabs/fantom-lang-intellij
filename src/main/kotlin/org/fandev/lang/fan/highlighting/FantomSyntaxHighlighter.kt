package org.fandev.lang.fan.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.fandev.ide.color.FantomColors
import org.fandev.lang.fan.FanTokenTypes.*
import org.fandev.lang.fan.FantomLexer

class FantomSyntaxHighlighter: SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = FantomLexer()

    override fun getTokenHighlights(
        tokenType: IElementType?
    ): Array<TextAttributesKey> = pack(map(tokenType)?.textAttributesKey)

    companion object {
        fun map(tokenType: IElementType?): FantomColors? =
            when (tokenType) {
                in FAN_KEYWORDS -> FantomColors.KEY_WORD
                in STRING_LITERALS -> FantomColors.STRINGS
                FAN_SYS_TYPE -> FantomColors.TYPES
                in NUMERIC_LITERALS -> FantomColors.NUMBERS
                FANDOC_LINE_COMMENT -> FantomColors.DOC_COMMENTS
                END_OF_LINE_COMMENT -> FantomColors.COMMENTS
                C_STYLE_COMMENT -> FantomColors.COMMENTS
                TokenType.BAD_CHARACTER -> FantomColors.BAD_CHAR
                else -> null
            }
    }
}