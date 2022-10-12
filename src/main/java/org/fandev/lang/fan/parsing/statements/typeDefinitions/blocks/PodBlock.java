/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.FieldDefinition;
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
/*    */ public class PodBlock
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 22 */     ParserUtils.removeNls(builder);
/* 23 */     PsiBuilder.Marker blockMark = builder.mark();
/* 24 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
/* 25 */       blockMark.error(FanBundle.message("lcurly.expected", new Object[0]));
/* 26 */       return false;
/*    */     } 
/* 28 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 31 */     while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE && 
/* 32 */       FieldDefinition.parse(builder))
/*    */     {
/*    */       
/* 35 */       ParserUtils.removeNls(builder);
/*    */     }
/* 37 */     if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
/* 38 */       blockMark.done(FanElementTypes.POD_BODY);
/* 39 */       return true;
/*    */     } 
/* 41 */     ParserUtils.cleanAfterErrorInBlock(builder);
/* 42 */     blockMark.done(FanElementTypes.POD_BODY);
/* 43 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private static void eatCommas(PsiBuilder builder) {
/* 48 */     ParserUtils.removeNls(builder);
/* 49 */     while (FanTokenTypes.COMMA == builder.getTokenType()) {
/* 50 */       builder.error(FanBundle.message("enum.value.expected", new Object[0]));
/* 51 */       ParserUtils.getToken(builder, FanTokenTypes.COMMA);
/* 52 */       ParserUtils.removeNls(builder);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/blocks/PodBlock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */