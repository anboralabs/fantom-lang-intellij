/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.extapi.psi.ASTDelegatePsiElement;
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiIdentifier;
/*    */ import com.intellij.psi.impl.source.tree.SharedImplUtil;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanIdentifierImpl
/*    */   extends ASTDelegatePsiElement
/*    */   implements PsiIdentifier
/*    */ {
/*    */   private final ASTNode myNode;
/*    */   private String myName;
/*    */   
/*    */   public FanIdentifierImpl(ASTNode myNode) {
/* 24 */     this.myNode = myNode;
/* 25 */     this.myName = myNode.getText();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return this.myName;
/*    */   }
/*    */   
/*    */   public PsiElement getParent() {
/* 34 */     return SharedImplUtil.getParent(getNode());
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ASTNode getNode() {
/* 39 */     return this.myNode;
/*    */   }
/*    */   
/*    */   public IElementType getTokenType() {
/* 43 */     return FanElementTypes.NAME_ELEMENT;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return "FanIdentifier:" + getText();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanIdentifierImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */