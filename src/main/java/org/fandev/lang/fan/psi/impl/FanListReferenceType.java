package org.fandev.lang.fan.psi.impl;

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
import org.jetbrains.annotations.NotNull;


public class FanListReferenceType
        extends PsiArrayType {
    private FanListTypeElement element;

    public FanListReferenceType(FanListTypeElement element, @NotNull PsiType psiType) {
        super(psiType);
        this.element = element;
    }

    public FanTypeDefinition getListType() {
        return this.element.getListType();
    }

    public PsiType getType() {
        return this.element.getType();
    }

    public FanClassTypeElement getTypeElement() {
        return this.element.getTypeElement();
    }
}