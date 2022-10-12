/*    */ package org.fandev.lang.fan.parsing.expression.logical;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.arithmetic.TermExpression;
/*    */ import org.fandev.lang.fan.parsing.expression.arithmetic.UnaryExpression;
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
/*    */ public class BitOrExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 33 */   private static final BitOrExpression instance = new BitOrExpression();
/*    */   
/*    */   public BitOrExpression() {
/* 36 */     super(FanElementTypes.BIT_OR_EXPR, TokenSet.create(new IElementType[] { FanTokenTypes.XOR, FanTokenTypes.OR }));
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 40 */     if (FanTokenTypes.XOR == builder.getTokenType())
/*    */     {
/* 42 */       return UnaryExpression.parse(builder, stopper);
/*    */     }
/* 44 */     if (FanTokenTypes.OR == builder.getTokenType())
/*    */     {
/* 46 */       return TermExpression.parse(builder, stopper);
/*    */     }
/* 48 */     return BitAndExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 52 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/logical/BitOrExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */