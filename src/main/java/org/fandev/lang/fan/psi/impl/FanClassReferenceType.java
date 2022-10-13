package org.fandev.lang.fan.psi.impl;

import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FanClassReferenceType
        extends PsiClassType {
    private final FanCodeReferenceElement myReferenceElement;

    public FanClassReferenceType(FanCodeReferenceElement ref) {
        this(LanguageLevel.JDK_1_6, ref);
    }

    public FanClassReferenceType(LanguageLevel languageLevel, FanCodeReferenceElement ref) {
        super(languageLevel);
        this.myReferenceElement = ref;
    }

    public PsiClass resolve() {
        ResolveResult[] results = multiResolve();
        if (results.length == 1) {
            PsiElement only = results[0].getElement();
            return (only instanceof PsiClass) ? (PsiClass) only : null;
        }

        return null;
    }

    public FanTypeDefinition resolveFanType() {
        return (FanTypeDefinition) resolve();
    }


    private ResolveResult[] multiResolve() {
        return this.myReferenceElement.multiResolve(false);
    }

    public String getClassName() {
        return this.myReferenceElement.getReferenceName();
    }


    @NotNull
    public PsiType[] getParameters() {
        return PsiType.EMPTY_ARRAY;
    }

    @NotNull
    public PsiClassType.ClassResolveResult resolveGenerics() {
        return ClassResolveResult.EMPTY;
    }

    @NotNull
    public PsiClassType rawType() {
        return this;
    }

    public String getPresentableText() {
        return this.myReferenceElement.getReferenceName();
    }

    public String getCanonicalText() {
        return this.myReferenceElement.getReferenceName();
    }

    public String getInternalCanonicalText() {
        return getCanonicalText();
    }

    public boolean isValid() {
        return this.myReferenceElement.isValid();
    }

    public boolean equalsToText(@NonNls String text) {
        return (text.endsWith(getPresentableText()) && text.equals(getCanonicalText()));
    }


    @NotNull
    public GlobalSearchScope getResolveScope() {
        return this.myReferenceElement.getResolveScope();
    }

    @NotNull
    public LanguageLevel getLanguageLevel() {
        return this.myLanguageLevel;
    }

    public PsiClassType setLanguageLevel(LanguageLevel languageLevel) {
        return new FanClassReferenceType(languageLevel, this.myReferenceElement);
    }
}