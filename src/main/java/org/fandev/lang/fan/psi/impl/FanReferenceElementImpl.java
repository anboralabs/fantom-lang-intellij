/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.openapi.util.TextRange;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiReference;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import com.intellij.util.IncorrectOperationException;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.psi.FanReferenceElement;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FanReferenceElementImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanReferenceElement
/*    */ {
/*    */   protected FanReferenceElementImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
/* 22 */     super(stubElement, iStubElementType);
/*    */   }
/*    */   
/*    */   protected FanReferenceElementImpl(ASTNode astNode) {
/* 26 */     super(astNode);
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiReference getReference() {
/* 31 */     return (PsiReference)this;
/*    */   }
/*    */   
/*    */   public String getReferenceName() {
/* 35 */     PsiElement nameElement = getReferenceNameElement();
/* 36 */     if (nameElement != null) {
/* 37 */       return nameElement.getText();
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   public PsiElement getReferenceNameElement() {
/* 43 */     PsiElement element = findChildByType(FanTokenTypes.IDENTIFIER_TOKENS_SET);
/* 44 */     if (element != null) {
/* 45 */       return element;
/*    */     }
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   public PsiElement getElement() {
/* 51 */     return (PsiElement)this;
/*    */   }
/*    */   
/*    */   public TextRange getRangeInElement() {
/* 55 */     PsiElement refNameElement = getReferenceNameElement();
/* 56 */     if (refNameElement != null) {
/* 57 */       int offsetInParent = refNameElement.getStartOffsetInParent();
/* 58 */       return new TextRange(offsetInParent, offsetInParent + refNameElement.getTextLength());
/*    */     } 
/* 60 */     return new TextRange(0, getTextLength());
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiElement handleElementRename(String s) throws IncorrectOperationException {
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
/* 69 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PsiType[] getTypeArguments() {
/* 86 */     return PsiType.EMPTY_ARRAY;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanReferenceElementImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */