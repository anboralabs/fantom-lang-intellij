/*    */ package org.fandev.lang.fan.parsing.expression;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.logical.LogicalOrExpression;
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
/*    */ 
/*    */ public class Expression
/*    */ {
/*    */   public static boolean parseExpr(PsiBuilder builder, TokenSet stopper, IElementType expressionType) {
/* 36 */     PsiBuilder.Marker m = builder.mark();
/* 37 */     TokenSet newStopper = TokenSet.orSet(new TokenSet[] { stopper, FanTokenTypes.ASSIGN_OP, TokenSet.create(new IElementType[] { FanTokenTypes.QUEST }) });
/* 38 */     boolean res = LogicalOrExpression.parse(builder, newStopper);
/* 39 */     if (!res) {
/* 40 */       m.drop();
/* 41 */       return false;
/*    */     } 
/* 43 */     if (ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }) == FanTokenTypes.QUEST) {
/* 44 */       PsiBuilder.Marker condExprMarker = m.precede();
/* 45 */       PsiBuilder.Marker exprWrapper = condExprMarker.precede();
/* 46 */       m.done(FanElementTypes.CONDITION_EXPR);
/* 47 */       ParserUtils.removeNls(builder);
/* 48 */       ParserUtils.advanceNoNls(builder);
/* 49 */       res = parseExpr(builder, TokenSet.orSet(new TokenSet[] { stopper, TokenSet.create(new IElementType[] { FanTokenTypes.COLON } ) } ), FanElementTypes.COND_TRUE_BLOCK);
/* 50 */       IElementType firstAfter = ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS });
/* 51 */       if (res && firstAfter == FanTokenTypes.COLON) {
/* 52 */         ParserUtils.removeNls(builder);
/* 53 */         ParserUtils.advanceNoNls(builder);
/* 54 */         PsiBuilder.Marker falseBlock = builder.mark();
/* 55 */         res = parseExpr(builder, stopper, FanElementTypes.COND_FALSE_BLOCK);
/* 56 */         falseBlock.done(FanElementTypes.COND_FALSE_BLOCK);
/*    */       } 
/* 58 */       condExprMarker.done(FanElementTypes.COND_EXPR);
/* 59 */       exprWrapper.done(expressionType);
/* 60 */     } else if (FanTokenTypes.ASSIGN_OP.contains(builder.getTokenType())) {
/* 61 */       PsiBuilder.Marker assignExprMarker = m.precede();
/* 62 */       PsiBuilder.Marker exprWrapper = assignExprMarker.precede();
/* 63 */       m.done(FanElementTypes.ASSIGN_LEFT_EXPR);
/* 64 */       ParserUtils.advanceNoNls(builder);
/* 65 */       res = parseExpr(builder, stopper, FanElementTypes.ASSIGN_RIGHT_EXPR);
/* 66 */       assignExprMarker.done(FanElementTypes.ASSIGN_EXPRESSION);
/* 67 */       exprWrapper.done(expressionType);
/*    */     } else {
/* 69 */       m.done(expressionType);
/*    */     } 
/* 71 */     return res;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/Expression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */