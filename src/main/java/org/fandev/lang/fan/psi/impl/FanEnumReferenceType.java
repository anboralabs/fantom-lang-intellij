/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.pom.java.LanguageLevel;
/*    */ import com.intellij.psi.PsiClass;
/*    */ import com.intellij.psi.PsiClassType;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.search.GlobalSearchScope;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
/*    */ import org.jetbrains.annotations.NonNls;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanEnumReferenceType
/*    */   extends PsiClassType
/*    */ {
/*    */   private final FanEnumDefinition myEnum;
/*    */   
/*    */   public FanEnumReferenceType(FanEnumDefinition myEnum) {
/* 22 */     this(LanguageLevel.JDK_1_6, myEnum);
/*    */   }
/*    */   
/*    */   public FanEnumReferenceType(LanguageLevel languageLevel, FanEnumDefinition myEnum) {
/* 26 */     super(languageLevel);
/* 27 */     this.myEnum = myEnum;
/*    */   }
/*    */   
/*    */   public PsiClass resolve() {
/* 31 */     return (PsiClass)this.myEnum;
/*    */   }
/*    */   
/*    */   public String getClassName() {
/* 35 */     return this.myEnum.getName();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiType[] getParameters() {
/* 40 */     return PsiType.EMPTY_ARRAY;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiClassType.ClassResolveResult resolveGenerics() {
/* 45 */     return ClassResolveResult.EMPTY;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiClassType rawType() {
/* 50 */     return this;
/*    */   }
/*    */   
/*    */   public String getPresentableText() {
/* 54 */     return this.myEnum.getName();
/*    */   }
/*    */   
/*    */   public String getCanonicalText() {
/* 58 */     return this.myEnum.getName();
/*    */   }
/*    */   
/*    */   public String getInternalCanonicalText() {
/* 62 */     return getCanonicalText();
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 66 */     return this.myEnum.isValid();
/*    */   }
/*    */   
/*    */   public boolean equalsToText(@NonNls String text) {
/* 70 */     return (text.endsWith(getPresentableText()) && text.equals(getCanonicalText()));
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public GlobalSearchScope getResolveScope() {
/* 76 */     return this.myEnum.getResolveScope();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public LanguageLevel getLanguageLevel() {
/* 81 */     return this.myLanguageLevel;
/*    */   }
/*    */   
/*    */   public PsiClassType setLanguageLevel(LanguageLevel languageLevel) {
/* 85 */     return new FanEnumReferenceType(languageLevel, this.myEnum);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanEnumReferenceType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */