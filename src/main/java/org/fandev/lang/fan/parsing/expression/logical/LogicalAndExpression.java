/*    */ package org.fandev.lang.fan.parsing.expression.logical;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
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
/*    */ public class LogicalAndExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 27 */   private static final LogicalAndExpression instance = new LogicalAndExpression();
/*    */   
/*    */   public LogicalAndExpression() {
/* 30 */     super(FanElementTypes.LOGICAL_AND_EXPR, TokenSet.create(new IElementType[] { FanTokenTypes.ANDAND }));
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 34 */     return EqualityExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 38 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/logical/LogicalAndExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */