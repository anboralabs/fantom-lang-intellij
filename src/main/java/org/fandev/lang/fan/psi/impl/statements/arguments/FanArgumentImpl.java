package org.fandev.lang.fan.psi.impl.statements.arguments;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgumentList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NotNull;


public class FanArgumentImpl
        extends FanBaseElementImpl
        implements FanArgument {
    public FanArgumentImpl(ASTNode astNode) {
        super(astNode);
    }

    public int getIndex() {
        return getArgumentList().indexOf(this);
    }

    @NotNull
    public FanArgumentList getArgumentList() {
        return PsiTreeUtil.getParentOfType(this, FanArgumentList.class);
    }
}