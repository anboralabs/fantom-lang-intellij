/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.pom.java.LanguageLevel;
/*    */ import com.intellij.psi.PsiClass;
/*    */ import com.intellij.psi.PsiClassType;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.ResolveResult;
/*    */ import com.intellij.psi.search.GlobalSearchScope;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
/*    */ import org.jetbrains.annotations.NonNls;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class FanClassReferenceType
/*    */   extends PsiClassType {
/*    */   private final FanCodeReferenceElement myReferenceElement;
/*    */   
/*    */   public FanClassReferenceType(FanCodeReferenceElement ref) {
/* 20 */     this(LanguageLevel.JDK_1_6, ref);
/*    */   }
/*    */   
/*    */   public FanClassReferenceType(LanguageLevel languageLevel, FanCodeReferenceElement ref) {
/* 24 */     super(languageLevel);
/* 25 */     this.myReferenceElement = ref;
/*    */   }
/*    */   
/*    */   public PsiClass resolve() {
/* 29 */     ResolveResult[] results = multiResolve();
/* 30 */     if (results.length == 1) {
/* 31 */       PsiElement only = results[0].getElement();
/* 32 */       return (only instanceof PsiClass) ? (PsiClass)only : null;
/*    */     } 
/*    */     
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public FanTypeDefinition resolveFanType() {
/* 39 */     return (FanTypeDefinition)resolve();
/*    */   }
/*    */ 
/*    */   
/*    */   private ResolveResult[] multiResolve() {
/* 44 */     return this.myReferenceElement.multiResolve(false);
/*    */   }
/*    */   
/*    */   public String getClassName() {
/* 48 */     return this.myReferenceElement.getReferenceName();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PsiType[] getParameters() {
/* 54 */     return PsiType.EMPTY_ARRAY;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiClassType.ClassResolveResult resolveGenerics() {
/* 59 */     return ClassResolveResult.EMPTY;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiClassType rawType() {
/* 64 */     return this;
/*    */   }
/*    */   
/*    */   public String getPresentableText() {
/* 68 */     return this.myReferenceElement.getReferenceName();
/*    */   }
/*    */   
/*    */   public String getCanonicalText() {
/* 72 */     return this.myReferenceElement.getReferenceName();
/*    */   }
/*    */   
/*    */   public String getInternalCanonicalText() {
/* 76 */     return getCanonicalText();
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 80 */     return this.myReferenceElement.isValid();
/*    */   }
/*    */   
/*    */   public boolean equalsToText(@NonNls String text) {
/* 84 */     return (text.endsWith(getPresentableText()) && text.equals(getCanonicalText()));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public GlobalSearchScope getResolveScope() {
/* 90 */     return this.myReferenceElement.getResolveScope();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public LanguageLevel getLanguageLevel() {
/* 95 */     return this.myLanguageLevel;
/*    */   }
/*    */   
/*    */   public PsiClassType setLanguageLevel(LanguageLevel languageLevel) {
/* 99 */     return new FanClassReferenceType(languageLevel, this.myReferenceElement);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanClassReferenceType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */