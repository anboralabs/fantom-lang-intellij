package org.fandev.lang.fan.psi.api.statements.typeDefs;

import com.intellij.psi.PsiClass;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;

public interface FanEnumDefinition extends FanTypeDefinition, PsiClass {
  FanEnumValue[] getEnumValues();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/typeDefs/FanEnumDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */