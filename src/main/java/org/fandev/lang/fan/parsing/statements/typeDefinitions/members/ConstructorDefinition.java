/*     */ package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
/*     */ import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
/*     */ import org.fandev.lang.fan.parsing.statements.Block;
/*     */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*     */ import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
/*     */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.ReferenceElement;
/*     */ import org.fandev.lang.fan.parsing.types.TypeParameters;
/*     */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstructorDefinition
/*     */ {
/*     */   public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
/*  36 */     PsiBuilder.Marker constructorMarker = builder.mark();
/*     */     
/*  38 */     Facet.parse(builder);
/*     */     
/*  40 */     TokenSet modifiers = Modifiers.parse(builder, DeclarationType.CONSTRUCTOR);
/*     */     
/*  42 */     if (!FanTokenTypes.NEW_KEYWORD.equals(builder.getTokenType())) {
/*  43 */       constructorMarker.error("Constructor should have <modifiers> new <id> ()");
/*  44 */       return false;
/*     */     } 
/*     */     
/*  47 */     ParserUtils.advanceNoNls(builder);
/*     */     
/*  49 */     if (!ParserUtils.parseName(builder)) {
/*  50 */       constructorMarker.drop();
/*  51 */       return false;
/*     */     } 
/*  53 */     ParserUtils.removeNls(builder);
/*     */ 
/*     */     
/*  56 */     if (FanElementTypes.TYPE_PARAMETER_LIST != TypeParameters.parse(builder)) {
/*  57 */       builder.error(FanBundle.message("params.expected", new Object[0]));
/*  58 */       constructorMarker.drop();
/*  59 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  63 */     parseCtorChain(builder);
/*     */     
/*  65 */     ParserUtils.removeNls(builder);
/*     */     
/*  67 */     if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
/*  68 */       Block.parse(builder, FanElementTypes.METHOD_BODY);
/*  69 */       constructorMarker.done(FanElementTypes.CTOR_DEFINITION);
/*  70 */       ParserUtils.removeNls(builder);
/*  71 */       return true;
/*  72 */     }  if (isBuiltInType) {
/*  73 */       constructorMarker.done(FanElementTypes.CTOR_DEFINITION);
/*  74 */       ParserUtils.removeNls(builder);
/*  75 */       return true;
/*     */     } 
/*  77 */     constructorMarker.error(FanBundle.message("lcurly.expected", new Object[0]));
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean parseCtorChain(PsiBuilder builder) {
/*  94 */     if (FanTokenTypes.COLON == builder.getTokenType()) {
/*  95 */       PsiBuilder.Marker ctorChainMarker = builder.mark();
/*     */       
/*  97 */       ParserUtils.advanceNoNls(builder);
/*  98 */       if (FanTokenTypes.SUPER_KEYWORD == builder.getTokenType() || FanTokenTypes.THIS_KEYWORD == builder.getTokenType()) {
/*     */         
/* 100 */         builder.advanceLexer();
/*     */         
/* 102 */         if (FanTokenTypes.DOT == builder.getTokenType()) {
/* 103 */           builder.advanceLexer();
/* 104 */           if (!ReferenceElement.parseReferenceElement(builder)) {
/* 105 */             ctorChainMarker.error(FanBundle.message("identifier.expected", new Object[0]));
/*     */           }
/*     */         } 
/*     */         
/* 109 */         if (Arguments.parse(builder)) {
/* 110 */           ctorChainMarker.done(FanElementTypes.CTOR_CHAIN);
/* 111 */           return true;
/*     */         } 
/* 113 */         ctorChainMarker.error(FanBundle.message("argument.expected", new Object[0]));
/*     */       } else {
/*     */         
/* 116 */         ctorChainMarker.error(FanBundle.message("super.or.this.expected", new Object[0]));
/*     */       } 
/*     */     } 
/* 119 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/typeDefinitions/members/ConstructorDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */