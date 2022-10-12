/*     */ package org.fandev.lang.fan.parsing.types;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FuncTypeSpec
/*     */ {
/*     */   public static TypeType parseFuncType(PsiBuilder builder, boolean forLiteral) {
/*  41 */     if (FanTokenTypes.OR != builder.getTokenType()) {
/*  42 */       return TypeType.NONE;
/*     */     }
/*     */     
/*  45 */     PsiBuilder.Marker funcMarker = builder.mark();
/*  46 */     builder.advanceLexer();
/*  47 */     parseFormals(builder);
/*     */ 
/*     */     
/*  50 */     if (FanTokenTypes.DYN_CALL == builder.getTokenType()) {
/*  51 */       builder.advanceLexer();
/*  52 */       if (TypeSpec.parseType(builder, false) != TypeType.NONE) {
/*  53 */         return parseClosingOr(builder, funcMarker, forLiteral);
/*     */       }
/*  55 */       funcMarker.error(FanBundle.message("type.expected", new Object[0]));
/*     */     } else {
/*     */       
/*  58 */       return parseClosingOr(builder, funcMarker, forLiteral);
/*     */     } 
/*  60 */     return TypeType.NONE;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TypeType parseClosingOr(PsiBuilder builder, PsiBuilder.Marker funcMarker, boolean forLiteral) {
/*  65 */     if (FanTokenTypes.OR == builder.getTokenType()) {
/*  66 */       builder.advanceLexer();
/*  67 */       funcMarker.done(FanElementTypes.FUNC_TYPE);
/*  68 */       return TypeSpec.endOfTypeParse(builder, builder.mark(), forLiteral, TypeType.FUNCTION);
/*     */     } 
/*  70 */     funcMarker.error(FanBundle.message("or.expected", new Object[0]));
/*  71 */     return TypeType.NONE;
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
/*     */   public static boolean parseFormals(PsiBuilder builder) {
/*  83 */     PsiBuilder.Marker formalsMarker = builder.mark();
/*  84 */     boolean commaExpected = false;
/*  85 */     while (!builder.eof() && !FanTokenTypes.DYN_CALL.equals(builder.getTokenType())) {
/*  86 */       if (commaExpected) {
/*  87 */         if (FanTokenTypes.COMMA.equals(builder.getTokenType()))
/*     */         
/*  89 */         { builder.advanceLexer(); }
/*  90 */         else { if (FanTokenTypes.OR.equals(builder.getTokenType())) {
/*     */             break;
/*     */           }
/*  93 */           formalsMarker.error(FanBundle.message("comma.expected", new Object[0]));
/*  94 */           return false; }
/*     */ 
/*     */       
/*  97 */       } else if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
/*     */         
/*  99 */         builder.advanceLexer();
/*     */         
/*     */         break;
/*     */       } 
/* 103 */       PsiBuilder.Marker formalMarker = builder.mark();
/* 104 */       if (TypeSpec.parseType(builder, false) != TypeType.NONE) {
/* 105 */         commaExpected = true;
/* 106 */         if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 107 */           ParserUtils.parseName(builder);
/*     */         }
/* 109 */         formalMarker.done(FanElementTypes.FORMAL); continue;
/*     */       } 
/* 111 */       formalMarker.rollbackTo();
/* 112 */       formalsMarker.error(FanBundle.message("type.expected", new Object[0]));
/* 113 */       return false;
/*     */     } 
/*     */     
/* 116 */     formalsMarker.done(FanElementTypes.FORMALS);
/* 117 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/types/FuncTypeSpec.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */