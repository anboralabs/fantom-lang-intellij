/*    */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
/*    */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*    */ import org.fandev.lang.fan.parsing.statements.Block;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.types.TypeParameters;
/*    */ import org.fandev.lang.fan.parsing.types.TypeSpec;
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
/*    */ public class MethodDefinition
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
/* 50 */     PsiBuilder.Marker declMarker = builder.mark();
/*    */     
/* 52 */     Facet.parse(builder);
/*    */     
/* 54 */     TokenSet modifiers = Modifiers.parse(builder, DeclarationType.METHOD);
/* 55 */     boolean modifiersParsed = ((modifiers.getTypes()).length > 0);
/*    */     
/* 57 */     if (!TypeSpec.parse(builder)) {
/* 58 */       declMarker.error(FanBundle.message("type.expected", new Object[0]));
/* 59 */       return false;
/*    */     } 
/*    */     
/* 62 */     if (!ParserUtils.parseName(builder)) {
/* 63 */       declMarker.drop();
/* 64 */       return false;
/*    */     } 
/*    */     
/* 67 */     if (FanElementTypes.TYPE_PARAMETER_LIST != TypeParameters.parse(builder)) {
/*    */       
/* 69 */       declMarker.error(FanBundle.message("type.expected", new Object[0]));
/* 70 */       return false;
/*    */     } 
/* 72 */     if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
/* 73 */       Block.parse(builder, FanElementTypes.METHOD_BODY);
/* 74 */       declMarker.done(FanElementTypes.METHOD_DEFINITION);
/* 75 */       ParserUtils.removeNls(builder);
/* 76 */       return true;
/*    */     } 
/*    */     
/* 79 */     if ((modifiersParsed && (modifiers.contains(FanTokenTypes.ABSTRACT_KEYWORD) || modifiers.contains(FanTokenTypes.NATIVE_KEYWORD))) || isBuiltInType) {
/* 80 */       declMarker.done(FanElementTypes.METHOD_DEFINITION);
/* 81 */       ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/* 82 */       return true;
/*    */     } 
/* 84 */     declMarker.error(FanBundle.message("lcurly.expected", new Object[0]));
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/members/MethodDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */