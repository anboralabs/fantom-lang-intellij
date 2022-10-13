package org.fandev.lang.fan.psi.impl;

import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiType;
import com.intellij.psi.search.GlobalSearchScope;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class FanEnumReferenceType
        extends PsiClassType {
    private final FanEnumDefinition myEnum;

    public FanEnumReferenceType(FanEnumDefinition myEnum) {
        this(LanguageLevel.JDK_1_6, myEnum);
    }

    public FanEnumReferenceType(LanguageLevel languageLevel, FanEnumDefinition myEnum) {
        super(languageLevel);
        this.myEnum = myEnum;
    }

    public PsiClass resolve() {
        return this.myEnum;
    }

    public String getClassName() {
        return this.myEnum.getName();
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
        return this.myEnum.getName();
    }

    public String getCanonicalText() {
        return this.myEnum.getName();
    }

    public String getInternalCanonicalText() {
        return getCanonicalText();
    }

    public boolean isValid() {
        return this.myEnum.isValid();
    }

    public boolean equalsToText(@NonNls String text) {
        return (text.endsWith(getPresentableText()) && text.equals(getCanonicalText()));
    }


    @NotNull
    public GlobalSearchScope getResolveScope() {
        return this.myEnum.getResolveScope();
    }

    @NotNull
    public LanguageLevel getLanguageLevel() {
        return this.myLanguageLevel;
    }

    public PsiClassType setLanguageLevel(LanguageLevel languageLevel) {
        return new FanEnumReferenceType(languageLevel, this.myEnum);
    }
}