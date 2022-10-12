/*     */ package org.fandev.lang.fan.psi.impl.statements;
/*     */ 
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.psi.Bottom;
/*     */ import com.intellij.psi.PsiElement;
/*     */ import com.intellij.psi.PsiExpression;
/*     */ import com.intellij.psi.PsiIdentifier;
/*     */ import com.intellij.psi.PsiModifierList;
/*     */ import com.intellij.psi.PsiType;
/*     */ import com.intellij.psi.PsiTypeElement;
/*     */ import com.intellij.psi.stubs.IStubElementType;
/*     */ import com.intellij.psi.stubs.StubElement;
/*     */ import com.intellij.util.IncorrectOperationException;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.FanVariable;
/*     */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*     */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*     */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
/*     */ import org.jetbrains.annotations.NonNls;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FanVariableBaseImpl<T extends StubElement>
/*     */   extends FanBaseElementImpl<T>
/*     */   implements FanVariable
/*     */ {
/*     */   public FanVariableBaseImpl(T t, @NotNull IStubElementType iStubElementType) {
/*  33 */     super(t, iStubElementType);
/*     */   }
/*     */   
/*     */   public FanVariableBaseImpl(ASTNode astNode) {
/*  37 */     super(astNode);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiType getType() {
/*  42 */     PsiType type = getDeclaredType();
/*  43 */     return (type != null) ? type : (PsiType)Bottom.BOTTOM;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public FanTypeElement getTypeElementFan() {
/*  48 */     return (FanTypeElement)findChildByClass(FanTypeElement.class);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiType getDeclaredType() {
/*  53 */     FanTypeElement typeElement = getTypeElementFan();
/*  54 */     if (typeElement != null) return typeElement.getType();
/*     */     
/*  56 */     return null;
/*     */   }
/*     */   
/*     */   public PsiIdentifier getNameIdentifier() {
/*  60 */     PsiElement ident = findChildByType(FanElementTypes.ID_EXPR);
/*  61 */     assert ident != null;
/*     */     
/*  63 */     return FanPsiImplUtil.getFanIdentifier(ident);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  68 */     return getNameIdentifier().getText();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PsiModifierList getModifierList() {
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasModifierProperty(String property) {
/*  78 */     PsiModifierList modifierList = getModifierList();
/*  79 */     return (modifierList != null && modifierList.hasModifierProperty(property));
/*     */   }
/*     */   
/*     */   public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
/*  83 */     FanPsiImplUtil.setName((PsiElement)getNameIdentifier(), name);
/*  84 */     return (PsiElement)this;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiTypeElement getTypeElement() {
/*  89 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiExpression getInitializer() {
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasInitializer() {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Object computeConstantValue() {
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public void normalizeDeclaration() throws IncorrectOperationException {}
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/FanVariableBaseImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */