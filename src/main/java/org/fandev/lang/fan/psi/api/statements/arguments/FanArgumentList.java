package org.fandev.lang.fan.psi.api.statements.arguments;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

public interface FanArgumentList extends FanElement {
  FanArgument[] getArguments();
  
  int indexOf(@NotNull FanArgument paramFanArgument);
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/arguments/FanArgumentList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */