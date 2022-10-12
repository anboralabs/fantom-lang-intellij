/*     */ package org.fandev.lang.fan.parsing.expression.logical;
/*     */ 
/*     */ import com.intellij.lang.PsiBuilder;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.psi.tree.TokenSet;
/*     */ import org.fandev.lang.fan.FanBundle;
/*     */ import org.fandev.lang.fan.parsing.expression.ExpressionParser;
/*     */ import org.fandev.lang.fan.parsing.expression.arithmetic.UnaryExpression;
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
/*     */ public abstract class SeparatorRepeatExpression
/*     */   implements ExpressionParser
/*     */ {
/*     */   protected final IElementType expressionType;
/*     */   protected final TokenSet separators;
/*     */   protected final boolean checkPrefixExpression;
/*     */   
/*     */   protected SeparatorRepeatExpression(IElementType expressionType, TokenSet separators) {
/*  37 */     this.expressionType = expressionType;
/*  38 */     this.separators = separators;
/*     */     
/*  40 */     boolean needToCheckPrefix = false;
/*  41 */     IElementType[] prefixes = UnaryExpression.PREFIXES.getTypes();
/*  42 */     for (IElementType prefix : prefixes) {
/*  43 */       if (separators.contains(prefix)) {
/*  44 */         needToCheckPrefix = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*  48 */     this.checkPrefixExpression = needToCheckPrefix;
/*     */   }
/*     */   
/*     */   protected boolean parseThis(PsiBuilder builder, TokenSet stopper) {
/*  52 */     if (stopper.contains(builder.getTokenType())) {
/*  53 */       return false;
/*     */     }
/*  55 */     PsiBuilder.Marker marker = builder.mark();
/*  56 */     TokenSet newStopper = TokenSet.orSet(new TokenSet[] { stopper, this.separators });
/*  57 */     if (lheParser(builder, newStopper)) {
/*  58 */       if (this.separators.contains(builder.getTokenType())) {
/*  59 */         IElementType separator = builder.getTokenType();
/*  60 */         ParserUtils.advanceNoNls(builder);
/*  61 */         if (!rheParse(builder, newStopper, separator)) {
/*  62 */           marker.error(FanBundle.message("expression.expected", new Object[0]));
/*  63 */           return false;
/*     */         } 
/*  65 */         PsiBuilder.Marker newMarker = marker.precede();
/*  66 */         marker.done(this.expressionType);
/*  67 */         if (this.separators.contains(builder.getTokenType())) {
/*  68 */           subParse(builder, newMarker, newStopper);
/*     */         } else {
/*  70 */           newMarker.drop();
/*     */         } 
/*     */       } else {
/*  73 */         marker.drop();
/*     */       } 
/*  75 */       return true;
/*     */     } 
/*  77 */     marker.drop();
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean lheParser(PsiBuilder builder, TokenSet newStopper) {
/*  83 */     if (this.checkPrefixExpression && 
/*  84 */       this.separators.contains(builder.getTokenType()) && UnaryExpression.parsePrefixExpression(builder, newStopper, this))
/*     */     {
/*  86 */       return true;
/*     */     }
/*     */     
/*  89 */     return innerParse(builder, newStopper);
/*     */   }
/*     */   
/*     */   protected boolean rheParse(PsiBuilder builder, TokenSet newStopper, IElementType separator) {
/*  93 */     if (this.checkPrefixExpression && 
/*  94 */       this.separators.contains(builder.getTokenType()) && UnaryExpression.parsePrefixExpression(builder, newStopper, this))
/*     */     {
/*  96 */       return true;
/*     */     }
/*     */     
/*  99 */     return innerParse(builder, newStopper);
/*     */   }
/*     */   
/*     */   protected void subParse(PsiBuilder builder, PsiBuilder.Marker marker, TokenSet stopper) {
/* 103 */     IElementType separator = builder.getTokenType();
/* 104 */     ParserUtils.advanceNoNls(builder);
/* 105 */     if (!rheParse(builder, stopper, separator)) {
/* 106 */       builder.error(FanBundle.message("expression.expected", new Object[0]));
/* 107 */       marker.drop();
/*     */       return;
/*     */     } 
/* 110 */     PsiBuilder.Marker newMarker = marker.precede();
/* 111 */     marker.done(this.expressionType);
/* 112 */     if (this.separators.contains(builder.getTokenType())) {
/* 113 */       subParse(builder, newMarker, stopper);
/*     */     } else {
/* 115 */       newMarker.drop();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/logical/SeparatorRepeatExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */