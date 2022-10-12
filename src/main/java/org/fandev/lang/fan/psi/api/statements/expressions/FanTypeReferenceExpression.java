package org.fandev.lang.fan.psi.api.statements.expressions;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.Nullable;

public interface FanTypeReferenceExpression {
  @Nullable
  FanTypeDefinition getReferencedType();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/expressions/FanTypeReferenceExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */