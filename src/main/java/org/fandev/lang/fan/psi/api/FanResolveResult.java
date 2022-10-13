package org.fandev.lang.fan.psi.api;

import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.ResolveResult;


public interface FanResolveResult
        extends ResolveResult {
    public static final FanResolveResult[] EMPTY_ARRAY = new FanResolveResult[0];

    PsiSubstitutor getSubstitutor();
}
