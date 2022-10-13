package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.psi.Bottom;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;

import javax.swing.Icon;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.stubs.FanFieldStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FanFieldImpl
        extends FanSlotElementImpl<FanFieldStub>
        implements FanField, StubBasedPsiElement<FanFieldStub> {
    public FanFieldImpl(FanFieldStub fanFieldStub, @NotNull IStubElementType iStubElementType) {
        super(fanFieldStub, iStubElementType);
    }

    public FanFieldImpl(ASTNode astNode) {
        super(astNode);
    }


    public Icon getIconInner() {
        return Icons.FIELD;
    }


    public void setInitializer(@Nullable PsiExpression initializer) throws IncorrectOperationException {
    }


    @NotNull
    public PsiType getType() {
        FanTypeElement classTypeElement = findTypeElement();
        if (classTypeElement != null) {
            return classTypeElement.getType();
        }
        return (PsiType) Bottom.BOTTOM;
    }

    public PsiType getTypeNoResolve() {
        return getType();
    }

    protected FanTypeElement findTypeElement() {
        FanTypeElement classTypeElement = findChildByType(FanElementTypes.CLASS_TYPE_ELEMENT);
        if (classTypeElement == null) {
            classTypeElement = findChildByType(FanElementTypes.LIST_TYPE);
            if (classTypeElement == null) {
                classTypeElement = findChildByType(FanElementTypes.MAP_TYPE);
                if (classTypeElement == null) {
                    classTypeElement = findChildByType(FanElementTypes.FUNC_TYPE);
                }
            }
        }
        return classTypeElement;
    }


    public PsiTypeElement getTypeElement() {
        PsiElement typeEl = findChildByType(FanElementTypes.TYPE);
        return null;
    }

    public PsiExpression getInitializer() {
        PsiElement initEl = findChildByType(FanElementTypes.FIELD_DEFAULT);
        return null;
    }

    public boolean hasInitializer() {
        return false;
    }


    public void normalizeDeclaration() throws IncorrectOperationException {
    }

    public Object computeConstantValue() {
        return null;
    }
}