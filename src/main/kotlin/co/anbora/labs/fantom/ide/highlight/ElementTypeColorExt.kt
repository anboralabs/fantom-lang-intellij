package co.anbora.labs.fantom.ide.highlight

import co.anbora.labs.fantom.ide.color.FantomColors
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.C_STYLE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.END_OF_LINE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.FAN_SYS_TYPE
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.SHABENG
import co.anbora.labs.fantom.lang.core.KEYWORDS
import co.anbora.labs.fantom.lang.core.NUMERIC_LITERALS
import co.anbora.labs.fantom.lang.core.STRING_LITERALS
import co.anbora.labs.fantom.lang.core.psi.FantomTypes.FANDOC_LINE_COMMENT
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

fun IElementType?.textAttributesKey(): TextAttributesKey? = map(this)?.textAttributesKey

private fun map(tokenType: IElementType?): FantomColors? {
    return when (tokenType) {
        in KEYWORDS -> FantomColors.KEY_WORD
        in STRING_LITERALS -> FantomColors.STRINGS
        FAN_SYS_TYPE -> FantomColors.TYPES
        in NUMERIC_LITERALS -> FantomColors.NUMBERS
        SHABENG, FANDOC_LINE_COMMENT -> FantomColors.DOC_COMMENTS
        END_OF_LINE_COMMENT -> FantomColors.COMMENTS
        C_STYLE_COMMENT -> FantomColors.COMMENTS
        TokenType.BAD_CHARACTER -> FantomColors.BAD_CHAR
        else -> null
    }
}