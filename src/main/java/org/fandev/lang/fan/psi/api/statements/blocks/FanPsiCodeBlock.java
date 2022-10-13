package org.fandev.lang.fan.psi.api.statements.blocks;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import org.fandev.lang.fan.psi.FanElement;

public interface FanPsiCodeBlock extends FanElement, PsiCodeBlock {
    PsiElement getLeftBrace();

    PsiElement getRightBrace();
}