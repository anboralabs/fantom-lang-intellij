/*    */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.Block;
/*    */ import org.fandev.lang.fan.parsing.types.FuncTypeSpec;
/*    */ import org.fandev.lang.fan.parsing.types.TypeType;
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
/*    */ public class ClosureExpression
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 35 */     PsiBuilder.Marker marker = builder.mark();
/*    */     
/* 37 */     if (FuncTypeSpec.parseFuncType(builder, false) == TypeType.FUNCTION) {
/* 38 */       ParserUtils.removeNls(builder);
/* 39 */       if (FanTokenTypes.LBRACE == builder.getTokenType()) {
/* 40 */         Block.parse(builder, FanElementTypes.CLOSURE_BODY);
/*    */       } else {
/* 42 */         builder.error(FanBundle.message("lcurly.expected", new Object[0]));
/*    */       } 
/* 44 */       marker.done(FanElementTypes.CLOSURE_EXPR);
/* 45 */       return true;
/*    */     } 
/* 47 */     marker.rollbackTo();
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/ClosureExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */