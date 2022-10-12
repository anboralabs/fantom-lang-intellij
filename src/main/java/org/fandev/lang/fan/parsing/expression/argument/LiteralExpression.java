/*     */ package org.fandev.lang.fan.parsing.expression.argument;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.expression.Expression;
/*     */ import org.fandev.lang.fan.parsing.types.TypeSpec;
/*     */ import org.fandev.lang.fan.parsing.types.TypeType;
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
/*     */ public class LiteralExpression
/*     */ {
/*  35 */   private static final TokenSet LITERALS = TokenSet.orSet(new TokenSet[] { FanTokenTypes.FAN_LITERALS, TokenSet.create(new IElementType[] { FanTokenTypes.NULL_KEYWORD, FanTokenTypes.THIS_KEYWORD, FanTokenTypes.SUPER_KEYWORD }) });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private static final TokenSet COLON_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.COLON });
/*  41 */   private static final TokenSet COLON_COMMA_RBRACKET_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.COLON, FanTokenTypes.COMMA, FanTokenTypes.RBRACKET });
/*  42 */   private static final TokenSet RBRACKET_COMMA = TokenSet.create(new IElementType[] { FanTokenTypes.RBRACKET, FanTokenTypes.COMMA });
/*     */ 
/*     */   
/*     */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/*  46 */     PsiBuilder.Marker marker = builder.mark();
/*     */     
/*  48 */     if (FanTokenTypes.THIS_KEYWORD.equals(builder.getTokenType())) {
/*  49 */       builder.advanceLexer();
/*  50 */       marker.done(FanElementTypes.THIS_REFERENCE_EXPRESSION);
/*  51 */       return true;
/*  52 */     }  if (FanTokenTypes.SUPER_KEYWORD.equals(builder.getTokenType())) {
/*  53 */       builder.advanceLexer();
/*  54 */       marker.done(FanElementTypes.SUPER_REFERENCE_EXPRESSION);
/*  55 */       return true;
/*     */     } 
/*  57 */     if (LITERALS.contains(builder.getTokenType())) {
/*  58 */       builder.advanceLexer();
/*  59 */       marker.done(FanElementTypes.LITERAL);
/*  60 */       return true;
/*     */     } 
/*     */     
/*  63 */     if (ParserUtils.getToken(builder, FanTokenTypes.SHARP)) {
/*  64 */       if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
/*  65 */         marker.done(FanElementTypes.LITERAL);
/*  66 */         return true;
/*     */       } 
/*  68 */       marker.drop();
/*  69 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  73 */     TypeType typeType = TypeSpec.parseType(builder, true);
/*  74 */     if (typeType != TypeType.NONE) {
/*  75 */       if (ParserUtils.getToken(builder, FanTokenTypes.SHARP)) {
/*     */         
/*  77 */         ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET);
/*  78 */         marker.done(FanElementTypes.LITERAL);
/*  79 */         return true;
/*     */       } 
/*  81 */       if (ParserUtils.getToken(builder, FanTokenTypes.DSL_STRING)) {
/*  82 */         marker.done(FanElementTypes.LITERAL);
/*  83 */         return true;
/*     */       } 
/*  85 */       if (FanTokenTypes.LBRACKET == builder.getTokenType()) {
/*  86 */         if (typeType == TypeType.MAP) {
/*  87 */           return parseListOrMapLiteral(builder, LiteralType.MAP, marker);
/*     */         }
/*  89 */         if (typeType == TypeType.LIST) {
/*  90 */           return parseListOrMapLiteral(builder, LiteralType.LIST, marker);
/*     */         }
/*     */       } 
/*  93 */       marker.rollbackTo();
/*  94 */       marker = builder.mark();
/*     */     } 
/*  96 */     return parseListOrMapLiteral(builder, LiteralType.UNKNOW, marker);
/*     */   }
/*     */   
/*     */   enum LiteralType {
/* 100 */     UNKNOW, LIST, MAP, ERROR;
/*     */   }
/*     */   
/*     */   private static boolean parseListOrMapLiteral(PsiBuilder builder, LiteralType litType, PsiBuilder.Marker marker) {
/* 104 */     if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACKET)) {
/* 105 */       marker.rollbackTo();
/* 106 */       return false;
/*     */     } 
/* 108 */     ParserUtils.removeNls(builder);
/*     */     
/* 110 */     LiteralType emptyLiteralType = emptyMapOrList(builder, litType);
/* 111 */     switch (emptyLiteralType) {
/*     */       case LIST:
/* 113 */         ParserUtils.removeNls(builder);
/* 114 */         ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/* 115 */         marker.done(FanElementTypes.LIST_LITERAL);
/* 116 */         return true;
/*     */       case MAP:
/* 118 */         ParserUtils.removeNls(builder);
/* 119 */         ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/* 120 */         marker.done(FanElementTypes.MAP_LITERAL);
/* 121 */         return true;
/*     */       case ERROR:
/* 123 */         marker.rollbackTo();
/* 124 */         return false;
/*     */     } 
/*     */ 
/*     */     
/* 128 */     litType = mapOrListLiteralWithValues(builder, litType);
/* 129 */     switch (litType) {
/*     */       case LIST:
/* 131 */         ParserUtils.removeNls(builder);
/* 132 */         ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/* 133 */         marker.done(FanElementTypes.LIST_LITERAL);
/* 134 */         return true;
/*     */       case MAP:
/* 136 */         ParserUtils.removeNls(builder);
/* 137 */         ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/* 138 */         marker.done(FanElementTypes.MAP_LITERAL);
/* 139 */         return true;
/*     */     } 
/* 141 */     marker.rollbackTo();
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   private static LiteralType mapOrListLiteralWithValues(PsiBuilder builder, LiteralType litType) {
/* 146 */     PsiBuilder.Marker valMark = builder.mark();
/*     */     
/* 148 */     boolean res = Expression.parseExpr(builder, COLON_COMMA_RBRACKET_STOPPER, FanElementTypes.EXPRESSION);
/* 149 */     if (res) {
/* 150 */       litType = findLiteralType(builder, litType);
/*     */     }
/* 152 */     if (!res || litType == LiteralType.ERROR) {
/* 153 */       valMark.drop();
/* 154 */       return LiteralType.ERROR;
/*     */     } 
/* 156 */     if (litType == LiteralType.LIST) {
/* 157 */       valMark.done(FanElementTypes.LIST_ITEM);
/* 158 */       if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
/* 159 */         ParserUtils.advanceNoNls(builder);
/*     */       }
/* 161 */       while (res && !builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
/* 162 */         res = Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.LIST_ITEM);
/* 163 */         ParserUtils.removeNls(builder);
/* 164 */         if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
/* 165 */           ParserUtils.advanceNoNls(builder);
/*     */         }
/*     */       } 
/* 168 */       return LiteralType.LIST;
/*     */     } 
/* 170 */     if (litType == LiteralType.MAP) {
/* 171 */       PsiBuilder.Marker mapEntryMark = valMark.precede();
/* 172 */       valMark.done(FanElementTypes.MAP_ITEM_KEY);
/* 173 */       ParserUtils.advanceNoNls(builder);
/* 174 */       if (!Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.MAP_ITEM_VALUE)) {
/* 175 */         mapEntryMark.drop();
/* 176 */         return LiteralType.ERROR;
/*     */       } 
/* 178 */       mapEntryMark.done(FanElementTypes.MAP_ITEM);
/* 179 */       ParserUtils.removeNls(builder);
/* 180 */       if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
/* 181 */         ParserUtils.advanceNoNls(builder);
/*     */       }
/* 183 */       while (!builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
/* 184 */         mapEntryMark = builder.mark();
/* 185 */         if (Expression.parseExpr(builder, COLON_STOPPER, FanElementTypes.MAP_ITEM_KEY)) {
/* 186 */           ParserUtils.advanceNoNls(builder);
/* 187 */           if (Expression.parseExpr(builder, RBRACKET_COMMA, FanElementTypes.MAP_ITEM_VALUE)) {
/* 188 */             mapEntryMark.done(FanElementTypes.MAP_ITEM);
/* 189 */             ParserUtils.removeNls(builder);
/* 190 */             if (FanTokenTypes.COMMA.equals(builder.getTokenType()))
/* 191 */               ParserUtils.advanceNoNls(builder); 
/*     */             continue;
/*     */           } 
/* 194 */           mapEntryMark.drop();
/* 195 */           return LiteralType.MAP;
/*     */         } 
/*     */         
/* 198 */         mapEntryMark.drop();
/* 199 */         return LiteralType.MAP;
/*     */       } 
/*     */       
/* 202 */       return LiteralType.MAP;
/*     */     } 
/* 204 */     return LiteralType.ERROR;
/*     */   }
/*     */   
/*     */   private static LiteralType findLiteralType(PsiBuilder builder, LiteralType litType) {
/* 208 */     switch (litType)
/*     */     { case LIST:
/* 210 */         if (!RBRACKET_COMMA.contains(builder.getTokenType())) {
/* 211 */           builder.error(FanBundle.message("comma.rbracket.expected", new Object[0]));
/* 212 */           litType = LiteralType.ERROR;
/*     */         } 
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
/* 233 */         return litType;case MAP: if (!FanTokenTypes.COLON.equals(builder.getTokenType())) { builder.error(FanBundle.message("colon.expected", new Object[0])); litType = LiteralType.ERROR; }  return litType; }  if (RBRACKET_COMMA.contains(builder.getTokenType())) { litType = LiteralType.LIST; } else if (FanTokenTypes.COLON.equals(builder.getTokenType())) { litType = LiteralType.MAP; } else { builder.error(FanBundle.message("literal.listOrMap.expected", new Object[0])); litType = LiteralType.ERROR; }  return litType;
/*     */   }
/*     */   
/*     */   private static LiteralType emptyMapOrList(PsiBuilder builder, LiteralType litType) {
/* 237 */     LiteralType res = LiteralType.UNKNOW;
/* 238 */     if (ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {
/*     */       
/* 240 */       if (litType == LiteralType.UNKNOW) {
/* 241 */         res = LiteralType.LIST;
/* 242 */       } else if (litType != LiteralType.LIST) {
/*     */ 
/*     */ 
/*     */         
/* 246 */         res = LiteralType.LIST;
/*     */       } else {
/* 248 */         res = litType;
/*     */       } 
/* 250 */     } else if (ParserUtils.getToken(builder, FanTokenTypes.COLON)) {
/*     */       
/* 252 */       if (litType == LiteralType.UNKNOW) {
/* 253 */         res = LiteralType.MAP;
/* 254 */       } else if (litType != LiteralType.MAP) {
/* 255 */         builder.error(FanBundle.message("literal.map.unexpected", new Object[0]));
/*     */         
/* 257 */         res = LiteralType.MAP;
/*     */       } else {
/* 259 */         res = litType;
/*     */       } 
/*     */     } 
/* 262 */     return res;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/argument/LiteralExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */