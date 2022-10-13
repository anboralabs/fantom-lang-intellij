package org.fandev.lang.fan.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiQualifiedReference;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;

public interface FanReferenceElement extends FanElement, PsiQualifiedReference, PsiPolyVariantReference {
    PsiElement getReferenceNameElement();

    @NotNull
    PsiType[] getTypeArguments();
}