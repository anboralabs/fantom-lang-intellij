/*    */ package org.fandev.lang.fan.parsing.statements;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import org.fandev.lang.fan.FanBundle;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
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
/*    */ public class Block
/*    */ {
/*    */   public static boolean parse(PsiBuilder builder, IElementType statementType) {
/* 36 */     PsiBuilder.Marker m = builder.mark();
/* 37 */     if (ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
/* 38 */       ParserUtils.removeNls(builder);
/* 39 */       while (!builder.eof() && !FanTokenTypes.RBRACE.equals(builder.getTokenType())) {
/* 40 */         if (!Statement.parse(builder, (statementType == FanElementTypes.WITH_BLOCK_EXPR))) {
/*    */           
/* 42 */           builder.error(FanBundle.message("rcurly.expected", new Object[0]));
/* 43 */           ParserUtils.advanceNoNls(builder); continue;
/*    */         } 
/* 45 */         ParserUtils.removeNls(builder);
/*    */       } 
/*    */       
/* 48 */       ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]));
/* 49 */       m.done(statementType);
/* 50 */       return true;
/*    */     } 
/*    */     
/* 53 */     if (Statement.parse(builder)) {
/* 54 */       m.done(statementType);
/* 55 */       return true;
/*    */     } 
/* 57 */     m.drop();
/* 58 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/statements/Block.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */