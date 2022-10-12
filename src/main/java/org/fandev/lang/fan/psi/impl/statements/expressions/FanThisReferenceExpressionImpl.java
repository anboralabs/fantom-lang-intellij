/*    */ package org.fandev.lang.fan.psi.impl.statements.expressions;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.expressions.FanThisReferenceExpression;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.utils.FanUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanThisReferenceExpressionImpl
/*    */   extends FanExpressionImpl
/*    */   implements FanThisReferenceExpression
/*    */ {
/*    */   public FanThisReferenceExpressionImpl(ASTNode astNode) {
/* 23 */     super(astNode);
/*    */   }
/*    */   
/*    */   public FanTypeDefinition getReferencedType() {
/* 27 */     return FanUtil.getContainingType((PsiElement)this);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/expressions/FanThisReferenceExpressionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */