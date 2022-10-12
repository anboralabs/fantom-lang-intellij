/*    */ package org.fandev.lang.fan.psi.impl.statements.expressions;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import org.fandev.lang.fan.psi.api.statements.expressions.FanSuperReferenceExpression;
/*    */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
/*    */ import org.fandev.utils.FanUtil;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanSuperReferenceExpressionImpl
/*    */   extends FanExpressionImpl
/*    */   implements FanSuperReferenceExpression
/*    */ {
/*    */   public FanSuperReferenceExpressionImpl(ASTNode astNode) {
/* 23 */     super(astNode);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public FanTypeDefinition getReferencedType() {
/* 28 */     FanTypeDefinition thisTypeDefinition = FanUtil.getContainingType((PsiElement)this);
/* 29 */     if (thisTypeDefinition != null) {
/* 30 */       return thisTypeDefinition.getSuperType();
/*    */     }
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/expressions/FanSuperReferenceExpressionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */