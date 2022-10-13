package org.fandev.lang.fan.psi.api.types;

import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

public interface FanTypeElement extends FanElement {
    @NotNull
    PsiType getType();
}
