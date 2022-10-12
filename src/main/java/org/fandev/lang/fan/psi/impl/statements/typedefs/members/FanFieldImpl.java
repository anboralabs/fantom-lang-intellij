/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs.members;
/*     */ 
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.psi.Bottom;
/*     */ import com.intellij.psi.PsiElement;
/*     */ import com.intellij.psi.PsiExpression;
/*     */ import com.intellij.psi.PsiType;
/*     */ import com.intellij.psi.PsiTypeElement;
/*     */ import com.intellij.psi.StubBasedPsiElement;
/*     */ import com.intellij.psi.stubs.IStubElementType;
/*     */ import com.intellij.util.IncorrectOperationException;
/*     */ import javax.swing.Icon;
/*     */ import org.fandev.icons.Icons;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
/*     */ import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*     */ import org.fandev.lang.fan.psi.stubs.FanFieldStub;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FanFieldImpl
/*     */   extends FanSlotElementImpl<FanFieldStub>
/*     */   implements FanField, StubBasedPsiElement<FanFieldStub>
/*     */ {
/*     */   public FanFieldImpl(FanFieldStub fanFieldStub, @NotNull IStubElementType iStubElementType) {
/*  49 */     super(fanFieldStub, iStubElementType);
/*     */   }
/*     */   
/*     */   public FanFieldImpl(ASTNode astNode) {
/*  53 */     super(astNode);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIconInner() {
/*  58 */     return Icons.FIELD;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitializer(@Nullable PsiExpression initializer) throws IncorrectOperationException {}
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PsiType getType() {
/*  68 */     FanTypeElement classTypeElement = findTypeElement();
/*  69 */     if (classTypeElement != null) {
/*  70 */       return classTypeElement.getType();
/*     */     }
/*  72 */     return (PsiType)Bottom.BOTTOM;
/*     */   }
/*     */   
/*     */   public PsiType getTypeNoResolve() {
/*  76 */     return getType();
/*     */   }
/*     */   
/*     */   protected FanTypeElement findTypeElement() {
/*  80 */     FanTypeElement classTypeElement = (FanTypeElement)findChildByType(FanElementTypes.CLASS_TYPE_ELEMENT);
/*  81 */     if (classTypeElement == null) {
/*  82 */       classTypeElement = (FanTypeElement)findChildByType(FanElementTypes.LIST_TYPE);
/*  83 */       if (classTypeElement == null) {
/*  84 */         classTypeElement = (FanTypeElement)findChildByType(FanElementTypes.MAP_TYPE);
/*  85 */         if (classTypeElement == null) {
/*  86 */           classTypeElement = (FanTypeElement)findChildByType(FanElementTypes.FUNC_TYPE);
/*     */         }
/*     */       } 
/*     */     } 
/*  90 */     return classTypeElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiTypeElement getTypeElement() {
/*  95 */     PsiElement typeEl = findChildByType(FanElementTypes.TYPE);
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public PsiExpression getInitializer() {
/* 100 */     PsiElement initEl = findChildByType(FanElementTypes.FIELD_DEFAULT);
/* 101 */     return null;
/*     */   }
/*     */   
/*     */   public boolean hasInitializer() {
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void normalizeDeclaration() throws IncorrectOperationException {}
/*     */   
/*     */   public Object computeConstantValue() {
/* 112 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/members/FanFieldImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */