/*    */ package org.fandev.lang.fan.psi.impl.statements;
/*    */ 
/*    */ import com.intellij.lang.ASTNode;
/*    */ import com.intellij.psi.Bottom;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiIdentifier;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.stubs.IStubElementType;
/*    */ import com.intellij.psi.stubs.StubElement;
/*    */ import org.fandev.lang.fan.FanElementTypes;
/*    */ import org.fandev.lang.fan.psi.api.statements.FanVariable;
/*    */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*    */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FanVariableImpl
/*    */   extends FanVariableBaseImpl<StubElement>
/*    */   implements FanVariable
/*    */ {
/*    */   public FanVariableImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
/* 28 */     super(stubElement, iStubElementType);
/*    */   }
/*    */   
/*    */   public FanVariableImpl(ASTNode astNode) {
/* 32 */     super(astNode);
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiIdentifier getNameIdentifier() {
/* 37 */     PsiElement ident = findChildByType(FanElementTypes.NAME_ELEMENT);
/* 38 */     assert ident != null;
/* 39 */     return FanPsiImplUtil.getFanIdentifier(ident);
/*    */   }
/*    */ 
/*    */   
/*    */   public PsiType getTypeNoResolve() {
/* 44 */     return (PsiType)Bottom.BOTTOM;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 49 */     return getNameIdentifier().getText();
/*    */   }
/*    */ 
/*    */   
/*    */   public FanTypeElement getTypeElementFan() {
/* 54 */     FanTypeElement type = super.getTypeElementFan();
/* 55 */     if (type == null);
/*    */ 
/*    */     
/* 58 */     return type;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/FanVariableImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */