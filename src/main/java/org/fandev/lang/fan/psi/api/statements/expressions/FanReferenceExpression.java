package org.fandev.lang.fan.psi.api.statements.expressions;

import com.intellij.psi.PsiNamedElement;
import org.fandev.lang.fan.psi.FanReferenceElement;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FanReferenceExpression extends FanExpression, FanReferenceElement, PsiNamedElement {
  @Nullable
  FanExpression getQualifierExpression();
  
  @NotNull
  FanResolveResult[] getSameNameVariants();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/expressions/FanReferenceExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */