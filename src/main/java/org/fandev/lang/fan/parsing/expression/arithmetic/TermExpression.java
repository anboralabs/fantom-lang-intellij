/*     */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.FanTokenTypes;
/*     */ import org.fandev.lang.fan.parsing.expression.Expression;
/*     */ import org.fandev.lang.fan.parsing.expression.argument.LiteralExpression;
/*     */ import org.fandev.lang.fan.parsing.statements.Block;
/*     */ import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
/*     */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.FieldDefinition;
/*     */ import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.PropertyBlock;
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
/*     */ public class TermExpression
/*     */ {
/*  38 */   private static final TokenSet DOTS = TokenSet.create(new IElementType[] { FanTokenTypes.DOT, FanTokenTypes.DYN_CALL, FanTokenTypes.SAFE_DOT, FanTokenTypes.SAFE_DYN_CALL });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/*  46 */     PsiBuilder.Marker marker = builder.mark();
/*  47 */     TokenSet newStopper = TokenSet.orSet(new TokenSet[] { stopper, DOTS });
/*  48 */     boolean res = parseBase(builder, newStopper);
/*  49 */     if (res && (hasDot(builder) || !stopper.contains(builder.getTokenType()))) {
/*  50 */       res = parseTermChainLoop(builder, stopper);
/*     */     }
/*  52 */     if (res)
/*     */     {
/*  54 */       if (parseWithBlock(builder) == null) {
/*  55 */         res = false;
/*     */       }
/*     */     }
/*  58 */     marker.done(FanElementTypes.TERM_EXPR);
/*  59 */     return res;
/*     */   }
/*     */   
/*     */   public static boolean parseTermChainLoop(PsiBuilder builder, TokenSet stopper) {
/*  63 */     boolean res = true;
/*  64 */     if (!stopper.contains(FanTokenTypes.NLS)) {
/*  65 */       ParserUtils.removeNls(builder);
/*     */     }
/*  67 */     while (res && !builder.eof() && (hasDot(builder) || !stopper.contains(builder.getTokenType()))) {
/*  68 */       res = parseTermChain(builder, stopper);
/*  69 */       if (!stopper.contains(FanTokenTypes.NLS))
/*     */       {
/*  71 */         ParserUtils.removeNls(builder);
/*     */       }
/*     */     } 
/*  74 */     return res;
/*     */   }
/*     */   
/*     */   public static boolean parseBase(PsiBuilder builder, TokenSet stopper) {
/*  78 */     if (LiteralExpression.parse(builder, stopper)) {
/*  79 */       return true;
/*     */     }
/*  81 */     if (IdExpression.parse(builder)) {
/*  82 */       return true;
/*     */     }
/*  84 */     return ClosureExpression.parse(builder);
/*     */   }
/*     */   
/*     */   private static boolean parseTermChain(PsiBuilder builder, TokenSet stopper) {
/*  88 */     if (hasDot(builder)) {
/*     */       
/*  90 */       ParserUtils.removeNls(builder);
/*  91 */       ParserUtils.advanceNoNls(builder);
/*     */       
/*  93 */       if (FanTokenTypes.SUPER_KEYWORD == builder.getTokenType()) {
/*  94 */         builder.advanceLexer();
/*     */       } else {
/*  96 */         IdExpression.parse(builder);
/*     */       } 
/*  98 */     } else if (FanTokenTypes.LBRACKET.equals(builder.getTokenType())) {
/*     */       
/* 100 */       ParserUtils.advanceNoNls(builder);
/* 101 */       boolean res = true;
/* 102 */       while (res && !builder.eof() && FanTokenTypes.RBRACKET != builder.getTokenType()) {
/* 103 */         res = Expression.parseExpr(builder, TokenSet.create(new IElementType[] { FanTokenTypes.RBRACKET } ), FanElementTypes.INDEX_EXPR);
/* 104 */         ParserUtils.removeNls(builder);
/*     */       } 
/* 106 */       ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
/* 107 */     } else if (FanTokenTypes.LPAR.equals(builder.getTokenType())) {
/*     */       
/* 109 */       ParserUtils.removeNls(builder);
/* 110 */       Arguments.parse(builder);
/* 111 */       ParserUtils.removeNls(builder);
/*     */       
/* 113 */       ClosureExpression.parse(builder);
/*     */     } else {
/* 115 */       return (parseWithBlock(builder) == FanElementTypes.WITH_BLOCK_EXPR);
/*     */     } 
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean hasDot(PsiBuilder builder) {
/* 122 */     return DOTS.contains(ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }));
/*     */   }
/*     */   
/*     */   private static IElementType parseWithBlock(PsiBuilder builder) {
/* 126 */     if (ParserUtils.firstAfter(builder, new IElementType[] { FanTokenTypes.NLS }) == FanTokenTypes.LBRACE) {
/* 127 */       if (builder.getUserData(FieldDefinition.FIELD_NAME) != null) {
/*     */         
/* 129 */         PropertyBlock block = FieldDefinition.findPropertyBlockType(builder);
/* 130 */         if (block != PropertyBlock.NONE)
/*     */         {
/* 132 */           return FanElementTypes.WRONGWAY;
/*     */         }
/*     */       } 
/* 135 */       ParserUtils.removeNls(builder);
/* 136 */       if (Block.parse(builder, FanElementTypes.WITH_BLOCK_EXPR)) {
/* 137 */         return FanElementTypes.WITH_BLOCK_EXPR;
/*     */       }
/* 139 */       return null;
/*     */     } 
/*     */     
/* 142 */     return FanElementTypes.WRONGWAY;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/TermExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */