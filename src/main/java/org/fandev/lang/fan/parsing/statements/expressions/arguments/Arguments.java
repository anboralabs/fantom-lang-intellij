/*    */ package org.fandev.lang.fan.parsing.statements.expressions.arguments;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.Expression;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Arguments
/*    */ {
/* 20 */   public static final TokenSet ARGUMENTS_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.RPAR, FanTokenTypes.COMMA });
/*    */ 
/*    */   
/*    */   public static boolean parse(PsiBuilder builder) {
/* 24 */     PsiBuilder.Marker marker = builder.mark();
/* 25 */     if (FanTokenTypes.LPAR == builder.getTokenType()) {
/* 26 */       ParserUtils.advanceNoNls(builder);
/* 27 */       while (!builder.eof() && FanTokenTypes.RPAR != builder.getTokenType()) {
/* 28 */         boolean res = Expression.parseExpr(builder, ARGUMENTS_STOPPER, FanElementTypes.ARGUMENT_EXPR);
/* 29 */         ParserUtils.removeNls(builder);
/* 30 */         if (!res || FanTokenTypes.RPAR == builder.getTokenType()) {
/*    */           break;
/*    */         }
/* 33 */         ParserUtils.getToken(builder, FanTokenTypes.COMMA, FanBundle.message("comma.expected", new Object[0]));
/* 34 */         ParserUtils.removeNls(builder);
/*    */       } 
/* 36 */       ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 37 */       marker.done(FanElementTypes.ARGUMENT_LIST);
/* 38 */       return true;
/*    */     } 
/* 40 */     marker.drop();
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/expressions/arguments/Arguments.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */