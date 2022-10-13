package org.fandev.lang.fan.psi.impl.statements.blocks;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiStatement;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.jetbrains.annotations.NotNull;


public class FanPsiCodeBlockImpl
        extends ASTWrapperPsiElement
        implements FanPsiCodeBlock {
    public FanPsiCodeBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    public PsiStatement[] getStatements() {
        return (PsiStatement[]) findChildrenByClass(PsiStatement.class);
    }

    public PsiElement getFirstBodyElement() {
        return null;
    }

    public PsiElement getLastBodyElement() {
        return null;
    }

    public PsiJavaToken getLBrace() {
        return null;
    }

    public PsiJavaToken getRBrace() {
        return null;
    }

    public PsiElement getLeftBrace() {
        return findChildByType(FanTokenTypes.LBRACE);
    }

    public PsiElement getRightBrace() {
        return findChildByType(FanTokenTypes.RBRACE);
    }

    @Override
    public boolean shouldChangeModificationCount(PsiElement place) {
        return false;
    }
}
