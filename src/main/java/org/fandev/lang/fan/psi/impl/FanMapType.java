package org.fandev.lang.fan.psi.impl;

import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeVisitor;
import com.intellij.psi.search.GlobalSearchScope;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class FanMapType
        extends PsiType {
    private final FanTypeElement keyType;
    private final FanTypeElement valueType;
    private final String text;
    private FanMapTypeElement element;

    public FanMapType(FanMapTypeElement element, FanTypeElement keyType, FanTypeElement valueType) {
        super(new com.intellij.psi.PsiAnnotation[0]);
        this.element = element;
        this.keyType = keyType;
        this.valueType = valueType;
        this.text = "[" + keyType.getType().getPresentableText() + ":" + valueType.getType().getPresentableText() + "]";
    }

    public FanTypeDefinition getMapType() {
        return this.element.getMapType();
    }

    public String getPresentableText() {
        return this.text;
    }

    public String getCanonicalText() {
        return this.text;
    }

    public String getInternalCanonicalText() {
        return this.text;
    }

    public boolean isValid() {
        return true;
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