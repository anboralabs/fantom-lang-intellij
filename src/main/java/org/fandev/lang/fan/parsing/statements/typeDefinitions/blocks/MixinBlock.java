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
/*    */ public class MixinBlock
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
/* 21 */     PsiBuilder.Marker cbMarker = builder.mark();
/*    */     
/* 23 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
/* 24 */       builder.error(FanBundle.message("lcurly.expected", new Object[0]));
/* 25 */       cbMarker.rollbackTo();
/* 26 */       return false;
/*    */     } 
/*    */     
/* 29 */     ParserUtils.removeNls(builder);
/* 30 */     while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE && 
/* 31 */       SlotDefinition.parse(builder, DeclarationType.MIXIN, isBuiltInType))
/*    */     {
/*    */       
/* 34 */       ParserUtils.removeNls(builder);
/*    */     }
/*    */     
/* 37 */     if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
/* 38 */       cbMarker.done(FanElementTypes.MIXIN_BODY);
/* 39 */       return true;
/*    */     } 
/* 41 */     ParserUtils.cleanAfterErrorInBlock(builder);
/* 42 */     cbMarker.done(FanElementTypes.MIXIN_BODY);
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/blocks/MixinBlock.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */