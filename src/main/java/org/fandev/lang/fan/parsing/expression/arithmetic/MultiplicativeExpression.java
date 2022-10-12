/*    */ package org.fandev.lang.fan.parsing.expression.arithmetic;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.expression.logical.SeparatorRepeatExpression;
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
/*    */ public class MultiplicativeExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 28 */   private static final MultiplicativeExpression instance = new MultiplicativeExpression();
/*    */   
/*    */   public MultiplicativeExpression() {
/* 31 */     super(FanElementTypes.MULT_EXPR, TokenSet.create(new IElementType[] { FanTokenTypes.MULT, FanTokenTypes.DIV, FanTokenTypes.PERC }));
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 35 */     return ParenExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 39 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/MultiplicativeExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */