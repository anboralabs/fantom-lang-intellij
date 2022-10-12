package org.fandev.lang.fan

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
import org.fandev.lang.fan.parser.FanPsiCreator
import org.fandev.lang.fan.parsing.FanParser
import org.fandev.lang.fan.psi.impl.FanFileImpl

class FantomParserDefinition: ParserDefinition {
    override fun createLexer(project: Project?): Lexer = FantomLexer()

    override fun createParser(project: Project?): PsiParser = FanParser()

    override fun getFileNodeType(): IFileElementType = FanElementTypes.FILE

    override fun getWhitespaceTokens(): TokenSet = TokenSet.create(*arrayOf(FanTokenTypes.WHITE_SPACE))

    override fun getCommentTokens(): TokenSet = FanTokenTypes.COMMENTS

    override fun getStringLiteralElements(): TokenSet = FanTokenTypes.STRING_LITERALS

    override fun createElement(node: ASTNode?): PsiElement = FanPsiCreator.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = FanFileImpl(viewProvider)
}