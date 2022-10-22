package co.anbora.labs.fantom.lang.core

import co.anbora.labs.fantom.lang.FantomLanguage
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.C_STYLE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.END_OF_LINE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.FAN_SYS_TYPE
import co.anbora.labs.fantom.lang.core.psi.FantomTypes.FANDOC_LINE_COMMENT
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class FantomTokenType(debugName: String) : IElementType(debugName, FantomLanguage)

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)

val FANTOM_COMMENTS = tokenSetOf(C_STYLE_COMMENT, END_OF_LINE_COMMENT, FAN_SYS_TYPE, FANDOC_LINE_COMMENT)