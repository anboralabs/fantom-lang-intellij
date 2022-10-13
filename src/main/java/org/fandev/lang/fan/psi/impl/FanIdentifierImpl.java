package org.fandev.lang.fan.psi.impl;

import com.intellij.extapi.psi.ASTDelegatePsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.impl.source.tree.SharedImplUtil;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanElementTypes;
import org.jetbrains.annotations.NotNull;


public class FanIdentifierImpl
        extends ASTDelegatePsiElement
        implements PsiIdentifier {
    private final ASTNode myNode;
    private String myName;

    public FanIdentifierImpl(ASTNode myNode) {
        this.myNode = myNode;
        this.myName = myNode.getText();
    }

    public String getName() {
        return this.myName;
    }

    public PsiElement getParent() {
        return SharedImplUtil.getParent(getNode());
    }

    @NotNull
    public ASTNode getNode() {
        return this.myNode;
    }

    public IElementType getTokenType() {
        return FanElementTypes.NAME_ELEMENT;
    }

    public String toString() {
        return "FanIdentifier:" + getText();
    }
}