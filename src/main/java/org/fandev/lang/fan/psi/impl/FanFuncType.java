/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.psi.PsiAnnotation;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.PsiTypeVisitor;
/*    */ import com.intellij.psi.search.GlobalSearchScope;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
/*    */ import org.jetbrains.annotations.NonNls;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanFuncType
/*    */   extends PsiType
/*    */ {
/*    */   private FanFuncTypeElement element;
/*    */   
/*    */   public FanFuncType(FanFuncTypeElement element) {
/* 23 */     super(PsiAnnotation.EMPTY_ARRAY);
/* 24 */     this.element = element;
/*    */   }
/*    */   
/*    */   public PsiType getReturnType() {
/* 28 */     return this.element.getReturnType().getType();
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getFuncType() {
/* 32 */     return this.element.getFuncType();
/*    */   }
/*    */   
/*    */   public String getPresentableText() {
/* 36 */     return this.element.getText();
/*    */   }
/*    */   
/*    */   public String getCanonicalText() {
/* 40 */     return this.element.getText();
/*    */   }
/*    */   
/*    */   public String getInternalCanonicalText() {
/* 44 */     return this.element.getText();
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public boolean equalsToText(@NonNls String s) {
/* 52 */     return false;
/*    */   }
/*    */   
/*    */   public <A> A accept(PsiTypeVisitor<A> aPsiTypeVisitor) {
/* 56 */     return null;
/*    */   }
/*    */   
/*    */   public GlobalSearchScope getResolveScope() {
/* 60 */     return null;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType[] getSuperTypes() {
/* 65 */     return new PsiType[0];
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanFuncType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */