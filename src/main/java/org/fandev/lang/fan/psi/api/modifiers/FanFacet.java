package org.fandev.lang.fan.psi.api.modifiers;

import com.intellij.psi.PsiElement;
import org.fandev.lang.fan.psi.api.statements.expressions.FanExpression;

public interface FanFacet extends PsiElement {
    String getName();

    FanExpression getValue();
}