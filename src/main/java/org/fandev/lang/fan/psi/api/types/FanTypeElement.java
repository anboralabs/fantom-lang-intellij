package org.fandev.lang.fan.psi.api.types;

import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

public interface FanTypeElement extends FanElement {
  @NotNull
  PsiType getType();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/types/FanTypeElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */