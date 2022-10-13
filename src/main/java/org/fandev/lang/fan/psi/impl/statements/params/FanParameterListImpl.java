package org.fandev.lang.fan.psi.impl.statements.params;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiParameter;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NotNull;


public class FanParameterListImpl
        extends FanBaseElementImpl
        implements FanParameterList {
    public FanParameterListImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiParameter[] getParameters() {
        return findChildrenByClass(FanParameter.class);
    }

    public int getParameterIndex(PsiParameter psiParameter) {
        PsiParameter[] parameters = getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].equals(psiParameter)) return i;

        }
        return -1;
    }

    public int getParametersCount() {
        return (getParameters()).length;
    }
}