package org.fandev.lang.fan.psi.api.statements.params;

import com.intellij.psi.PsiParameter;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.jetbrains.annotations.Nullable;

public interface FanParameter extends PsiParameter {
    @Nullable
    FanDefaultValue getDefaultValue();
}
