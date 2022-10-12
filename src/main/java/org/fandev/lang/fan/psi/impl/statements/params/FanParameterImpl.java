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
/*    */ import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
/*    */ import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
/*    */ import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanParameterImpl
/*    */   extends FanVariableBaseImpl<StubElement>
/*    */   implements FanParameter
/*    */ {
/*    */   public FanParameterImpl(ASTNode astNode) {
/* 33 */     super(astNode);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public PsiElement getDeclarationScope() {
/* 38 */     FanParameterOwner owner = (FanParameterOwner)PsiTreeUtil.getParentOfType((PsiElement)this, FanParameterOwner.class);
/* 39 */     assert owner != null;
/* 40 */     return (PsiElement)owner;
/*    */   }
/*    */   
/*    */   public boolean isVarArgs() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PsiAnnotation[] getAnnotations() {
/* 50 */     return new PsiAnnotation[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "Parameter";
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiIdentifier getNameIdentifier() {
/* 60 */     PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
/* 61 */     assert ident != null;
/* 62 */     return FanPsiImplUtil.getFanIdentifier(ident);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 67 */     return getNameIdentifier().getText();
/*    */   }
/*    */   
/*    */   public PsiType getTypeNoResolve() {
/* 71 */     return getType();
/*    */   }
/*    */   
/*    */   public FanDefaultValue getDefaultValue() {
/* 75 */     return (FanDefaultValue)findChildByClass(FanDefaultValue.class);
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/params/FanParameterImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */