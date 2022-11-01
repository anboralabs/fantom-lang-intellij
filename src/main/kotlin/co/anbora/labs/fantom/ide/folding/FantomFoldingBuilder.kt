package co.anbora.labs.fantom.ide.folding

import co.anbora.labs.fantom.lang.core.psi.FantomBlockCode
import co.anbora.labs.fantom.lang.core.psi.FantomCodeBlock
import co.anbora.labs.fantom.lang.core.psi.FantomVisitor
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

private const val BLOCK_PLACE_HOLDER = "{...}"

class FantomFoldingBuilder: FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val visitor = FoldingVisitor(descriptors)
        PsiTreeUtil.processElements(root) { it.accept(visitor); true }
        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? = when (node.psi) {
        is FantomBlockCode -> BLOCK_PLACE_HOLDER
        is FantomCodeBlock -> BLOCK_PLACE_HOLDER
        else -> null
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private class FoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>): FantomVisitor() {

        override fun visitBlockCode(o: FantomBlockCode) = fold(o)

        override fun visitCodeBlock(o: FantomCodeBlock) = fold(o)

        private fun fold(element: PsiElement) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }
}