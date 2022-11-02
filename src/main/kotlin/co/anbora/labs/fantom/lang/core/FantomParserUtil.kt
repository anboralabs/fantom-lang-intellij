package co.anbora.labs.fantom.lang.core

import co.anbora.labs.fantom.lang.core.psi.FantomTypes.*
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiBuilderUtil
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.psi.tree.IElementType

object FantomParserUtil: GeneratedParserUtilBase() {

    @JvmStatic
    fun dynCallImpl(b: PsiBuilder, level: Int): Boolean = collapse(b, DYN_CALL, MINUS, GT)

    @JvmStatic
    fun safeDynCallImpl(b: PsiBuilder, level: Int): Boolean = collapse(b, SAFE_DYN_CALL, QUEST, MINUS, GT)

    @JvmStatic
    fun getKeyword(b: PsiBuilder, level: Int): Boolean =
        contextualKeyword(b, "get", GET)

    @JvmStatic
    fun setKeyword(b: PsiBuilder, level: Int): Boolean =
        contextualKeyword(b, "set", SET)

    @JvmStatic
    private fun collapse(b: PsiBuilder, tokenType: IElementType, vararg parts: IElementType): Boolean {
        // We do not want whitespace between parts, so firstly we do raw lookup for each part,
        // and when we make sure that we have desired token, we consume and collapse it.
        parts.forEachIndexed { i, tt ->
            if (b.rawLookup(i) != tt) return false
        }
        val marker = b.mark()
        val expectedLength = parts.size
        PsiBuilderUtil.advance(b, expectedLength)
        marker.collapse(tokenType)
        return true
    }

    private fun contextualKeyword(
        b: PsiBuilder,
        keyword: String,
        elementType: IElementType,
        nextElementPredicate: (IElementType?) -> Boolean = { it !in tokenSetOf() }
    ): Boolean {
        if (b.tokenType == elementType ||
            b.tokenType == IDENTIFIER && b.tokenText == keyword && nextElementPredicate(b.lookAhead(1))
        ) {
            b.remapCurrentToken(elementType)
            b.advanceLexer();
            return true
        }
        return false
    }
}
