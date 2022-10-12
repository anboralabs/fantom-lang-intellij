/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnumValueDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 17 */     PsiBuilder.Marker marker = builder.mark();
/*    */ 
/*    */     
/* 20 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 21 */       marker.drop();
/* 22 */       return false;
/*    */     } 
/* 24 */     PsiBuilder.Marker idMark = builder.mark();
/* 25 */     builder.advanceLexer();
/* 26 */     idMark.done(FanElementTypes.NAME_ELEMENT);
/*    */ 
/*    */     
/* 29 */     if (FanTokenTypes.LPAR.equals(builder.getTokenType())) {
/* 30 */       if (Arguments.parse(builder)) {
/* 31 */         marker.done(FanElementTypes.ENUM_VALUE);
/* 32 */         return true;
/*    */       } 
/* 34 */       marker.error(FanBundle.message("argument.expected", new Object[0]));
/* 35 */       return false;
/*    */     } 
/*    */     
/* 38 */     marker.done(FanElementTypes.ENUM_VALUE);
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/members/EnumValueDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */