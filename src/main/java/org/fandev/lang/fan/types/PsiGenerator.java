package org.fandev.lang.fan.types;

import com.intellij.lang.ASTNode;

public interface PsiGenerator<T extends com.intellij.psi.PsiElement> {
  T construct(ASTNode paramASTNode);
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/types/PsiGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */