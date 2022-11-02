package co.anbora.labs.fantom.lang.core

import co.anbora.labs.fantom.lang.FantomLanguage
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.C_STYLE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.END_OF_LINE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.FAN_SYS_TYPE
import co.anbora.labs.fantom.lang.core.psi.FantomTypes.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class FantomTokenType(debugName: String) : IElementType(debugName, FantomLanguage)

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)

val FANTOM_COMMENTS = tokenSetOf(C_STYLE_COMMENT, END_OF_LINE_COMMENT, FAN_SYS_TYPE, FANDOC_LINE_COMMENT)

val NUMERIC_LITERALS = tokenSetOf(
    INT_LITERAL,
    FLOAT_LITERAL,
    DECIMAL_LITERAL,
    DURATION_LITERAL
)

val STRING_LITERALS = tokenSetOf(
    URI_LITERAL,
    STRING_LITERAL,
    DSL_STRING,
    CHAR_LITERAL
)

val KEYWORDS = TokenSet.create(
    ABSTRACT,
    AS,
    BREAK,
    CASE,
    CATCH,
    CLASS,
    CONST,
    CONTINUE,
    DEFAULT,
    ELSE,
    ENUM,
    FALSE,
    FINAL,
    FINALLY,
    FOR,
    IF,
    INTERNAL,
    IS,
    ISNOT,
    MIXIN,
    NATIVE,
    NEW,
    NULL,
    ONCE,
    OVERRIDE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    READONLY,
    RETURN,
    STATIC,
    SUPER,
    SWITCH,
    THIS,
    THROW,
    TRUE,
    TRY,
    USING,
    VIRTUAL,
    WHILE
)
