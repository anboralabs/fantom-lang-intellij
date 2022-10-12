/*    */ package org.fandev.lang.fan.psi.impl.statements.expressions;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import org.fandev.lang.fan.psi.api.statements.expressions.PodReferenceExpression;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PodReferenceExpressionImpl
/*    */   extends FanExpressionImpl
/*    */   implements PodReferenceExpression
/*    */ {
/*    */   public PodReferenceExpressionImpl(ASTNode astNode) {
/* 14 */     super(astNode);
/*    */   }
/*    */   
/*    */   public String getPodName() {
/* 18 */     return getText();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/expressions/PodReferenceExpressionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */