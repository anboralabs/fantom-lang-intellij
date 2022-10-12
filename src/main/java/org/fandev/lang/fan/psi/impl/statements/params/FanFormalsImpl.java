/*    */ package org.fandev.lang.fan.psi.impl.statements.params;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiParameter;
/*    */ import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
/*    */ import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanFormalsImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanFormals
/*    */ {
/*    */   public FanFormalsImpl(ASTNode astNode) {
/* 18 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiParameter[] getParameters() {
/* 23 */     return (PsiParameter[])findChildrenByClass(FanFormal.class);
/*    */   }
/*    */   
/*    */   public int getParameterIndex(PsiParameter psiParameter) {
/* 27 */     PsiParameter[] parameters = getParameters();
/* 28 */     for (int i = 0; i < parameters.length; i++) {
/* 29 */       if (parameters[i].equals(psiParameter)) return i;
/*    */     
/*    */     } 
/* 32 */     return -1;
/*    */   }
/*    */   
/*    */   public int getParametersCount() {
/* 36 */     return (getParameters()).length;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/params/FanFormalsImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */