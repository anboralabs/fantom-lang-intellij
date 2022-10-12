/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs.members;
/*     */ 
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.navigation.ItemPresentation;
/*     */ import com.intellij.openapi.editor.colors.TextAttributesKey;
/*     */ import com.intellij.psi.PsiClass;
/*     */ import com.intellij.psi.PsiElement;
/*     */ import com.intellij.psi.PsiExpression;
/*     */ import com.intellij.psi.PsiIdentifier;
/*     */ import com.intellij.psi.PsiModifierList;
/*     */ import com.intellij.psi.PsiType;
/*     */ import com.intellij.psi.PsiTypeElement;
/*     */ import com.intellij.psi.javadoc.PsiDocComment;
/*     */ import com.intellij.psi.stubs.IStubElementType;
/*     */ import com.intellij.psi.stubs.StubElement;
/*     */ import com.intellij.util.IncorrectOperationException;
/*     */ import javax.swing.Icon;
/*     */ import org.fandev.icons.Icons;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
/*     */ import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
/*     */ import org.fandev.lang.fan.psi.impl.FanEnumReferenceType;
/*     */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
/*     */ import org.fandev.lang.fan.psi.stubs.FanEnumValueStub;
/*     */ import org.jetbrains.annotations.NonNls;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class FanEnumValueImpl
/*     */   extends FanBaseElementImpl<FanEnumValueStub> implements FanEnumValue {
/*     */   public FanEnumValueImpl(FanEnumValueStub fanEnumValueStub, @NotNull IStubElementType iStubElementType) {
/*  34 */     super(fanEnumValueStub, iStubElementType);
/*     */   }
/*     */   
/*     */   public FanEnumValueImpl(ASTNode astNode) {
/*  38 */     super(astNode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInitializer(@Nullable PsiExpression initializer) throws IncorrectOperationException {}
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PsiType getType() {
/*  47 */     return (PsiType)new FanEnumReferenceType((FanEnumDefinition)getContainingClass());
/*     */   }
/*     */   
/*     */   public PsiType getTypeNoResolve() {
/*  51 */     return getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiTypeElement getTypeElement() {
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextOffset() {
/*  61 */     PsiIdentifier identifier = getNameIdentifier();
/*  62 */     return (identifier == null) ? 0 : identifier.getTextRange().getStartOffset();
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiExpression getInitializer() {
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasInitializer() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void normalizeDeclaration() throws IncorrectOperationException {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object computeConstantValue() {
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  86 */     PsiIdentifier psiId = getNameIdentifier();
/*  87 */     return (psiId == null) ? null : psiId.getText();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiIdentifier getNameIdentifier() {
/*  92 */     return (PsiIdentifier)findChildByType(FanElementTypes.NAME_ELEMENT);
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiClass getContainingClass() {
/*  97 */     PsiElement parent = getParent().getParent();
/*  98 */     if (parent instanceof FanEnumDefinition) {
/*  99 */       return (PsiClass)parent;
/*     */     }
/* 101 */     throw new IllegalStateException("Have an enum value " + getName() + " with no enum: " + this);
/*     */   }
/*     */   
/*     */   public boolean isDeprecated() {
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiModifierList getModifierList() {
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasModifierProperty(String name) {
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
/* 119 */     FanPsiImplUtil.setName((PsiElement)getNameIdentifier(), name);
/* 120 */     return (PsiElement)this;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PsiDocComment getDocComment() {
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIcon(int flags) {
/* 131 */     return Icons.ENUM;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemPresentation getPresentation() {
/* 136 */     return new ItemPresentation() {
/*     */         public String getPresentableText() {
/* 138 */           return FanEnumValueImpl.this.getName();
/*     */         }
/*     */         
/*     */         @Nullable
/*     */         public String getLocationString() {
/* 143 */           PsiClass clazz = FanEnumValueImpl.this.getContainingClass();
/* 144 */           String name = clazz.getQualifiedName();
/* 145 */           assert name != null;
/* 146 */           return "(in " + name + ")";
/*     */         }
/*     */         
/*     */         @Nullable
/*     */         public Icon getIcon(boolean open) {
/* 151 */           return FanEnumValueImpl.this.getIcon(3);
/*     */         }
/*     */         
/*     */         @Nullable
/*     */         public TextAttributesKey getTextAttributesKey() {
/* 156 */           return null;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/members/FanEnumValueImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */