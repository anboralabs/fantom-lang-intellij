package org.fandev.lang.fan.psi.api.statements.params;

import com.intellij.psi.PsiParameter;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.jetbrains.annotations.Nullable;

public interface FanParameter extends PsiParameter {
  @Nullable
  FanDefaultValue getDefaultValue();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/params/FanParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */