/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs;
/*     */ 
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.openapi.diagnostic.Logger;
/*     */ import com.intellij.openapi.util.Pair;
/*     */ import com.intellij.psi.HierarchicalMethodSignature;
/*     */ import com.intellij.psi.PsiClass;
/*     */ import com.intellij.psi.PsiClassInitializer;
/*     */ import com.intellij.psi.PsiElement;
/*     */ import com.intellij.psi.PsiField;
/*     */ import com.intellij.psi.PsiJavaToken;
/*     */ import com.intellij.psi.PsiMethod;
/*     */ import com.intellij.psi.PsiSubstitutor;
/*     */ import com.intellij.psi.PsiTypeParameter;
/*     */ import com.intellij.psi.PsiTypeParameterList;
/*     */ import com.intellij.psi.javadoc.PsiDocComment;
/*     */ import com.intellij.psi.stubs.IStubElementType;
/*     */ import com.intellij.psi.tree.IElementType;
/*     */ import com.intellij.util.IncorrectOperationException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ import org.fandev.icons.Icons;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanMixinDefinition;
/*     */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*     */ import org.jetbrains.annotations.NonNls;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FanMixinDefinitionImpl
/*     */   extends FanTypeDefinitionImpl
/*     */   implements FanMixinDefinition
/*     */ {
/*  38 */   private static final Logger logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.statements.typedefs.FanMixinDefinitionImpl");
/*     */   
/*     */   public FanMixinDefinitionImpl(FanTypeDefinitionStub stubElement) {
/*  41 */     super(stubElement, (IStubElementType)FanElementTypes.CLASS_DEFINITION);
/*     */   }
/*     */   
/*     */   public FanMixinDefinitionImpl(ASTNode astNode) {
/*  45 */     super(astNode);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  49 */     return "Mixin definition";
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
/*  54 */     return (PsiElement)this;
/*     */   }
/*     */   
/*     */   public boolean isInterface() {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isAnnotationType() {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEnum() {
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public PsiClass[] getInterfaces() {
/*  70 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void subtreeChanged() {
/*  75 */     this.fanSlots = null;
/*  76 */     this.fanFields = null;
/*  77 */     this.fanMethods = null;
/*  78 */     super.subtreeChanged();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getFields() {
/*  83 */     return (PsiField[])getFanFields();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getMethods() {
/*  88 */     return (PsiMethod[])getFanMethods();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getConstructors() {
/*  94 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getInnerClasses() {
/*  99 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClassInitializer[] getInitializers() {
/* 104 */     return PsiClassInitializer.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getAllFields() {
/* 109 */     return PsiField.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getAllMethods() {
/* 114 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getAllInnerClasses() {
/* 119 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   public PsiField findFieldByName(@NonNls String name, boolean checkBases) {
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 132 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
/* 137 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String name, boolean checkBases) {
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases) {
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getLBrace() {
/* 156 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getRBrace() {
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public PsiElement getScope() {
/* 164 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   public PsiClass getContainingClass() {
/* 176 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   public PsiDocComment getDocComment() {
/* 185 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDeprecated() {
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasTypeParameters() {
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   public PsiTypeParameterList getTypeParameterList() {
/* 197 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiTypeParameter[] getTypeParameters() {
/* 202 */     return PsiTypeParameter.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Icon getIconInner() {
/* 207 */     return Icons.MIXIN;
/*     */   }
/*     */   
/*     */   protected IElementType getBodyElementType() {
/* 211 */     return FanElementTypes.MIXIN_BODY;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/FanMixinDefinitionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */