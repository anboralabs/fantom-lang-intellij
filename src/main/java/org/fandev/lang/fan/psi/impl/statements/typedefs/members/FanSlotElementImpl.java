package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocCommentOwner;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiModifierListOwner;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterList;
import com.intellij.psi.PsiTypeParameterListOwner;
import com.intellij.psi.impl.ElementBase;
import com.intellij.psi.impl.ElementPresentationUtil;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.ui.IconManager;
import com.intellij.ui.icons.RowIcon;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import com.intellij.util.VisibilityIcons;

import javax.swing.Icon;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.modifiers.FanFacet;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.impl.modifiers.FanModifierListImpl;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class FanSlotElementImpl<T extends NamedStub>
        extends FanBaseElementImpl<T>
        implements PsiMember, PsiTypeParameterListOwner, PsiNameIdentifierOwner, PsiDocCommentOwner {
    protected FanSlotElementImpl(T t, @NotNull IStubElementType iStubElementType) {
        super(t, iStubElementType);
    }

    protected FanSlotElementImpl(ASTNode astNode) {
        super(astNode);
    }

    public int getTextOffset() {
        PsiIdentifier identifier = getNameIdentifier();
        return (identifier == null) ? 0 : identifier.getTextRange().getStartOffset();
    }


    public String getName() {
        PsiIdentifier psiId = getNameIdentifier();
        return (psiId == null) ? null : psiId.getText();
    }

    @Nullable
    public PsiIdentifier getNameIdentifier() {
        PsiElement element = findChildByType(FanElementTypes.NAME_ELEMENT);
        return FanPsiImplUtil.getFanIdentifier(element);
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        FanPsiImplUtil.setName((PsiElement) getNameIdentifier(), name);
        return (PsiElement) this;
    }


    public PsiClass getContainingClass() {
        PsiElement parent = getParent().getParent();
        if (parent instanceof PsiClass) {
            return (PsiClass) parent;
        }
        throw new IllegalStateException("Have a slot " + getName() + " with no class: " + this);
    }


    public PsiDocComment getDocComment() {
        return null;
    }


    public boolean isDeprecated() {
        return false;
    }

    @Nullable
    public PsiModifierList getModifierList() {
        FanModifierListImpl list = findChildByClass(FanModifierListImpl.class);
        assert list != null;
        return list;
    }

    public boolean hasModifierProperty(String name) {
        PsiModifierList modifiers = getModifierList();
        if (modifiers != null) {
            return modifiers.hasModifierProperty(name);
        }
        return false;
    }


    public boolean hasTypeParameters() {
        return false;
    }


    public PsiTypeParameterList getTypeParameterList() {
        return null;
    }

    @NotNull
    public PsiTypeParameter[] getTypeParameters() {
        return PsiTypeParameter.EMPTY_ARRAY;
    }

    public FanFacet[] getFacets() {
        return new FanFacet[0];
    }

    @Nullable
    public Icon getIcon(int flags) {
        Icon icon = getIconInner();
        boolean isLocked = ((flags & 0x2) != 0 && !isWritable());
        RowIcon rowIcon = IconManager.getInstance().createLayeredIcon(this, icon, ElementPresentationUtil.getFlags(this, isLocked));
        VisibilityIcons.setVisibilityIcon(getModifierList(), rowIcon);
        return rowIcon;
    }


    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            public String getPresentableText() {
                return FanSlotElementImpl.this.getName();
            }

            @Nullable
            public String getLocationString() {
                PsiClass clazz = FanSlotElementImpl.this.getContainingClass();
                String name = clazz.getQualifiedName();
                assert name != null;
                return "(in " + name + ")";
            }

            @Nullable
            public Icon getIcon(boolean open) {
                return FanSlotElementImpl.this.getIcon(3);
            }

            @Nullable
            public TextAttributesKey getTextAttributesKey() {
                return null;
            }
        };
    }

    protected abstract Icon getIconInner();
}