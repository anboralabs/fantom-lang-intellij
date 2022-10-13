package org.fandev.lang.fan.psi.impl.statements.params;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.impl.statements.FanVariableBaseImpl;
import org.jetbrains.annotations.NotNull;


public class FanParameterImpl
        extends FanVariableBaseImpl<StubElement>
        implements FanParameter {
    public FanParameterImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiElement getDeclarationScope() {
        FanParameterOwner owner = PsiTreeUtil.getParentOfType(this, FanParameterOwner.class);
        assert owner != null;
        return owner;
    }

    public boolean isVarArgs() {
        return false;
    }


    @NotNull
    public PsiAnnotation[] getAnnotations() {
        return new PsiAnnotation[0];
    }


    public String toString() {
        return "Parameter";
    }


    public PsiIdentifier getNameIdentifier() {
        PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
        assert ident != null;
        return FanPsiImplUtil.getFanIdentifier(ident);
    }


    public String getName() {
        return getNameIdentifier().getText();
    }

    public PsiType getTypeNoResolve() {
        return getType();
    }

    public FanDefaultValue getDefaultValue() {
        return findChildByClass(FanDefaultValue.class);
    }
}