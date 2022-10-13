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