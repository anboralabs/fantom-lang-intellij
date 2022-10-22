package co.anbora.labs.fantom.lang.core.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

interface FantomElement: PsiElement

abstract class FantomElementImpl(
    node: ASTNode
) : ASTWrapperPsiElement(node), FantomElement
