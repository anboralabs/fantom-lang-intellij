package org.fandev.lang.fan.psi.impl.statements.params;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.impl.statements.FanVariableBaseImpl;
import org.jetbrains.annotations.NotNull;


public class FanFormalImpl
        extends FanVariableBaseImpl<StubElement>
        implements FanFormal {
    public FanFormalImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiElement getDeclarationScope() {
        FanFuncTypeElement owner = (FanFuncTypeElement) PsiTreeUtil.getParentOfType((PsiElement) this, FanFuncTypeElement.class);
        assert owner != null;
        return (PsiElement) owner;
    }

    public boolean isVarArgs() {
        return false;
    }

    @NotNull
    public PsiAnnotation[] getAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }

    public PsiType getTypeNoResolve() {
        return getType();
    }


    public PsiIdentifier getNameIdentifier() {
        PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
        return FanPsiImplUtil.getFanIdentifier(ident);
    }


    public String getName() {
        PsiIdentifier identifier = getNameIdentifier();
        if (identifier != null) {
            return identifier.getText();
        }
        return null;
    }
}