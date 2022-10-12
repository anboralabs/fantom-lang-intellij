/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.EnumBlock;
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
/*    */ public class EnumDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 28 */     Modifiers.parse(builder, DeclarationType.ENUM);
/* 29 */     ParserUtils.removeNls(builder);
/* 30 */     if (!ParserUtils.getToken(builder, FanTokenTypes.ENUM_KEYWORD)) {
/* 31 */       builder.error(FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.ENUM_KEYWORD.toString() }));
/* 32 */       return false;
/*    */     } 
/* 34 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 37 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 38 */       builder.error(FanBundle.message("identifier.expected", new Object[0]));
/* 39 */       return false;
/*    */     } 
/* 41 */     boolean isBuiltInType = (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType());
/* 42 */     PsiBuilder.Marker idMark = builder.mark();
/* 43 */     builder.advanceLexer();
/* 44 */     idMark.done(FanElementTypes.NAME_ELEMENT);
/* 45 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 48 */     if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
/* 49 */       InheritanceClause.parse(builder);
/* 50 */       ParserUtils.removeNls(builder);
/*    */     } 
/* 52 */     return EnumBlock.parse(builder, isBuiltInType);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/typeDefs/EnumDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */