/*    */ package org.fandev.lang.fan.psi.impl.statements.params;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.PsiAnnotation;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiIdentifier;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.util.PsiTreeUtil;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
/*    */ import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
/*    */ import org.fandev.lang.fan.psi.impl.statements.FanVariableBaseImpl;
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
/*    */ public class FanFormalImpl
/*    */   extends FanVariableBaseImpl<StubElement>
/*    */   implements FanFormal
/*    */ {
/*    */   public FanFormalImpl(ASTNode astNode) {
/* 29 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiElement getDeclarationScope() {
/* 34 */     FanFuncTypeElement owner = (FanFuncTypeElement)PsiTreeUtil.getParentOfType((PsiElement)this, FanFuncTypeElement.class);
/* 35 */     assert owner != null;
/* 36 */     return (PsiElement)owner;
/*    */   }
/*    */   
/*    */   public boolean isVarArgs() {
/* 40 */     return false;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiAnnotation[] getAnnotations() {
/* 45 */     return PsiAnnotation.EMPTY_ARRAY;
/*    */   }
/*    */   
/*    */   public PsiType getTypeNoResolve() {
/* 49 */     return getType();
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiIdentifier getNameIdentifier() {
/* 54 */     PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
/* 55 */     return FanPsiImplUtil.getFanIdentifier(ident);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 60 */     PsiIdentifier identifier = getNameIdentifier();
/* 61 */     if (identifier != null) {
/* 62 */       return identifier.getText();
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/params/FanFormalImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */