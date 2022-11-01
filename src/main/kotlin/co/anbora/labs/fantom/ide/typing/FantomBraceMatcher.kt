package co.anbora.labs.fantom.ide.typing

import co.anbora.labs.fantom.lang.core.psi.FantomTypes.*
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

private val bracePairs = arrayOf(
    BracePair(LPAR, RPAR, true),
    BracePair(LBRACKET, RBRACKET, false),
    BracePair(LBRACE, RBRACE, true))

class FantomBraceMatcher: PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = bracePairs

    override fun isPairedBracesAllowedBeforeType(
        lbraceType: IElementType,
        contextType: IElementType?
    ): Boolean = true

    override fun getCodeConstructStart(
        file: PsiFile?,
        openingBraceOffset: Int
    ): Int = openingBraceOffset
}
