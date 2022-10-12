/*    */ package org.fandev.lang.fan.psi.impl.statements.expressions;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import org.fandev.lang.fan.psi.api.statements.expressions.FanIndexExpression;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanIndexExpressionImpl
/*    */   extends FanExpressionImpl
/*    */   implements FanIndexExpression
/*    */ {
/*    */   public FanIndexExpressionImpl(ASTNode astNode) {
/* 14 */     super(astNode);
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 18 */     return Integer.valueOf(getText()).intValue();
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/expressions/FanIndexExpressionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */