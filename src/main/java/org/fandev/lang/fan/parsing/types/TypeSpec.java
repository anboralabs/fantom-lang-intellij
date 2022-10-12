/*     */ package org.fandev.lang.fan.parsing.types;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TypeSpec
/*     */ {
/*     */   public static boolean parse(PsiBuilder builder) {
/*  19 */     ParserUtils.removeNls(builder);
/*  20 */     boolean res = (parseType(builder, false) != TypeType.NONE);
/*  21 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   private static TypeType parseFunctionOrSimpleType(PsiBuilder builder, boolean forLiteral) {
/*  26 */     if (FanTokenTypes.OR == builder.getTokenType()) {
/*  27 */       return FuncTypeSpec.parseFuncType(builder, forLiteral);
/*     */     }
/*  29 */     return SimpleTypeSpec.parseSimpleType(builder, forLiteral);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TypeType parseType(PsiBuilder builder, boolean forLiteral) {
/*  39 */     boolean bracketFlag = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     PsiBuilder.Marker typeMarker = builder.mark();
/*     */     
/*  46 */     boolean forLiteralInnerType = forLiteral;
/*  47 */     if (FanTokenTypes.LBRACKET == builder.getTokenType()) {
/*  48 */       bracketFlag = true;
/*  49 */       builder.advanceLexer();
/*     */       
/*  51 */       forLiteralInnerType = false;
/*     */     } 
/*     */     
/*  54 */     TypeType result = parseFunctionOrSimpleType(builder, forLiteralInnerType);
/*  55 */     if (result == TypeType.NONE) {
/*  56 */       typeMarker.rollbackTo();
/*  57 */       return result;
/*     */     } 
/*     */     
/*  60 */     if (FanTokenTypes.COLON != builder.getTokenType()) {
/*  61 */       if (bracketFlag) {
/*     */         
/*  63 */         if (forLiteral) {
/*     */           
/*  65 */           typeMarker.rollbackTo();
/*  66 */           return TypeType.NONE;
/*     */         } 
/*  68 */         builder.error(FanBundle.message("colon.expected", new Object[0]));
/*     */         
/*  70 */         if (ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }) == FanTokenTypes.RBRACKET) {
/*  71 */           ParserUtils.removeNls(builder);
/*  72 */           ParserUtils.advanceNoNls(builder);
/*     */         } 
/*     */       } 
/*  75 */       typeMarker.drop();
/*  76 */       return result;
/*     */     } 
/*  78 */     result = TypeType.MAP;
/*  79 */     builder.advanceLexer();
/*  80 */     TypeType valueType = parseFunctionOrSimpleType(builder, false);
/*  81 */     if (valueType != TypeType.NONE) {
/*     */ 
/*     */       
/*  84 */       if (!FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && bracketFlag) {
/*  85 */         typeMarker.error(FanBundle.message("rbrack.expected", new Object[0]));
/*  86 */         return result;
/*  87 */       }  if (FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && !bracketFlag) {
/*  88 */         typeMarker.error(FanBundle.message("rbrack.no.lbrack", new Object[0]));
/*  89 */         return result;
/*  90 */       }  if (FanTokenTypes.RBRACKET.equals(builder.getTokenType()) && bracketFlag) {
/*  91 */         builder.advanceLexer();
/*     */       }
/*  93 */       if (FanTokenTypes.LBRACKET == builder.getTokenType() || FanTokenTypes.QUEST == builder.getTokenType()) {
/*  94 */         PsiBuilder.Marker arrMarker = typeMarker;
/*  95 */         typeMarker = arrMarker.precede();
/*  96 */         result = endOfTypeParse(builder, arrMarker, forLiteral, TypeType.MAP);
/*     */       } 
/*  98 */       typeMarker.done(FanElementTypes.MAP_TYPE);
/*  99 */       return result;
/*     */     } 
/* 101 */     if (bracketFlag);
/*     */ 
/*     */     
/* 104 */     typeMarker.error(FanBundle.message("type.expected", new Object[0]));
/* 105 */     return result;
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
/*     */ 
/*     */   
/*     */   static TypeType endOfTypeParse(PsiBuilder builder, PsiBuilder.Marker marker, boolean forLiteral, TypeType defaultType) {
/* 123 */     PsiBuilder.Marker rollTo = builder.mark();
/* 124 */     if (FanTokenTypes.QUEST == builder.getTokenType()) {
/*     */       
/* 126 */       int offset = builder.getCurrentOffset();
/* 127 */       if (offset > 0) {
/* 128 */         char c = builder.getOriginalText().charAt(offset - 1);
/* 129 */         if (!Character.isWhitespace(c)) {
/* 130 */           builder.advanceLexer();
/* 131 */           rollTo.done(FanElementTypes.NULLABLE_TYPE);
/* 132 */           rollTo = builder.mark();
/*     */         } 
/*     */       } 
/*     */     } 
/* 136 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACKET)) {
/* 137 */       rollTo.rollbackTo();
/* 138 */       marker.drop();
/* 139 */       return defaultType;
/*     */     } 
/* 141 */     ParserUtils.removeNls(builder);
/* 142 */     if (!ParserUtils.getToken(builder, FanTokenTypes.RBRACKET)) {
/*     */       
/* 144 */       if (forLiteral) {
/*     */ 
/*     */         
/* 147 */         if (FanTokenTypes.COMMA == builder.getTokenType()) {
/* 148 */           rollTo.rollbackTo();
/* 149 */           marker.done(FanElementTypes.LIST_TYPE);
/* 150 */           return TypeType.LIST;
/*     */         } 
/*     */         
/* 153 */         if (FanTokenTypes.COLON == builder.getTokenType()) {
/* 154 */           rollTo.rollbackTo();
/* 155 */           marker.done(FanElementTypes.MAP_TYPE);
/* 156 */           return TypeType.MAP;
/*     */         } 
/*     */         
/* 159 */         if (defaultType != TypeType.MAP && defaultType != TypeType.LIST) {
/*     */ 
/*     */ 
/*     */           
/* 163 */           boolean hasComma = false;
/* 164 */           while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACKET) {
/* 165 */             if (builder.getTokenType() == FanTokenTypes.COMMA) {
/* 166 */               hasComma = true;
/*     */               break;
/*     */             } 
/* 169 */             builder.advanceLexer();
/*     */           } 
/* 171 */           if (hasComma) {
/* 172 */             rollTo.rollbackTo();
/* 173 */             marker.done(FanElementTypes.LIST_TYPE);
/* 174 */             return TypeType.LIST;
/*     */           } 
/*     */         } 
/*     */       } 
/* 178 */       rollTo.rollbackTo();
/* 179 */       marker.drop();
/* 180 */       return defaultType;
/*     */     } 
/* 182 */     rollTo.drop();
/* 183 */     ParserUtils.removeNls(builder);
/* 184 */     marker.done(FanElementTypes.LIST_TYPE);
/* 185 */     PsiBuilder.Marker newMarker = builder.mark();
/* 186 */     return endOfTypeParse(builder, newMarker, forLiteral, TypeType.LIST);
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/types/TypeSpec.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */