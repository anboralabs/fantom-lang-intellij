package org.fandev.lang.fan.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiType;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.IncorrectOperationException;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.FanReferenceElement;
import org.jetbrains.annotations.NotNull;


public abstract class FanReferenceElementImpl
        extends FanBaseElementImpl
        implements FanReferenceElement {
    protected FanReferenceElementImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
        super(stubElement, iStubElementType);
    }

    protected FanReferenceElementImpl(ASTNode astNode) {
        super(astNode);
    }

    public PsiReference getReference() {
        return this;
    }

    public String getReferenceName() {
        PsiElement nameElement = getReferenceNameElement();
        if (nameElement != null) {
            return nameElement.getText();
        }
        return null;
    }

    public PsiElement getReferenceNameElement() {
        PsiElement element = findChildByType(FanTokenTypes.IDENTIFIER_TOKENS_SET);
        if (element != null) {
            return element;
        }
        return null;
    }

    public PsiElement getElement() {
        return this;
    }

    public TextRange getRangeInElement() {
        PsiElement refNameElement = getReferenceNameElement();
        if (refNameElement != null) {
            int offsetInParent = refNameElement.getStartOffsetInParent();
            return new TextRange(offsetInParent, offsetInParent + refNameElement.getTextLength());
        }
        return new TextRange(0, getTextLength());
    }

    public PsiElement handleElementRename(String s) throws IncorrectOperationException {
        return null;
    }

    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        return null;
    }

    @NotNull
    public PsiType[] getTypeArguments() {
        return PsiType.EMPTY_ARRAY;
    }
}