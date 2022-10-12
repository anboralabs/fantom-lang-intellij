/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiSubstitutor;
/*    */ import org.fandev.lang.fan.psi.FanElement;
/*    */ import org.fandev.lang.fan.psi.api.FanResolveResult;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanResolveResultImpl
/*    */   implements FanResolveResult
/*    */ {
/*    */   private PsiElement myElement;
/*    */   private boolean myIsAccessible;
/*    */   private boolean myIsStaticsOK;
/*    */   private PsiSubstitutor mySubstitutor;
/*    */   private FanElement myCurrentFileResolveContext;
/*    */   
/*    */   public FanResolveResultImpl(PsiElement element, boolean isAccessible) {
/* 21 */     this(element, null, PsiSubstitutor.EMPTY, isAccessible, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FanResolveResultImpl(PsiElement element, FanElement context, PsiSubstitutor substitutor, boolean isAccessible, boolean staticsOK) {
/* 29 */     this.myCurrentFileResolveContext = context;
/* 30 */     this.myElement = element;
/* 31 */     this.myIsAccessible = isAccessible;
/* 32 */     this.mySubstitutor = substitutor;
/* 33 */     this.myIsStaticsOK = staticsOK;
/*    */   }
/*    */   
/*    */   public PsiElement getElement() {
/* 37 */     return this.myElement;
/*    */   }
/*    */   
/*    */   public boolean isValidResult() {
/* 41 */     return isAccessible();
/*    */   }
/*    */   
/*    */   public boolean isAccessible() {
/* 45 */     return this.myIsAccessible;
/*    */   }
/*    */   
/*    */   public PsiSubstitutor getSubstitutor() {
/* 49 */     return this.mySubstitutor;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanResolveResultImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */