package co.anbora.labs.fantom.ide.annotator

import co.anbora.labs.fantom.ide.color.FantomColors
import co.anbora.labs.fantom.lang.core.psi.FantomDotCall
import co.anbora.labs.fantom.lang.core.psi.FantomFacetItems
import co.anbora.labs.fantom.lang.core.psi.FantomFormalFull
import co.anbora.labs.fantom.lang.core.psi.FantomList
import co.anbora.labs.fantom.lang.core.psi.FantomLocalDef
import co.anbora.labs.fantom.lang.core.psi.FantomMethodDef
import co.anbora.labs.fantom.lang.core.psi.FantomParam
import co.anbora.labs.fantom.lang.core.psi.FantomSimple
import co.anbora.labs.fantom.lang.core.psi.FantomSimpleType
import co.anbora.labs.fantom.lang.core.psi.FantomSlotExpr
import co.anbora.labs.fantom.lang.core.psi.FantomStaticCall
import co.anbora.labs.fantom.lang.core.psi.FantomType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class HighlightingAnnotator: Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val color = when {
            (element.parent is FantomStaticCall || element.parent is FantomDotCall) -> highlightMethodCalls(element)
            element.parent is FantomFacetItems && element is FantomSimpleType -> FantomColors.ANNOTATIONS
            element is FantomType -> highlightTypes(element)
            else -> null
        } ?: return
        val severity = color.testSeverity
        holder.newSilentAnnotation(severity).textAttributes(color.textAttributesKey).create()
    }

    private fun highlightMethodCalls(element: PsiElement): FantomColors? {
        return if (element is FantomSlotExpr) {
            FantomColors.METHOD_CALL
        } else null
    }

    private fun highlightTypes(element: PsiElement): FantomColors? {
        return if (element.parent is FantomSimple || element.parent is FantomList) {
            null
        } else FantomColors.TYPES
    }
}
