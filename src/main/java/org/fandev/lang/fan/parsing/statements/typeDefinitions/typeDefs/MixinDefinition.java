/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.MixinBlock;
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
/*    */ public class MixinDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 26 */     TokenSet modifers = Modifiers.parse(builder, DeclarationType.MIXIN);
/* 27 */     ParserUtils.removeNls(builder);
/* 28 */     if (!ParserUtils.getToken(builder, FanTokenTypes.MIXIN_KEYWORD)) {
/* 29 */       builder.error(FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.MIXIN_KEYWORD.toString() }));
/* 30 */       return false;
/*    */     } 
/*    */     
/* 33 */     boolean isBuiltInType = (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType());
/* 34 */     if (!ParserUtils.parseName(builder)) {
/* 35 */       return false;
/*    */     }
/* 37 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 40 */     if (modifers.contains(FanTokenTypes.CONST_KEYWORD) && !isBuiltInType) {
/*    */       
/* 42 */       String tokenText = builder.getTokenText();
/* 43 */       builder.error(FanBundle.message("illegal.modifier", new Object[] { tokenText, DeclarationType.MIXIN }));
/*    */     } 
/*    */     
/* 46 */     if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
/* 47 */       InheritanceClause.parse(builder);
/* 48 */       ParserUtils.removeNls(builder);
/*    */     } 
/*    */     
/* 51 */     return MixinBlock.parse(builder, isBuiltInType);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/typeDefs/MixinDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */