package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiMethod;
import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;


public interface FanMethod
        extends FanSlot, FanParameterOwner, PsiMethod {
    public static final FanMethod[] EMPTY_ARRAY = new FanMethod[0];

    void setBlock(PsiCodeBlock paramPsiCodeBlock);
}