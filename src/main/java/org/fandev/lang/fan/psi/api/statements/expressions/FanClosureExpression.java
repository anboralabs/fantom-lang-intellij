package org.fandev.lang.fan.psi.api.statements.expressions;

import com.intellij.psi.PsiElement;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;

public interface FanClosureExpression extends PsiElement {
    FanFuncTypeElement getFunction();
}