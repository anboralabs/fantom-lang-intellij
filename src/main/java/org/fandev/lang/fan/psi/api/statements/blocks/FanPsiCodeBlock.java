package org.fandev.lang.fan.psi.api.statements.blocks;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import org.fandev.lang.fan.psi.FanElement;

public interface FanPsiCodeBlock extends FanElement, PsiCodeBlock {
  PsiElement getLeftBrace();
  
  PsiElement getRightBrace();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/blocks/FanPsiCodeBlock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */