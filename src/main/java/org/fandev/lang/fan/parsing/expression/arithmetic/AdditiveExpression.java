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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdditiveExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 31 */   private static final AdditiveExpression instance = new AdditiveExpression();
/*    */   
/*    */   public AdditiveExpression() {
/* 34 */     super(FanElementTypes.ADD_EXPR, TokenSet.create(new IElementType[] { FanTokenTypes.PLUS, FanTokenTypes.MINUS }));
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 38 */     return MultiplicativeExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 42 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/AdditiveExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */