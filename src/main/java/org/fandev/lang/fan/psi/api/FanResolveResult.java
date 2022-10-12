/*    */ package org.fandev.lang.fan.psi.api;
/*    */ 
/*    */ import com.intellij.psi.PsiSubstitutor;
/*    */ import com.intellij.psi.ResolveResult;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface FanResolveResult
/*    */   extends ResolveResult
/*    */ {
/* 11 */   public static final FanResolveResult[] EMPTY_ARRAY = new FanResolveResult[0];
/*    */   
/*    */   PsiSubstitutor getSubstitutor();
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/FanResolveResult.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */