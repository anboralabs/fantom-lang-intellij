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
/*    */ public class ShiftExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 29 */   private static final ShiftExpression instance = new ShiftExpression();
/*    */   
/*    */   public ShiftExpression() {
/* 32 */     super(FanElementTypes.SHIFT_EXPR, TokenSet.create(new IElementType[] { FanTokenTypes.GTGT, FanTokenTypes.LTLT }));
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 36 */     return AdditiveExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 40 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/arithmetic/ShiftExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */