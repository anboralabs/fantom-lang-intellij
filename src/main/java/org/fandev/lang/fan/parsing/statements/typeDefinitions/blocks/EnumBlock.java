/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.EnumValueDefinition;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.SlotDefinition;
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
/*    */ public class EnumBlock
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, boolean isBuiltinType) {
/* 25 */     ParserUtils.removeNls(builder);
/* 26 */     PsiBuilder.Marker blockMark = builder.mark();
/* 27 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
/* 28 */       blockMark.error(FanBundle.message("lcurly.expected", new Object[0]));
/* 29 */       return false;
/*    */     } 
/* 31 */     ParserUtils.removeNls(builder);
/*    */     
/* 33 */     while (EnumValueDefinition.parse(builder) && 
/* 34 */       ParserUtils.getToken(builder, FanTokenTypes.COMMA))
/*    */     {
/*    */       
/* 37 */       eatCommas(builder);
/*    */     }
/* 39 */     eatCommas(builder);
/* 40 */     ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON);
/* 41 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 44 */     while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE && 
/* 45 */       SlotDefinition.parse(builder, DeclarationType.ENUM, isBuiltinType))
/*    */     {
/*    */       
/* 48 */       ParserUtils.removeNls(builder);
/*    */     }
/* 50 */     if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
/* 51 */       blockMark.done(FanElementTypes.ENUM_BODY);
/* 52 */       return true;
/*    */     } 
/* 54 */     ParserUtils.cleanAfterErrorInBlock(builder);
/* 55 */     blockMark.done(FanElementTypes.ENUM_BODY);
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private static void eatCommas(PsiBuilder builder) {
/* 61 */     ParserUtils.removeNls(builder);
/* 62 */     while (FanTokenTypes.COMMA == builder.getTokenType()) {
/* 63 */       builder.error(FanBundle.message("enum.value.expected", new Object[0]));
/* 64 */       ParserUtils.getToken(builder, FanTokenTypes.COMMA);
/* 65 */       ParserUtils.removeNls(builder);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/blocks/EnumBlock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */