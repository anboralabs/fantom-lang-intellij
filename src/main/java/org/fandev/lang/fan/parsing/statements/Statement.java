/*     */ package org.fandev.lang.fan.parsing.statements;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.expression.Expression;
/*     */ import org.fandev.lang.fan.parsing.types.SimpleTypeSpec;
/*     */ import org.fandev.lang.fan.parsing.types.TypeSpec;
/*     */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Statement
/*     */ {
/*  21 */   public static final TokenSet FOR_STOPPERS = TokenSet.create(new IElementType[] { FanTokenTypes.SEMICOLON, FanTokenTypes.RPAR });
/*  22 */   public static final TokenSet RPAR_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.RPAR });
/*  23 */   public static final TokenSet SWITCH_CASE_STOPPER = TokenSet.create(new IElementType[] { FanTokenTypes.COLON, FanTokenTypes.RBRACE });
/*  24 */   public static final TokenSet CLOSURE_EOS = TokenSet.orSet(new TokenSet[] { FanTokenTypes.EOS, TokenSet.create(new IElementType[] { FanTokenTypes.COMMA }) });
/*     */   
/*     */   public static boolean parse(PsiBuilder builder) {
/*  27 */     return parse(builder, false);
/*     */   }
/*     */   
/*     */   public static boolean parse(PsiBuilder builder, boolean inClosure) {
/*  31 */     IElementType statementType = null;
/*  32 */     PsiBuilder.Marker statementMark = builder.mark();
/*  33 */     IElementType tokenType = builder.getTokenType();
/*  34 */     if (FanTokenTypes.BREAK_KEYWORD.equals(tokenType) || FanTokenTypes.CONTINUE_KEYWORD.equals(tokenType)) {
/*     */       
/*  36 */       builder.advanceLexer();
/*     */       
/*  38 */       if (FanTokenTypes.EOS.contains(builder.getTokenType())) {
/*  39 */         ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*  40 */         statementType = FanElementTypes.CONTROL_FLOW;
/*     */       } else {
/*  42 */         builder.error(FanBundle.message("separator.expected", new Object[0]));
/*     */       } 
/*  44 */     } else if (FanTokenTypes.FOR_KEYWORD.equals(tokenType)) {
/*  45 */       statementType = FanElementTypes.FOR_STATEMENT;
/*  46 */       parseFor(builder);
/*  47 */     } else if (FanTokenTypes.IF_KEYWORD.equals(tokenType)) {
/*  48 */       statementType = FanElementTypes.IF_STATEMENT;
/*  49 */       parseIf(builder);
/*  50 */     } else if (FanTokenTypes.RETURN_KEYWORD.equals(tokenType)) {
/*  51 */       statementType = FanElementTypes.RETURN_STATEMENT;
/*  52 */       parseReturnExpression(builder);
/*  53 */     } else if (FanTokenTypes.SWITCH_KEYWORD.equals(tokenType)) {
/*  54 */       statementType = FanElementTypes.SWITCH_STATEMENT;
/*  55 */       parseSwitch(builder);
/*  56 */     } else if (FanTokenTypes.THROW_KEYWORD.equals(tokenType)) {
/*  57 */       statementType = FanElementTypes.THROW_STATEMENT;
/*  58 */       parseThrowExpression(builder);
/*  59 */     } else if (FanTokenTypes.WHILE_KEYWORD.equals(tokenType)) {
/*  60 */       statementType = FanElementTypes.WHILE_STATEMENT;
/*  61 */       parseWhile(builder);
/*  62 */     } else if (FanTokenTypes.TRY_KEYWORD.equals(tokenType)) {
/*  63 */       statementType = FanElementTypes.TRY_STATEMENT;
/*  64 */       parseTry(builder);
/*     */     } else {
/*  66 */       TokenSet stopper = inClosure ? CLOSURE_EOS : FanTokenTypes.EOS;
/*  67 */       boolean res = expressionOrLocalDef(builder, stopper, FanElementTypes.EXPRESSION, FanElementTypes.LOCAL_DEF_STATEMENT);
/*  68 */       if (inClosure && res && ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {
/*  69 */         statementMark.done(FanElementTypes.IT_ADD_STATEMENT);
/*     */       } else {
/*  71 */         statementMark.drop();
/*     */       } 
/*  73 */       ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*  74 */       return res;
/*     */     } 
/*  76 */     if (statementType != null) {
/*  77 */       statementMark.done(statementType);
/*  78 */       ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*  79 */       return true;
/*     */     } 
/*  81 */     statementMark.drop();
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean parseFor(PsiBuilder builder) {
/*  87 */     if (!ParserUtils.getToken(builder, FanTokenTypes.FOR_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.FOR_KEYWORD }))) {
/*  88 */       return false;
/*     */     }
/*  90 */     ParserUtils.removeNls(builder);
/*  91 */     ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
/*  92 */     expressionOrLocalDef(builder, FOR_STOPPERS, FanElementTypes.FOR_INIT_EXPR, FanElementTypes.FOR_INIT_LOCAL_DEF);
/*  93 */     ParserUtils.removeNls(builder);
/*  94 */     ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON, FanBundle.message("semicolon.expected", new Object[0]));
/*  95 */     ParserUtils.removeNls(builder);
/*  96 */     Expression.parseExpr(builder, FOR_STOPPERS, FanElementTypes.FOR_CONDITION);
/*  97 */     ParserUtils.removeNls(builder);
/*  98 */     ParserUtils.getToken(builder, FanTokenTypes.SEMICOLON, FanBundle.message("semicolon.expected", new Object[0]));
/*  99 */     ParserUtils.removeNls(builder);
/* 100 */     Expression.parseExpr(builder, FOR_STOPPERS, FanElementTypes.FOR_REPEAT);
/* 101 */     ParserUtils.removeNls(builder);
/* 102 */     ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 103 */     ParserUtils.removeNls(builder);
/* 104 */     Block.parse(builder, FanElementTypes.FOR_BLOCK);
/* 105 */     ParserUtils.removeNls(builder);
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean expressionOrLocalDef(PsiBuilder builder, TokenSet stopper, IElementType exprType, IElementType localDefType) {
/* 111 */     ParserUtils.removeNls(builder);
/*     */     
/* 113 */     TokenSet lookAheadStoppers = TokenSet.orSet(new TokenSet[] { stopper, TokenSet.create(new IElementType[] { FanTokenTypes.LBRACE }) });
/* 114 */     if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.COLON_EQ, lookAheadStoppers)) {
/* 115 */       return parseLocalDef(builder, stopper, localDefType);
/*     */     }
/*     */     
/* 118 */     return Expression.parseExpr(builder, stopper, exprType);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean parseLocalDef(PsiBuilder builder, TokenSet stopper, IElementType localDefType) {
/* 123 */     boolean res = true;
/*     */     
/* 125 */     PsiBuilder.Marker localDef = builder.mark();
/*     */     
/* 127 */     PsiBuilder.Marker nameMark = builder.mark();
/* 128 */     if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET) && FanTokenTypes.COLON_EQ == builder.getTokenType()) {
/* 129 */       nameMark.done(FanElementTypes.NAME_ELEMENT);
/*     */     } else {
/* 131 */       nameMark.rollbackTo();
/* 132 */       res = TypeSpec.parse(builder);
/* 133 */       if (res) {
/* 134 */         ParserUtils.removeNls(builder);
/* 135 */         res = ParserUtils.parseName(builder);
/*     */       } 
/*     */     } 
/* 138 */     if (res && ParserUtils.getToken(builder, FanTokenTypes.COLON_EQ, FanBundle.message("localDef.assign.expected", new Object[0]))) {
/* 139 */       ParserUtils.removeNls(builder);
/* 140 */       res = Expression.parseExpr(builder, stopper, FanElementTypes.PARAM_DEFAULT);
/*     */     } 
/* 142 */     if (res) {
/* 143 */       localDef.done(localDefType);
/* 144 */       return true;
/*     */     } 
/*     */     
/* 147 */     localDef.done(localDefType);
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean parseIf(PsiBuilder builder) {
/* 153 */     if (!ParserUtils.getToken(builder, FanTokenTypes.IF_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.IF_KEYWORD }))) {
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     parseIfCondition(builder);
/* 158 */     boolean res = Block.parse(builder, FanElementTypes.COND_TRUE_BLOCK);
/* 159 */     ParserUtils.removeNls(builder);
/* 160 */     while (res && !builder.eof() && ParserUtils.getToken(builder, FanTokenTypes.ELSE_KEYWORD)) {
/* 161 */       ParserUtils.removeNls(builder);
/* 162 */       if (ParserUtils.getToken(builder, FanTokenTypes.IF_KEYWORD)) {
/* 163 */         parseIfCondition(builder);
/* 164 */         res = Block.parse(builder, FanElementTypes.COND_TRUE_BLOCK);
/*     */       } else {
/* 166 */         res = Block.parse(builder, FanElementTypes.COND_FALSE_BLOCK);
/*     */       } 
/* 168 */       ParserUtils.removeNls(builder);
/*     */     } 
/* 170 */     return res;
/*     */   }
/*     */   
/*     */   private static void parseIfCondition(PsiBuilder builder) {
/* 174 */     ParserUtils.removeNls(builder);
/* 175 */     ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
/* 176 */     ParserUtils.removeNls(builder);
/* 177 */     Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.CONDITION_EXPR);
/* 178 */     ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 179 */     ParserUtils.removeNls(builder);
/*     */   }
/*     */   
/*     */   private static boolean parseReturnExpression(PsiBuilder builder) {
/* 183 */     if (!ParserUtils.getToken(builder, FanTokenTypes.RETURN_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.RETURN_KEYWORD }))) {
/* 184 */       return false;
/*     */     }
/* 186 */     boolean res = true;
/* 187 */     if (!FanTokenTypes.EOS.contains(builder.getTokenType())) {
/* 188 */       res = Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.EXPRESSION);
/* 189 */       ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*     */     } 
/* 191 */     return res;
/*     */   }
/*     */   
/*     */   private static boolean parseThrowExpression(PsiBuilder builder) {
/* 195 */     if (!ParserUtils.getToken(builder, FanTokenTypes.THROW_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.THROW_KEYWORD }))) {
/* 196 */       return false;
/*     */     }
/* 198 */     boolean res = Expression.parseExpr(builder, FanTokenTypes.EOS, FanElementTypes.EXPRESSION);
/* 199 */     ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/* 200 */     return res;
/*     */   }
/*     */   
/*     */   private static boolean parseSwitch(PsiBuilder builder) {
/* 204 */     if (!ParserUtils.getToken(builder, FanTokenTypes.SWITCH_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.SWITCH_KEYWORD }))) {
/* 205 */       return false;
/*     */     }
/* 207 */     ParserUtils.removeNls(builder);
/* 208 */     ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
/* 209 */     ParserUtils.removeNls(builder);
/* 210 */     Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.SWITCH_VALUE);
/* 211 */     ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 212 */     ParserUtils.removeNls(builder);
/* 213 */     ParserUtils.getToken(builder, FanTokenTypes.LBRACE, FanBundle.message("lcurly.expected", new Object[0]));
/* 214 */     ParserUtils.removeNls(builder);
/* 215 */     boolean hasDefault = false;
/* 216 */     while (!builder.eof() && !FanTokenTypes.RBRACE.equals(builder.getTokenType())) {
/* 217 */       PsiBuilder.Marker inSwitchMark = builder.mark();
/* 218 */       if (ParserUtils.getToken(builder, FanTokenTypes.CASE_KEYWORD)) {
/* 219 */         if (hasDefault) {
/* 220 */           builder.error(FanBundle.message("case.after.default", new Object[0]));
/*     */         }
/* 222 */         Expression.parseExpr(builder, SWITCH_CASE_STOPPER, FanElementTypes.SWITCH_CASE_VALUE);
/* 223 */       } else if (ParserUtils.getToken(builder, FanTokenTypes.DEFAULT_KEYWORD)) {
/* 224 */         hasDefault = true;
/*     */       } else {
/* 226 */         inSwitchMark.error(FanBundle.message("case.default.expected", new Object[0]));
/* 227 */         ParserUtils.advanceNoNls(builder);
/*     */         continue;
/*     */       } 
/* 230 */       if (ParserUtils.getToken(builder, FanTokenTypes.COLON, FanBundle.message("colon.expected", new Object[0]))) {
/* 231 */         ParserUtils.removeNls(builder);
/* 232 */         PsiBuilder.Marker mark = builder.mark();
/* 233 */         while (!builder.eof() && !FanTokenTypes.SWITCH_BLOCK_TOKENS.contains(builder.getTokenType())) {
/* 234 */           if (!parse(builder)) {
/* 235 */             builder.error(FanBundle.message("expression.expected", new Object[0]));
/*     */             
/* 237 */             ParserUtils.advanceNoNls(builder);
/* 238 */             ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);
/*     */           } 
/*     */         } 
/* 241 */         mark.done(FanElementTypes.SWITCH_CASE_STATEMENT);
/*     */       } 
/* 243 */       inSwitchMark.done(FanElementTypes.SWITCH_CASE);
/* 244 */       ParserUtils.removeNls(builder);
/*     */     } 
/* 246 */     ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]));
/* 247 */     ParserUtils.removeNls(builder);
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean parseWhile(PsiBuilder builder) {
/* 252 */     if (!ParserUtils.getToken(builder, FanTokenTypes.WHILE_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.WHILE_KEYWORD }))) {
/* 253 */       return false;
/*     */     }
/* 255 */     ParserUtils.removeNls(builder);
/* 256 */     ParserUtils.getToken(builder, FanTokenTypes.LPAR, FanBundle.message("lpar.expected", new Object[0]));
/* 257 */     ParserUtils.removeNls(builder);
/* 258 */     Expression.parseExpr(builder, RPAR_STOPPER, FanElementTypes.WHILE_CONDITION);
/* 259 */     ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 260 */     ParserUtils.removeNls(builder);
/* 261 */     Block.parse(builder, FanElementTypes.WHILE_BLOCK);
/* 262 */     ParserUtils.removeNls(builder);
/* 263 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean parseTry(PsiBuilder builder) {
/* 267 */     if (!ParserUtils.getToken(builder, FanTokenTypes.TRY_KEYWORD, FanBundle.message("keywords.expected", new Object[] { FanTokenTypes.TRY_KEYWORD }))) {
/* 268 */       return false;
/*     */     }
/* 270 */     ParserUtils.removeNls(builder);
/* 271 */     Block.parse(builder, FanElementTypes.TRY_BLOCK);
/* 272 */     ParserUtils.removeNls(builder);
/* 273 */     if (!FanTokenTypes.TRY_BLOCK_TOKENS.contains(builder.getTokenType())) {
/* 274 */       builder.error(FanBundle.message("catch.finally.expected", new Object[0]));
/*     */     } else {
/* 276 */       boolean hasFinally = false;
/* 277 */       while (!builder.eof() && FanTokenTypes.TRY_BLOCK_TOKENS.contains(builder.getTokenType())) {
/* 278 */         PsiBuilder.Marker catchMark = builder.mark();
/* 279 */         if (ParserUtils.getToken(builder, FanTokenTypes.CATCH_KEYWORD)) {
/* 280 */           if (hasFinally) {
/* 281 */             builder.error(FanBundle.message("catch.after.finally", new Object[0]));
/*     */           }
/* 283 */           ParserUtils.removeNls(builder);
/*     */           
/* 285 */           if (ParserUtils.getToken(builder, FanTokenTypes.LPAR)) {
/* 286 */             ParserUtils.removeNls(builder);
/* 287 */             SimpleTypeSpec.parseSimpleType(builder, false);
/* 288 */             ParserUtils.removeNls(builder);
/* 289 */             PsiBuilder.Marker nameMarker = builder.mark();
/* 290 */             if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
/* 291 */               nameMarker.done(FanElementTypes.NAME_ELEMENT);
/*     */             } else {
/* 293 */               nameMarker.drop();
/*     */             } 
/* 295 */             ParserUtils.removeNls(builder);
/* 296 */             ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
/* 297 */             ParserUtils.removeNls(builder);
/*     */           } 
/* 299 */           Block.parse(builder, FanElementTypes.CATCH_BLOCK);
/* 300 */           catchMark.done(FanElementTypes.CATCH_STATEMENT);
/* 301 */         } else if (ParserUtils.getToken(builder, FanTokenTypes.FINALLY_KEYWORD)) {
/* 302 */           hasFinally = true;
/* 303 */           ParserUtils.removeNls(builder);
/* 304 */           Block.parse(builder, FanElementTypes.FINALLY_BLOCK);
/* 305 */           catchMark.done(FanElementTypes.FINALLY_STATEMENT);
/*     */         } else {
/* 307 */           catchMark.drop();
/*     */           break;
/*     */         } 
/* 310 */         ParserUtils.removeNls(builder);
/*     */       } 
/*     */     } 
/* 313 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/Statement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */