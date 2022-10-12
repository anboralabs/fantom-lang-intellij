/*    */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
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
/*    */ public class IdExpression
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 31 */     PsiBuilder.Marker marker = builder.mark();
/*    */     
/* 33 */     boolean symbol = ParserUtils.getToken(builder, FanTokenTypes.AT);
/*    */     
/* 35 */     boolean field = ParserUtils.getToken(builder, FanTokenTypes.MULT);
/* 36 */     if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 37 */       PsiBuilder.Marker pod = builder.mark();
/* 38 */       builder.advanceLexer();
/* 39 */       if (builder.getTokenType() == FanTokenTypes.COLON_COLON) {
/*    */         
/* 41 */         pod.done(FanElementTypes.POD_REFERENCE);
/* 42 */         builder.advanceLexer();
/*    */         
/* 44 */         parse(builder);
/*    */       } else {
/* 46 */         pod.drop();
/*    */       } 
/* 48 */       if (!field && !symbol) {
/* 49 */         boolean res = true;
/* 50 */         while (!builder.eof() && res && ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }) == FanTokenTypes.LPAR) {
/* 51 */           ParserUtils.removeNls(builder);
/* 52 */           res = Arguments.parse(builder);
/*    */         } 
/* 54 */         if (ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }) == FanTokenTypes.OR) {
/* 55 */           ParserUtils.removeNls(builder);
/* 56 */           ClosureExpression.parse(builder);
/*    */         } 
/*    */       } 
/* 59 */       marker.done(FanElementTypes.ID_EXPR);
/* 60 */       return true;
/*    */     } 
/* 62 */     marker.rollbackTo();
/* 63 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/IdExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */