/*    */ package org.fandev.lang.fan.psi.impl.statements.expressions;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import org.fandev.lang.fan.psi.api.statements.expressions.FanClosureExpression;
/*    */ import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanClosureExpressionImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanClosureExpression
/*    */ {
/*    */   public FanClosureExpressionImpl(ASTNode astNode) {
/* 16 */     super(astNode);
/*    */   }
/*    */   
/*    */   public FanFuncTypeElement getFunction() {
/* 20 */     return (FanFuncTypeElement)findChildByClass(FanFuncTypeElement.class);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/expressions/FanClosureExpressionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */