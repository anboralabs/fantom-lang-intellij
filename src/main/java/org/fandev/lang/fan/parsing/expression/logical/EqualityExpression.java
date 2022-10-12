/*    */ package org.fandev.lang.fan.parsing.expression.logical;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
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
/*    */ public class EqualityExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 27 */   private static final EqualityExpression instance = new EqualityExpression();
/*    */   
/*    */   public EqualityExpression() {
/* 30 */     super(FanElementTypes.EQUALITY_EXPR, FanTokenTypes.EQUALITY_OP);
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 34 */     return RelationalExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 38 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/logical/EqualityExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */