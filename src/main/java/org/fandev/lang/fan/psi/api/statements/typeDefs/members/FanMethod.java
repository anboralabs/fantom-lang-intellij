/*    */ package org.fandev.lang.fan.psi.api.statements.typeDefs.members;
/*    */ 
/*    */ import com.intellij.psi.PsiCodeBlock;
/*    */ import com.intellij.psi.PsiMethod;
/*    */ import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface FanMethod
/*    */   extends FanSlot, FanParameterOwner, PsiMethod
/*    */ {
/* 14 */   public static final FanMethod[] EMPTY_ARRAY = new FanMethod[0];
/*    */   
/*    */   void setBlock(PsiCodeBlock paramPsiCodeBlock);
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/typeDefs/members/FanMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */