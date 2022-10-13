package org.fandev.lang.fan.psi.impl;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeVisitor;
import com.intellij.psi.search.GlobalSearchScope;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class FanFuncType
        extends PsiType {
    private FanFuncTypeElement element;

    public FanFuncType(FanFuncTypeElement element) {
        super(PsiAnnotation.EMPTY_ARRAY);
        this.element = element;
    }

    public PsiType getReturnType() {
        return this.element.getReturnType().getType();
    }

    public FanTypeDefinition getFuncType() {
        return this.element.getFuncType();
    }

    public String getPresentableText() {
        return this.element.getText();
    }

    public String getCanonicalText() {
        return this.element.getText();
    }

    public String getInternalCanonicalText() {
        return this.element.getText();
    }

    public boolean isValid() {
        return false;
    }

    public boolean equalsToText(@NonNls String s) {
        return false;
    }

    public <A> A accept(PsiTypeVisitor<A> aPsiTypeVisitor) {
        return null;
    }

    public GlobalSearchScope getResolveScope() {
        return null;
    }

    @NotNull
    public PsiType[] getSuperTypes() {
        return new PsiType[0];
    }
}