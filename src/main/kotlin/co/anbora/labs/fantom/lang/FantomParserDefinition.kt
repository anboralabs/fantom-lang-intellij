package co.anbora.labs.fantom.lang

import co.anbora.labs.fantom.lang.core.FANTOM_COMMENTS
import co.anbora.labs.fantom.lang.core.FantomTokenType
import co.anbora.labs.fantom.lang.core.parser.FantomParser
import co.anbora.labs.fantom.lang.core.psi.FantomFile
import co.anbora.labs.fantom.lang.core.psi.FantomTypes
import co.anbora.labs.fantom.lang.lexer.FantomLexer
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class FantomParserDefinition: ParserDefinition {

    private val type = IFileElementType(FantomLanguage)

    override fun createLexer(project: Project?): Lexer = FantomLexer()

    override fun createParser(project: Project?): PsiParser = FantomParser()

    override fun getFileNodeType(): IFileElementType = type

    override fun getCommentTokens(): TokenSet = FANTOM_COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement = FantomTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = FantomFile(viewProvider)

    companion object {
        @JvmField val C_STYLE_COMMENT = FantomTokenType("C_STYLE_COMMENT")
        @JvmField val END_OF_LINE_COMMENT = FantomTokenType("END_OF_LINE_COMMENT")
        //@JvmField val FANDOC_LINE_COMMENT = FantomTokenType("FANDOC_LINE_COMMENT")
        @JvmField val SHABENG = FantomTokenType("SHABENG")
        //@JvmField val DSL_STRING = FantomTokenType("DSL_STRING")

        @JvmField val FAN_SYS_TYPE = FantomTokenType("FAN_SYS_TYPE")
    }
}