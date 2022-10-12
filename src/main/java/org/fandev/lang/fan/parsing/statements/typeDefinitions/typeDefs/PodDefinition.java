/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.PodBlock;
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
/*    */ public class PodDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 26 */     if (!ParserUtils.getToken(builder, FanTokenTypes.POD_KEYWORD)) {
/* 27 */       builder.error(FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.POD_KEYWORD.toString() }));
/* 28 */       return false;
/*    */     } 
/* 30 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 33 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 34 */       builder.error(FanBundle.message("identifier.expected", new Object[0]));
/* 35 */       return false;
/*    */     } 
/* 37 */     PsiBuilder.Marker idMark = builder.mark();
/* 38 */     builder.advanceLexer();
/* 39 */     idMark.done(FanElementTypes.NAME_ELEMENT);
/* 40 */     ParserUtils.removeNls(builder);
/*    */     
/* 42 */     return PodBlock.parse(builder);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/typeDefs/PodDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */