package co.anbora.labs.fantom.ide.folding

import co.anbora.labs.fantom.lang.core.psi.*
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
        is FantomHighOrderBlock -> BLOCK_PLACE_HOLDER
        is FantomEnumBlock -> BLOCK_PLACE_HOLDER
        is FantomFieldDefBlock -> BLOCK_PLACE_HOLDER
        is FantomStatementBlock -> BLOCK_PLACE_HOLDER
        is FantomSwitchStmBlock -> BLOCK_PLACE_HOLDER
        else -> null
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private class FoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>): FantomVisitor() {

        override fun visitHighOrderBlock(o: FantomHighOrderBlock) = fold(o)

        override fun visitEnumBlock(o: FantomEnumBlock) = fold(o)

        override fun visitFieldDefBlock(o: FantomFieldDefBlock) = fold(o)

        override fun visitStatementBlock(o: FantomStatementBlock) = fold(o)

        override fun visitSwitchStmBlock(o: FantomSwitchStmBlock) = fold(o)

        private fun fold(element: PsiElement) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }
}