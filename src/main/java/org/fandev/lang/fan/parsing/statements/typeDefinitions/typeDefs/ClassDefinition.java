/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
/*    */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.ClassBlock;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder) {
/* 19 */     Modifiers.parse(builder, DeclarationType.CLASS);
/*    */     
/* 21 */     if (!ParserUtils.getToken(builder, FanTokenTypes.CLASS_KEYWORD)) {
/* 22 */       builder.error(FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.CLASS_KEYWORD.toString() }));
/* 23 */       return false;
/*    */     } 
/* 25 */     ParserUtils.removeNls(builder);
/*    */ 
/*    */     
/* 28 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 29 */       builder.error(FanBundle.message("identifier.expected", new Object[0]));
/* 30 */       return false;
/*    */     } 
/* 32 */     boolean isBuiltInType = (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType());
/*    */     
/* 34 */     PsiBuilder.Marker idMark = builder.mark();
/* 35 */     builder.advanceLexer();
/* 36 */     idMark.done(FanElementTypes.NAME_ELEMENT);
/* 37 */     ParserUtils.removeNls(builder);
/*    */     
/* 39 */     if (FanTokenTypes.COLON.equals(builder.getTokenType())) {
/* 40 */       InheritanceClause.parse(builder);
/* 41 */       ParserUtils.removeNls(builder);
/*    */     } 
/* 43 */     return ClassBlock.parse(builder, isBuiltInType);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/typeDefs/ClassDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */