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


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/FanReferenceElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */