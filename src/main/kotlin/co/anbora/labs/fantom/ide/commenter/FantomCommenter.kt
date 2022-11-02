package co.anbora.labs.fantom.ide.commenter

import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.C_STYLE_COMMENT
import co.anbora.labs.fantom.lang.FantomParserDefinition.Companion.END_OF_LINE_COMMENT
import co.anbora.labs.fantom.lang.core.psi.FantomTypes.FANDOC_LINE_COMMENT
import com.intellij.lang.CodeDocumentationAwareCommenter
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType

class FantomCommenter: CodeDocumentationAwareCommenter {
    override fun getLineCommentPrefix(): String = "//"

    override fun getBlockCommentPrefix(): String = "/*"

    override fun getBlockCommentSuffix(): String = "*/"

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null

    override fun getLineCommentTokenType(): IElementType = END_OF_LINE_COMMENT

    override fun getBlockCommentTokenType(): IElementType = C_STYLE_COMMENT

    override fun getDocumentationCommentTokenType(): IElementType = FANDOC_LINE_COMMENT

    override fun getDocumentationCommentPrefix(): String? = null

    override fun getDocumentationCommentLinePrefix(): String = "**"

    override fun getDocumentationCommentSuffix(): String? = null

    override fun isDocumentationComment(element: PsiComment?): Boolean = false
}
