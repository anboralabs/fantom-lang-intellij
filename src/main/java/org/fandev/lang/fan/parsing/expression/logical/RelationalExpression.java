/*    */ package org.fandev.lang.fan.parsing.expression.logical;
/*    */ 
/*    */ import com.intellij.lang.PsiBuilder;
/*    */ import com.intellij.psi.tree.IElementType;
/*    */ import com.intellij.psi.tree.TokenSet;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.parsing.types.TypeSpec;
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
/*    */ public class RelationalExpression
/*    */   extends SeparatorRepeatExpression
/*    */ {
/* 30 */   private static final RelationalExpression instance = new RelationalExpression();
/*    */   
/*    */   public RelationalExpression() {
/* 33 */     super(FanElementTypes.RELATIONAL_EXPR, FanTokenTypes.RELATIONAL_OP);
/*    */   }
/*    */   
/*    */   public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
/* 37 */     return ElvisExpression.parse(builder, stopper);
/*    */   }
/*    */   
/*    */   public static boolean parse(PsiBuilder builder, TokenSet stopper) {
/* 41 */     return instance.parseThis(builder, stopper);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean rheParse(PsiBuilder builder, TokenSet newStopper, IElementType separator) {
/* 47 */     if (FanTokenTypes.TYPE_COMPARE.contains(separator)) {
/* 48 */       return TypeSpec.parse(builder);
/*    */     }
/* 50 */     return super.rheParse(builder, newStopper, separator);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/logical/RelationalExpression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */