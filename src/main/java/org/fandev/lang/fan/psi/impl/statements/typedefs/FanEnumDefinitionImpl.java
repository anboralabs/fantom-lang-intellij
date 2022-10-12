/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs;
/*     */ 
/*     */ import com.intellij.lang.ASTNode;
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
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.swing.Icon;
/*     */ import org.fandev.icons.Icons;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
/*     */ import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*     */ import org.jetbrains.annotations.NonNls;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class FanEnumDefinitionImpl
/*     */   extends FanTypeDefinitionImpl
/*     */   implements FanEnumDefinition
/*     */ {
/*     */   FanEnumValue[] fanEnumValues;
/*     */   
/*     */   public FanEnumDefinitionImpl(FanTypeDefinitionStub stubElement) {
/*  40 */     super(stubElement, (IStubElementType)FanElementTypes.CLASS_DEFINITION);
/*     */   }
/*     */   
/*     */   public FanEnumDefinitionImpl(ASTNode astNode) {
/*  44 */     super(astNode);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  48 */     return "Enum definition";
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
/*  53 */     return (PsiElement)this;
/*     */   }
/*     */   
/*     */   public boolean isInterface() {
/*  57 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isAnnotationType() {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEnum() {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public PsiClass[] getInterfaces() {
/*  69 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void subtreeChanged() {
/*  74 */     this.fanSlots = null;
/*  75 */     this.fanFields = null;
/*  76 */     this.fanMethods = null;
/*  77 */     super.subtreeChanged();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getFields() {
/*  82 */     return (PsiField[])getFanFields();
/*     */   }
/*     */   
/*     */   public FanEnumValue[] getEnumValues() {
/*  86 */     if (this.fanEnumValues == null) {
/*  87 */       PsiElement element = findChildByType(FanElementTypes.ENUM_BODY);
/*  88 */       List<FanEnumValue> list = new ArrayList<FanEnumValue>();
/*  89 */       if (element != null) {
/*  90 */         PsiElement[] bodyEls = element.getChildren();
/*  91 */         for (PsiElement bodyEl : bodyEls) {
/*  92 */           if (bodyEl instanceof FanEnumValue) {
/*  93 */             list.add((FanEnumValue)bodyEl);
/*     */           }
/*     */         } 
/*     */       } 
/*  97 */       this.fanEnumValues = list.<FanEnumValue>toArray(new FanEnumValue[list.size()]);
/*     */     } 
/*  99 */     return this.fanEnumValues;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getMethods() {
/* 104 */     return (PsiMethod[])getFanMethods();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getConstructors() {
/* 109 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getInnerClasses() {
/* 114 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClassInitializer[] getInitializers() {
/* 119 */     return PsiClassInitializer.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getAllFields() {
/* 124 */     return PsiField.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getAllMethods() {
/* 129 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getAllInnerClasses() {
/* 134 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   public PsiField findFieldByName(@NonNls String name, boolean checkBases) {
/* 138 */     return null;
/*     */   }
/*     */   
/*     */   public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 147 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
/* 152 */     return PsiMethod.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String name, boolean checkBases) {
/* 158 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases) {
/* 167 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getLBrace() {
/* 171 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getRBrace() {
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public PsiElement getScope() {
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
/* 183 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
/* 187 */     return false;
/*     */   }
/*     */   
/*     */   public PsiClass getContainingClass() {
/* 191 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
/* 196 */     return null;
/*     */   }
/*     */   
/*     */   public PsiDocComment getDocComment() {
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDeprecated() {
/* 204 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasTypeParameters() {
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public PsiTypeParameterList getTypeParameterList() {
/* 212 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiTypeParameter[] getTypeParameters() {
/* 217 */     return PsiTypeParameter.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Icon getIconInner() {
/* 222 */     return Icons.ENUM;
/*     */   }
/*     */   
/*     */   protected IElementType getBodyElementType() {
/* 226 */     return FanElementTypes.ENUM_BODY;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/FanEnumDefinitionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */