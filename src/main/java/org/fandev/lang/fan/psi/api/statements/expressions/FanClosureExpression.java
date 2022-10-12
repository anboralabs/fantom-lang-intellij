package org.fandev.lang.fan.psi.api.statements.expressions;

import com.intellij.psi.PsiElement;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;

public interface FanClosureExpression extends PsiElement {
  FanFuncTypeElement getFunction();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/expressions/FanClosureExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */