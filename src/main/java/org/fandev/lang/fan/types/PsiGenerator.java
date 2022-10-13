package org.fandev.lang.fan.types;

import com.intellij.lang.ASTNode;

public interface PsiGenerator<T extends com.intellij.psi.PsiElement> {
  T construct(ASTNode paramASTNode);
}