package org.fandev.lang.fan.psi.api.statements.expressions;

import com.intellij.psi.PsiElement;

public interface FanIndexExpression extends PsiElement {
    int getIndex();
}