/*     */ package org.fandev.lang.fan.parsing.util;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParserUtils
/*     */ {
/*     */   public static boolean getToken(PsiBuilder builder, IElementType elem) {
/*  16 */     if (elem.equals(builder.getTokenType())) {
/*  17 */       builder.advanceLexer();
/*  18 */       return true;
/*     */     } 
/*  20 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean getToken(PsiBuilder builder, TokenSet tokens) {
/*  24 */     if (tokens.contains(builder.getTokenType())) {
/*  25 */       builder.advanceLexer();
/*  26 */       return true;
/*     */     } 
/*  28 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean getToken(PsiBuilder builder, IElementType elem, String errorMsg) {
/*  32 */     if (elem.equals(builder.getTokenType())) {
/*  33 */       builder.advanceLexer();
/*  34 */       return true;
/*     */     } 
/*  36 */     if (errorMsg != null) {
/*  37 */       builder.error(errorMsg);
/*     */     }
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getToken(PsiBuilder builder, TokenSet tokens, String errorMsg) {
/*  44 */     if (tokens.contains(builder.getTokenType())) {
/*  45 */       builder.advanceLexer();
/*  46 */       return true;
/*     */     } 
/*  48 */     if (errorMsg != null) {
/*  49 */       builder.error(errorMsg);
/*     */     }
/*  51 */     return false;
/*     */   }
/*     */   
/*     */   public static IElementType firstAfter(PsiBuilder builder, IElementType... elems) {
/*     */     IElementType result;
/*  56 */     TokenSet ignored = TokenSet.create(elems);
/*     */     
/*  58 */     if (ignored.contains(builder.getTokenType())) {
/*  59 */       PsiBuilder.Marker rb = builder.mark();
/*  60 */       while (!builder.eof() && ignored.contains(builder.getTokenType())) {
/*  61 */         builder.advanceLexer();
/*     */       }
/*  63 */       result = builder.getTokenType();
/*  64 */       rb.rollbackTo();
/*     */     } else {
/*  66 */       result = builder.getTokenType();
/*     */     } 
/*  68 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean lookAhead(PsiBuilder builder, IElementType... elems) {
/*  72 */     if (!elems[0].equals(builder.getTokenType())) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     if (elems.length == 1) {
/*  77 */       return true;
/*     */     }
/*     */     
/*  80 */     PsiBuilder.Marker rb = builder.mark();
/*  81 */     builder.advanceLexer();
/*  82 */     int i = 1;
/*  83 */     while (!builder.eof() && i < elems.length && elems[i].equals(builder.getTokenType())) {
/*  84 */       builder.advanceLexer();
/*  85 */       i++;
/*     */     } 
/*  87 */     rb.rollbackTo();
/*  88 */     return (i == elems.length);
/*     */   }
/*     */   
/*     */   public static boolean lookAheadForElement(PsiBuilder builder, IElementType elem, IElementType... stopElements) {
/*  92 */     TokenSet stopElem = TokenSet.create(stopElements);
/*  93 */     return lookAheadForElement(builder, elem, stopElem);
/*     */   }
/*     */   
/*     */   public static boolean lookAheadForElement(PsiBuilder builder, IElementType elem, TokenSet stopElem) {
/*  97 */     PsiBuilder.Marker rb = builder.mark();
/*  98 */     while (!builder.eof() && elem != builder.getTokenType() && !stopElem.contains(builder.getTokenType())) {
/*  99 */       builder.advanceLexer();
/*     */     }
/* 101 */     if (builder.eof()) {
/* 102 */       rb.rollbackTo();
/* 103 */       return false;
/* 104 */     }  if (stopElem.contains(builder.getTokenType())) {
/* 105 */       rb.rollbackTo();
/* 106 */       return false;
/*     */     } 
/* 108 */     rb.rollbackTo();
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IElementType eatElement(PsiBuilder builder, IElementType elem) {
/* 114 */     PsiBuilder.Marker marker = builder.mark();
/* 115 */     builder.advanceLexer();
/* 116 */     marker.done(elem);
/* 117 */     return elem;
/*     */   }
/*     */   
/*     */   public static void advanceToBlockEnd(PsiBuilder builder) {
/* 121 */     advanceToBlockEnd(builder, FanTokenTypes.LBRACE, FanTokenTypes.RBRACE);
/*     */   }
/*     */   
/*     */   public static void advanceToArgumentsEnd(PsiBuilder builder) {
/* 125 */     advanceToBlockEnd(builder, FanTokenTypes.LPAR, FanTokenTypes.RPAR);
/*     */   }
/*     */   
/*     */   public static void advanceToArrayEnd(PsiBuilder builder) {
/* 129 */     advanceToBlockEnd(builder, FanTokenTypes.LBRACKET, FanTokenTypes.RBRACKET);
/*     */   }
/*     */   
/*     */   public static void advanceToBlockEnd(PsiBuilder builder, IElementType opening, IElementType closing) {
/* 133 */     int openLeft = 1;
/* 134 */     while (!builder.eof() && openLeft > 0) {
/* 135 */       builder.advanceLexer();
/* 136 */       if (builder.getTokenType() == opening) {
/* 137 */         openLeft++; continue;
/* 138 */       }  if (builder.getTokenType() == closing) {
/* 139 */         openLeft--;
/*     */       }
/*     */     } 
/* 142 */     builder.advanceLexer();
/*     */   }
/*     */   
/*     */   public static boolean advanceTo(PsiBuilder builder, TokenSet stopper) {
/* 146 */     while (!builder.eof() && !stopper.contains(builder.getTokenType())) {
/* 147 */       builder.advanceLexer();
/*     */     }
/* 149 */     return stopper.contains(builder.getTokenType());
/*     */   }
/*     */   
/*     */   public static void advanceNoNls(PsiBuilder builder) {
/* 153 */     builder.advanceLexer();
/* 154 */     removeNls(builder);
/*     */   }
/*     */   
/*     */   public static void removeNls(PsiBuilder builder) {
/* 158 */     while (FanTokenTypes.NLS.equals(builder.getTokenType())) {
/* 159 */       builder.advanceLexer();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void cleanAfterErrorInBlock(PsiBuilder builder) {
/* 164 */     PsiBuilder.Marker em = builder.mark();
/* 165 */     advanceToBlockEnd(builder);
/* 166 */     em.error(FanBundle.message("separator.expected", new Object[0]));
/*     */   }
/*     */   
/*     */   public static void cleanAfterErrorInArguments(PsiBuilder builder) {
/* 170 */     PsiBuilder.Marker em = builder.mark();
/* 171 */     advanceToArgumentsEnd(builder);
/* 172 */     em.error(FanBundle.message("separator.expected", new Object[0]));
/*     */   }
/*     */   
/*     */   public static void cleanAfterErrorInArray(PsiBuilder builder) {
/* 176 */     PsiBuilder.Marker em = builder.mark();
/* 177 */     advanceToArrayEnd(builder);
/* 178 */     em.error(FanBundle.message("separator.expected", new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean parseName(PsiBuilder builder) {
/* 183 */     if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 184 */       builder.error(FanBundle.message("identifier.expected", new Object[0]));
/* 185 */       return false;
/*     */     } 
/* 187 */     PsiBuilder.Marker idMark = builder.mark();
/* 188 */     builder.advanceLexer();
/* 189 */     idMark.done(FanElementTypes.NAME_ELEMENT);
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public static void removeStoppers(PsiBuilder builder, TokenSet stopper, TokenSet reccuring) {
/* 194 */     if (stopper.contains(builder.getTokenType())) {
/* 195 */       builder.advanceLexer();
/*     */     }
/* 197 */     while (reccuring.contains(builder.getTokenType()))
/* 198 */       builder.advanceLexer(); 
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/util/ParserUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */