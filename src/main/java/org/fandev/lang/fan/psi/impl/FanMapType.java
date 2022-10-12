/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.PsiTypeVisitor;
/*    */ import com.intellij.psi.search.GlobalSearchScope;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
/*    */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*    */ import org.jetbrains.annotations.NonNls;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanMapType
/*    */   extends PsiType
/*    */ {
/*    */   private final FanTypeElement keyType;
/*    */   private final FanTypeElement valueType;
/*    */   private final String text;
/*    */   private FanMapTypeElement element;
/*    */   
/*    */   public FanMapType(FanMapTypeElement element, FanTypeElement keyType, FanTypeElement valueType) {
/* 27 */     super(new com.intellij.psi.PsiAnnotation[0]);
/* 28 */     this.element = element;
/* 29 */     this.keyType = keyType;
/* 30 */     this.valueType = valueType;
/* 31 */     this.text = "[" + keyType.getType().getPresentableText() + ":" + valueType.getType().getPresentableText() + "]";
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getMapType() {
/* 35 */     return this.element.getMapType();
/*    */   }
/*    */   
/*    */   public String getPresentableText() {
/* 39 */     return this.text;
/*    */   }
/*    */   
/*    */   public String getCanonicalText() {
/* 43 */     return this.text;
/*    */   }
/*    */   
/*    */   public String getInternalCanonicalText() {
/* 47 */     return this.text;
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 51 */     return true;
/*    */   }
/*    */   
/*    */   public boolean equalsToText(@NonNls String s) {
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public <A> A accept(PsiTypeVisitor<A> aPsiTypeVisitor) {
/* 59 */     return null;
/*    */   }
/*    */   
/*    */   public GlobalSearchScope getResolveScope() {
/* 63 */     return null;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType[] getSuperTypes() {
/* 68 */     return new PsiType[0];
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanMapType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */