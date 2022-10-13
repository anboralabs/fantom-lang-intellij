package org.fandev.lang.fan.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiSubstitutor;
import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.api.FanResolveResult;


public class FanResolveResultImpl
        implements FanResolveResult {
    private PsiElement myElement;
    private boolean myIsAccessible;
    private boolean myIsStaticsOK;
    private PsiSubstitutor mySubstitutor;
    private FanElement myCurrentFileResolveContext;

    public FanResolveResultImpl(PsiElement element, boolean isAccessible) {
        this(element, null, PsiSubstitutor.EMPTY, isAccessible, true);
    }

    public FanResolveResultImpl(PsiElement element, FanElement context, PsiSubstitutor substitutor, boolean isAccessible, boolean staticsOK) {
        this.myCurrentFileResolveContext = context;
        this.myElement = element;
        this.myIsAccessible = isAccessible;
        this.mySubstitutor = substitutor;
        this.myIsStaticsOK = staticsOK;
    }

    public PsiElement getElement() {
        return this.myElement;
    }

    public boolean isValidResult() {
        return isAccessible();
    }

    public boolean isAccessible() {
        return this.myIsAccessible;
    }

    public PsiSubstitutor getSubstitutor() {
        return this.mySubstitutor;
    }
}