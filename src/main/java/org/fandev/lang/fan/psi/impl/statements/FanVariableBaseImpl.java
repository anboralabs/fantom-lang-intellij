package org.fandev.lang.fan.psi.impl.statements;

import com.intellij.lang.ASTNode;
import com.intellij.psi.Bottom;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.IncorrectOperationException;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class FanVariableBaseImpl<T extends StubElement>
        extends FanBaseElementImpl<T>
        implements FanVariable {
    public FanVariableBaseImpl(T t, @NotNull IStubElementType iStubElementType) {
        super(t, iStubElementType);
    }

    public FanVariableBaseImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiType getType() {
        PsiType type = getDeclaredType();
        return (type != null) ? type : Bottom.BOTTOM;
    }

    @Nullable
    public FanTypeElement getTypeElementFan() {
        return findChildByClass(FanTypeElement.class);
    }

    @Nullable
    public PsiType getDeclaredType() {
        FanTypeElement typeElement = getTypeElementFan();
        if (typeElement != null) return typeElement.getType();

        return null;
    }

    public PsiIdentifier getNameIdentifier() {
        PsiElement ident = findChildByType(FanElementTypes.ID_EXPR);
        assert ident != null;

        return FanPsiImplUtil.getFanIdentifier(ident);
    }


    public String getName() {
        return getNameIdentifier().getText();
    }


    @Nullable
    public PsiModifierList getModifierList() {
        return null;
    }

    public boolean hasModifierProperty(String property) {
        PsiModifierList modifierList = getModifierList();
        return (modifierList != null && modifierList.hasModifierProperty(property));
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        FanPsiImplUtil.setName(getNameIdentifier(), name);
        return this;
    }

    @Nullable
    public PsiTypeElement getTypeElement() {
        return null;
    }

    @Nullable
    public PsiExpression getInitializer() {
        return null;
    }

    public boolean hasInitializer() {
        return false;
    }

    @Nullable
    public Object computeConstantValue() {
        return null;
    }

    public void normalizeDeclaration() throws IncorrectOperationException {
    }
}
