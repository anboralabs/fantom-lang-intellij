package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;

import javax.swing.Icon;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanEnumReferenceType;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.stubs.FanEnumValueStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FanEnumValueImpl
        extends FanBaseElementImpl<FanEnumValueStub> implements FanEnumValue {
    public FanEnumValueImpl(FanEnumValueStub fanEnumValueStub, @NotNull IStubElementType iStubElementType) {
        super(fanEnumValueStub, iStubElementType);
    }

    public FanEnumValueImpl(ASTNode astNode) {
        super(astNode);
    }


    public void setInitializer(@Nullable PsiExpression initializer) throws IncorrectOperationException {
    }


    @NotNull
    public PsiType getType() {
        return (PsiType) new FanEnumReferenceType((FanEnumDefinition) getContainingClass());
    }

    public PsiType getTypeNoResolve() {
        return getType();
    }


    public PsiTypeElement getTypeElement() {
        return null;
    }


    public int getTextOffset() {
        PsiIdentifier identifier = getNameIdentifier();
        return (identifier == null) ? 0 : identifier.getTextRange().getStartOffset();
    }


    public PsiExpression getInitializer() {
        return null;
    }


    public boolean hasInitializer() {
        return false;
    }


    public void normalizeDeclaration() throws IncorrectOperationException {
    }


    @Nullable
    public Object computeConstantValue() {
        return null;
    }


    public String getName() {
        PsiIdentifier psiId = getNameIdentifier();
        return (psiId == null) ? null : psiId.getText();
    }

    @NotNull
    public PsiIdentifier getNameIdentifier() {
        return findChildByType(FanElementTypes.NAME_ELEMENT);
    }


    public PsiClass getContainingClass() {
        PsiElement parent = getParent().getParent();
        if (parent instanceof FanEnumDefinition) {
            return (PsiClass) parent;
        }
        throw new IllegalStateException("Have an enum value " + getName() + " with no enum: " + this);
    }

    public boolean isDeprecated() {
        return false;
    }

    @Nullable
    public PsiModifierList getModifierList() {
        return null;
    }


    public boolean hasModifierProperty(String name) {
        return false;
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        FanPsiImplUtil.setName(getNameIdentifier(), name);
        return this;
    }


    @Nullable
    public PsiDocComment getDocComment() {
        return null;
    }


    public Icon getIcon(int flags) {
        return Icons.ENUM;
    }


    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            public String getPresentableText() {
                return FanEnumValueImpl.this.getName();
            }

            @Nullable
            public String getLocationString() {
                PsiClass clazz = FanEnumValueImpl.this.getContainingClass();
                String name = clazz.getQualifiedName();
                assert name != null;
                return "(in " + name + ")";
            }

            @Nullable
            public Icon getIcon(boolean open) {
                return FanEnumValueImpl.this.getIcon(3);
            }

            @Nullable
            public TextAttributesKey getTextAttributesKey() {
                return null;
            }
        };
    }
}