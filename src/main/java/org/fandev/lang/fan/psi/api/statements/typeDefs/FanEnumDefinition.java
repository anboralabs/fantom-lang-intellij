package org.fandev.lang.fan.psi.api.statements.typeDefs;

import com.intellij.psi.PsiClass;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;

public interface FanEnumDefinition extends FanTypeDefinition, PsiClass {
    FanEnumValue[] getEnumValues();
}
