/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
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
/*    */ public class ClassBlock
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
/* 21 */     ParserUtils.removeNls(builder);
/* 22 */     PsiBuilder.Marker blockMark = builder.mark();
/* 23 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
/* 24 */       blockMark.error(FanBundle.message("lcurly.expected", new Object[0]));
/* 25 */       return false;
/*    */     } 
/* 27 */     ParserUtils.removeNls(builder);
/* 28 */     while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE && 
/* 29 */       SlotDefinition.parse(builder, DeclarationType.CLASS, isBuiltInType))
/*    */     {
/*    */       
/* 32 */       ParserUtils.removeNls(builder);
/*    */     }
/* 34 */     if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
/* 35 */       blockMark.done(FanElementTypes.CLASS_BODY);
/* 36 */       return true;
/*    */     } 
/* 38 */     ParserUtils.cleanAfterErrorInBlock(builder);
/* 39 */     blockMark.done(FanElementTypes.CLASS_BODY);
/* 40 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/blocks/ClassBlock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */