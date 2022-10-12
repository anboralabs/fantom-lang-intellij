/*    */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.ExpressionParser;
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
/*    */ public class UnaryExpression
/*    */   implements ExpressionParser
/*    */ {
/* 31 */   public static TokenSet PREFIXES = TokenSet.create(new IElementType[] { FanTokenTypes.EXCL, FanTokenTypes.PLUS, FanTokenTypes.MINUS, FanTokenTypes.TILDE, FanTokenTypes.AND, FanTokenTypes.PLUSPLUS, FanTokenTypes.MINUSMINUS });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   private static TokenSet POSTFIXES = TokenSet.create(new IElementType[] { FanTokenTypes.PLUSPLUS, FanTokenTypes.MINUSMINUS });
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   private static UnaryExpression instance = new UnaryExpression();
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 49 */     boolean res = parsePrefixExpression(builder, stopper, instance);
/* 50 */     if (!res) {
/* 51 */       PsiBuilder.Marker marker = builder.mark();
/* 52 */       TokenSet newStopper = TokenSet.orSet(new TokenSet[] { stopper, POSTFIXES });
/* 53 */       res = TermExpression.parse(builder, newStopper);
/* 54 */       if (POSTFIXES.contains(builder.getTokenType())) {
/* 55 */         builder.advanceLexer();
/* 56 */         marker.done(FanElementTypes.POSTFIX_EXPR);
/*    */       } else {
/* 58 */         marker.done(FanElementTypes.UNARY_EXPR);
/*    */       } 
/*    */     } 
/* 61 */     return res;
/*    */   }
/*    */   
/*    */   public static boolean parsePrefixExpression(PsiBuilder builder, TokenSet stopper, ExpressionParser nextParser) {
/* 65 */     if (PREFIXES.contains(builder.getTokenType())) {
/* 66 */       PsiBuilder.Marker marker = builder.mark();
/* 67 */       ParserUtils.advanceNoNls(builder);
/* 68 */       nextParser.innerParse(builder, stopper);
/* 69 */       marker.done(FanElementTypes.PREFIX_EXPR);
/* 70 */       return true;
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 76 */     return ParenExpression.parse(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/UnaryExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */