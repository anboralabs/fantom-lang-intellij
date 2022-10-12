/*    */ package org.fandev.lang.fan.psi.impl.statements.blocks;
/*    */ 
/*    */ import com.intellij.extapi.psi.ASTWrapperPsiElement;
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiJavaToken;
/*    */ import com.intellij.psi.PsiStatement;
/*    */ import org.fandev.lang.fan.FanTokenTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class FanPsiCodeBlockImpl
/*    */   extends ASTWrapperPsiElement
/*    */   implements FanPsiCodeBlock
/*    */ {
/*    */   public FanPsiCodeBlockImpl(@NotNull ASTNode node) {
/* 36 */     super(node);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiStatement[] getStatements() {
/* 41 */     return (PsiStatement[])findChildrenByClass(PsiStatement.class);
/*    */   }
/*    */   
/*    */   public PsiElement getFirstBodyElement() {
/* 45 */     return null;
/*    */   }
/*    */   
/*    */   public PsiElement getLastBodyElement() {
/* 49 */     return null;
/*    */   }
/*    */   
/*    */   public PsiJavaToken getLBrace() {
/* 53 */     return null;
/*    */   }
/*    */   
/*    */   public PsiJavaToken getRBrace() {
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   public PsiElement getLeftBrace() {
/* 61 */     return findChildByType(FanTokenTypes.LBRACE);
/*    */   }
/*    */   
/*    */   public PsiElement getRightBrace() {
/* 65 */     return findChildByType(FanTokenTypes.RBRACE);
/*    */   }

    @Override
    public boolean shouldChangeModificationCount(PsiElement place) {
        return false;
    }
    /*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/blocks/FanPsiCodeBlockImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */