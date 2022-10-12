/*    */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.Expression;
/*    */ import org.fandev.lang.fan.parsing.statements.Statement;
/*    */ import org.fandev.lang.fan.parsing.types.TypeSpec;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
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
/*    */ 
/*    */ 
/*    */ public class ParenExpression
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/*    */     boolean res;
/* 38 */     PsiBuilder.Marker marker = builder.mark();
/*    */     
/* 40 */     if (ParserUtils.getToken(builder, FanTokenTypes.LPAR)) {
/* 41 */       ParserUtils.removeNls(builder);
/*    */       
/* 43 */       res = parseCastExpression(builder, stopper);
/* 44 */       if (res) {
/* 45 */         marker.done(FanElementTypes.CAST_EXPR);
/*    */       } else {
/* 47 */         marker.rollbackTo();
/* 48 */         marker = builder.mark();
/*    */         
/* 50 */         ParserUtils.advanceNoNls(builder);
/* 51 */         res = Expression.parseExpr(builder, Statement.RPAR_STOPPER, FanElementTypes.EXPRESSION);
/* 52 */         ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 53 */         if (res) {
/* 54 */           res = TermExpression.parseTermChainLoop(builder, stopper);
/*    */         }
/* 56 */         marker.done(FanElementTypes.GROUPED_EXPR);
/*    */       } 
/*    */     } else {
/* 59 */       marker.drop();
/* 60 */       res = UnaryExpression.parse(builder, stopper);
/*    */     } 
/* 62 */     return res;
/*    */   }
/*    */   
/*    */   private static boolean parseCastExpression(PsiBuilder builder, TokenSet stopper) {
/* 66 */     if (TypeSpec.parse(builder) && 
/* 67 */       ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]))) {
/* 68 */       return parse(builder, stopper);
/*    */     }
/*    */     
/* 71 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/ParenExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */