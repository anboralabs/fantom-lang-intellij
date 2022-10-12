/*    */ package org.fandev.lang.fan.psi.impl.statements.arguments;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.util.PsiTreeUtil;
/*    */ import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
/*    */ import org.fandev.lang.fan.psi.api.statements.arguments.FanArgumentList;
/*    */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanArgumentImpl
/*    */   extends FanBaseElementImpl
/*    */   implements FanArgument
/*    */ {
/*    */   public FanArgumentImpl(ASTNode astNode) {
/* 18 */     super(astNode);
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 22 */     return getArgumentList().indexOf(this);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public FanArgumentList getArgumentList() {
/* 27 */     return (FanArgumentList)PsiTreeUtil.getParentOfType((PsiElement)this, FanArgumentList.class);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/arguments/FanArgumentImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */