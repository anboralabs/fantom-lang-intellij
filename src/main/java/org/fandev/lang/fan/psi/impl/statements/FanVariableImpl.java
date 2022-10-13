package org.fandev.lang.fan.psi.impl.statements;

import com.intellij.lang.ASTNode;
import com.intellij.psi.Bottom;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.jetbrains.annotations.NotNull;


public class FanVariableImpl
        extends FanVariableBaseImpl<StubElement>
        implements FanVariable {
    public FanVariableImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
        super(stubElement, iStubElementType);
    }

    public FanVariableImpl(ASTNode astNode) {
        super(astNode);
    }


    public PsiIdentifier getNameIdentifier() {
        PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
        assert ident != null;
        return FanPsiImplUtil.getFanIdentifier(ident);
    }


    public PsiType getTypeNoResolve() {
        return Bottom.BOTTOM;
    }


    public String getName() {
        return getNameIdentifier().getText();
    }


    public FanTypeElement getTypeElementFan() {
        FanTypeElement type = super.getTypeElementFan();
        if (type == null) ;

        return type;
    }
}