package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

public interface FanListTypeElement extends FanTypeElement {
  FanTypeDefinition getListType();
  
  FanClassTypeElement getTypeElement();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/types/FanListTypeElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */