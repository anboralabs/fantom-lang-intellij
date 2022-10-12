/*    */ package org.fandev.lang.fan.psi.impl.synthetic;
/*    */ 
/*    */ import com.intellij.openapi.util.TextRange;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiFile;
/*    */ import com.intellij.psi.PsiManager;
/*    */ import com.intellij.psi.impl.light.LightIdentifier;
/*    */ import com.intellij.util.IncorrectOperationException;
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
/*    */ public class FanLightIdentifier
/*    */   extends LightIdentifier
/*    */ {
/*    */   private PsiFile myFile;
/*    */   private TextRange myRange;
/*    */   
/*    */   public FanLightIdentifier(PsiManager manager, PsiFile file, TextRange range) {
/* 37 */     super(manager, file.getText().substring(range.getStartOffset(), range.getEndOffset()));
/* 38 */     this.myFile = file;
/* 39 */     this.myRange = range;
/*    */   }
/*    */   
/*    */   public FanLightIdentifier(PsiManager manager, PsiFile file, String text) {
/* 43 */     super(manager, text);
/* 44 */     this.myFile = file;
/* 45 */     this.myRange = null;
/*    */   }
/*    */   
/*    */   public TextRange getTextRange() {
/* 49 */     return this.myRange;
/*    */   }
/*    */   
/*    */   public PsiFile getContainingFile() {
/* 53 */     return this.myFile;
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiElement replace(@NotNull PsiElement newElement) throws IncorrectOperationException {
/* 58 */     return super.replace(newElement);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/synthetic/FanLightIdentifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */