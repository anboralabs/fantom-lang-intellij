/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.psi.PsiArrayType;
/*    */ import com.intellij.psi.PsiType;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanListReferenceType
/*    */   extends PsiArrayType
/*    */ {
/*    */   private FanListTypeElement element;
/*    */   
/*    */   public FanListReferenceType(FanListTypeElement element, @NotNull PsiType psiType) {
/* 22 */     super(psiType);
/* 23 */     this.element = element;
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getListType() {
/* 27 */     return this.element.getListType();
/*    */   }
/*    */   
/*    */   public PsiType getType() {
/* 31 */     return this.element.getType();
/*    */   }
/*    */   
/*    */   public FanClassTypeElement getTypeElement() {
/* 35 */     return this.element.getTypeElement();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanListReferenceType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */