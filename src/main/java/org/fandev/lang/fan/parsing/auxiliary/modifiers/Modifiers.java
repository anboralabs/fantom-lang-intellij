/*    */ package org.fandev.lang.fan.parsing.auxiliary.modifiers;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
/*    */ import org.fandev.lang.fan.parsing.util.ParserUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Modifiers
/*    */ {
/*    */   public static TokenSet parse(PsiBuilder builder, DeclarationType stmtType) {
/* 20 */     TokenSet modifiers = TokenSet.create(new IElementType[0]);
/*    */     
/* 22 */     ParserUtils.removeNls(builder);
/* 23 */     PsiBuilder.Marker modifiersMarker = builder.mark();
/*    */     
/* 25 */     while (!builder.eof()) {
/*    */       
/* 27 */       if (stmtType.getKeyword() != null) {
/* 28 */         if (stmtType.getKeyword().equals(builder.getTokenType())) {
/* 29 */           modifiersMarker.done(FanElementTypes.MODIFIERS);
/* 30 */           return modifiers;
/*    */         }
/*    */       
/* 33 */       } else if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
/* 34 */         modifiersMarker.done(FanElementTypes.MODIFIERS);
/* 35 */         return modifiers;
/*    */       } 
/*    */       
/* 38 */       IElementType possibleModifier = builder.getTokenType();
/* 39 */       if (!Modifier.parse(builder, stmtType)) {
/* 40 */         if (FanTokenTypes.ALL_MODIFIERS.contains(possibleModifier)) {
/*    */           
/* 42 */           String tokenText = builder.getTokenText();
/* 43 */           builder.error(FanBundle.message("illegal.modifier", new Object[] { tokenText, stmtType }));
/* 44 */           builder.advanceLexer(); continue;
/*    */         } 
/* 46 */         modifiersMarker.done(FanElementTypes.MODIFIERS);
/*    */         
/*    */         break;
/*    */       } 
/* 50 */       modifiers = TokenSet.orSet(new TokenSet[] { modifiers, TokenSet.create(new IElementType[] { possibleModifier }) });
/*    */     } 
/*    */     
/* 53 */     return modifiers;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/auxiliary/modifiers/Modifiers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */