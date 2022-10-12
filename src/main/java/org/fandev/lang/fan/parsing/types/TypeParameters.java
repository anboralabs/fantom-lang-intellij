/*    */ package org.fandev.lang.fan.parsing.types;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.Expression;
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
/*    */ public class TypeParameters
/*    */ {
/*    */   public static IElementType parse(PsiBuilder builder) {
/* 22 */     if (FanTokenTypes.LPAR == builder.getTokenType()) {
/* 23 */       PsiBuilder.Marker marker = builder.mark();
/* 24 */       ParserUtils.getToken(builder, FanTokenTypes.LPAR);
/* 25 */       ParserUtils.removeNls(builder);
/* 26 */       if (!ParserUtils.getToken(builder, FanTokenTypes.RPAR)) {
/* 27 */         while (parseTypeParameter(builder) != FanElementTypes.WRONGWAY && 
/* 28 */           ParserUtils.getToken(builder, FanTokenTypes.COMMA))
/*    */         {
/*    */           
/* 31 */           eatCommas(builder);
/*    */         }
/* 33 */         eatCommas(builder);
/* 34 */         if (!ParserUtils.getToken(builder, FanTokenTypes.RPAR)) {
/* 35 */           builder.error(FanBundle.message("rpar.expected", new Object[0]));
/* 36 */           ParserUtils.cleanAfterErrorInArguments(builder);
/*    */         } 
/*    */       } 
/* 39 */       ParserUtils.removeNls(builder);
/* 40 */       marker.done(FanElementTypes.TYPE_PARAMETER_LIST);
/* 41 */       return FanElementTypes.TYPE_PARAMETER_LIST;
/*    */     } 
/*    */     
/* 44 */     return FanElementTypes.WRONGWAY;
/*    */   }
/*    */   
/*    */   private static void eatCommas(PsiBuilder builder) {
/* 48 */     ParserUtils.removeNls(builder);
/* 49 */     while (FanTokenTypes.COMMA == builder.getTokenType()) {
/* 50 */       builder.error(FanBundle.message("type.parameter.expected", new Object[0]));
/* 51 */       ParserUtils.getToken(builder, FanTokenTypes.COMMA);
/* 52 */       ParserUtils.removeNls(builder);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static IElementType parseTypeParameter(PsiBuilder builder) {
/* 59 */     PsiBuilder.Marker marker = builder.mark();
/* 60 */     if (TypeSpec.parse(builder))
/*    */     {
/* 62 */       if (ParserUtils.parseName(builder)) {
/* 63 */         ParserUtils.removeNls(builder);
/* 64 */         if (FanTokenTypes.COLON_EQ.equals(builder.getTokenType())) {
/*    */           
/* 66 */           ParserUtils.advanceNoNls(builder);
/* 67 */           Expression.parseExpr(builder, Arguments.ARGUMENTS_STOPPER, FanElementTypes.PARAM_DEFAULT);
/*    */         } 
/* 69 */         marker.done(FanElementTypes.TYPE_PARAMETER);
/* 70 */         return FanElementTypes.TYPE_PARAMETER;
/*    */       } 
/*    */     }
/* 73 */     marker.error(FanBundle.message("type.parameter.expected", new Object[0]));
/* 74 */     return FanElementTypes.WRONGWAY;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/types/TypeParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */