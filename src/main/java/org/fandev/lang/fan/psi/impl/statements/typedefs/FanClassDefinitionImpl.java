/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs;
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.openapi.diagnostic.Logger;
/*     */ import com.intellij.openapi.util.Pair;
/*     */ import com.intellij.psi.*;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import com.intellij.psi.impl.InheritanceImplUtil;
/*     */ import com.intellij.psi.infos.CandidateInfo;
/*     */ import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.MethodSignature;
/*     */ import com.intellij.psi.util.TypeConversionUtil;
/*     */ import java.util.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import com.intellij.util.IncorrectOperationException;
import org.fandev.icons.Icons;
/*     */ import org.fandev.lang.fan.FanElementTypes;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
/*     */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
/*     */ import org.fandev.lang.fan.resolve.CollectClassMembersUtil;
/*     */ import org.jetbrains.annotations.NonNls;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/*     */
/*     */ public class FanClassDefinitionImpl extends FanTypeDefinitionImpl implements FanClassDefinition {
/*  35 */   private static final Logger logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.statements.typedefs.FanClassDefinitionImpl");
/*     */   
/*     */   public FanClassDefinitionImpl(FanTypeDefinitionStub stubElement) {
/*  38 */     super(stubElement, (IStubElementType)FanElementTypes.CLASS_DEFINITION);
/*     */   }
/*     */   
/*     */   public FanClassDefinitionImpl(ASTNode astNode) {
/*  42 */     super(astNode);
/*     */   }
/*     */   
/*     */   protected Icon getIconInner() {
/*  46 */     if (hasModifierProperty("abstract")) {
/*  47 */       return Icons.ABSTRACT_CLASS;
/*     */     }
/*  49 */     return Icons.CLASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  54 */     return "Class definition";
/*     */   }
/*     */ 
/*     */   
/*     */   public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
/*  59 */     return (PsiElement)this;
/*     */   }
/*     */   
/*     */   public boolean isInterface() {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isAnnotationType() {
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEnum() {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public PsiClass[] getInterfaces() {
/*  75 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void subtreeChanged() {
/*  80 */     this.fanSlots = null;
/*  81 */     this.fanFields = null;
/*  82 */     this.fanMethods = null;
/*  83 */     super.subtreeChanged();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getFields() {
/*  88 */     return (PsiField[])getFanFields();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getMethods() {
/*  93 */     return (PsiMethod[])getFanMethods();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getConstructors() {
/*  98 */     Set<PsiMethod> constructors = new HashSet<PsiMethod>();
/*  99 */     for (FanMethod method : getFanMethods()) {
/* 100 */       if (method instanceof org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor) constructors.add(method); 
/*     */     } 
/* 102 */     return constructors.<PsiMethod>toArray(new PsiMethod[constructors.size()]);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getInnerClasses() {
/* 107 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClassInitializer[] getInitializers() {
/* 112 */     return PsiClassInitializer.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiField[] getAllFields() {
/* 117 */     return PsiField.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] getAllMethods() {
/* 122 */     List<PsiMethod> allMethods = new ArrayList<PsiMethod>();
/* 123 */     getAllMethodsInner((PsiClass)this, allMethods, new HashSet<PsiClass>());
/*     */     
/* 125 */     return allMethods.<PsiMethod>toArray(new PsiMethod[allMethods.size()]);
/*     */   }
/*     */   
/*     */   private static void getAllMethodsInner(PsiClass clazz, List<PsiMethod> allMethods, HashSet<PsiClass> visited) {
/* 129 */     if (visited.contains(clazz))
/* 130 */       return;  visited.add(clazz);
/*     */     
/* 132 */     allMethods.addAll(Arrays.asList(clazz.getMethods()));
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
/* 147 */     PsiClass[] supers = clazz.getSupers();
/* 148 */     for (PsiClass aSuper : supers) {
/* 149 */       getAllMethodsInner(aSuper, allMethods, visited);
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiClass[] getAllInnerClasses() {
/* 155 */     return PsiClass.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiField findFieldByName(String name, boolean checkBases) {
/* 160 */     if (!checkBases) {
/* 161 */       for (FanField field : getFanFields()) {
/* 162 */         if (name.equals(field.getName())) return (PsiField)field;
/*     */       
/*     */       } 
/* 165 */       return null;
/*     */     } 
/*     */     
/* 168 */     Map<String, CandidateInfo> fieldsMap = CollectClassMembersUtil.getAllFields((PsiClass)this);
/* 169 */     CandidateInfo info = fieldsMap.get(name);
/* 170 */     return (info == null) ? null : (PsiField)info.getElement();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 175 */     MethodSignature patternSignature = patternMethod.getSignature(PsiSubstitutor.EMPTY);
/* 176 */     for (PsiMethod method : findMethodsByName(patternMethod.getName(), checkBases, false)) {
/* 177 */       PsiClass clazz = method.getContainingClass();
/* 178 */       PsiSubstitutor superSubstitutor = TypeConversionUtil.getClassSubstitutor(clazz, (PsiClass)this, PsiSubstitutor.EMPTY);
/* 179 */       assert superSubstitutor != null;
/* 180 */       MethodSignature signature = method.getSignature(superSubstitutor);
/* 181 */       if (signature.equals(patternSignature)) return method;
/*     */     
/*     */     } 
/* 184 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
/* 189 */     return findMethodsBySignature(patternMethod, checkBases, true);
/*     */   }
/*     */   
/*     */   private PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases, boolean includeSynthetic) {
/* 193 */     ArrayList<PsiMethod> result = new ArrayList<PsiMethod>();
/* 194 */     MethodSignature patternSignature = patternMethod.getSignature(PsiSubstitutor.EMPTY);
/* 195 */     for (PsiMethod method : findMethodsByName(patternMethod.getName(), checkBases, includeSynthetic)) {
/* 196 */       PsiClass clazz = method.getContainingClass();
/* 197 */       if (clazz != null) {
/* 198 */         PsiSubstitutor superSubstitutor = TypeConversionUtil.getClassSubstitutor(clazz, (PsiClass)this, PsiSubstitutor.EMPTY);
/* 199 */         assert superSubstitutor != null;
/* 200 */         MethodSignature signature = method.getSignature(superSubstitutor);
/* 201 */         if (signature.equals(patternSignature))
/*     */         {
/* 203 */           result.add(method); } 
/*     */       } 
/*     */     } 
/* 206 */     return result.<PsiMethod>toArray(new PsiMethod[result.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
/* 212 */     return findMethodsByName(name, checkBases, true);
/*     */   }
/*     */   
/*     */   private PsiMethod[] findMethodsByName(String name, boolean checkBases, boolean includeSyntheticAccessors) {
/* 216 */     if (!checkBases) {
/* 217 */       List<PsiMethod> result = new ArrayList<PsiMethod>();
/* 218 */       for (PsiMethod method : includeSyntheticAccessors ? getMethods() : (PsiMethod[])getFanMethods()) {
/* 219 */         if (name.equals(method.getName())) result.add(method);
/*     */       
/*     */       } 
/* 222 */       return result.<PsiMethod>toArray(new PsiMethod[result.size()]);
/*     */     } 
/*     */     
/* 225 */     Map<String, List<CandidateInfo>> methodsMap = CollectClassMembersUtil.getAllMethods((PsiClass)this, includeSyntheticAccessors);
/* 226 */     return FanPsiImplUtil.mapToMethods(methodsMap.get(name));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(String name, boolean checkBases) {
/* 231 */     ArrayList<Pair<PsiMethod, PsiSubstitutor>> result = new ArrayList<Pair<PsiMethod, PsiSubstitutor>>();
/*     */     
/* 233 */     if (!checkBases) {
/* 234 */       PsiMethod[] methods = findMethodsByName(name, false);
/* 235 */       for (PsiMethod method : methods) {
/* 236 */         result.add(new Pair(method, PsiSubstitutor.EMPTY));
/*     */       }
/*     */     } else {
/* 239 */       Map<String, List<CandidateInfo>> map = CollectClassMembersUtil.getAllMethods((PsiClass)this, true);
/* 240 */       List<CandidateInfo> candidateInfos = map.get(name);
/* 241 */       if (candidateInfos != null) {
/* 242 */         for (CandidateInfo info : candidateInfos) {
/* 243 */           PsiElement element = info.getElement();
/* 244 */           result.add(new Pair(element, info.getSubstitutor()));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
/* 254 */     Map<String, List<CandidateInfo>> allMethodsMap = CollectClassMembersUtil.getAllMethods((PsiClass)this, true);
/* 255 */     List<Pair<PsiMethod, PsiSubstitutor>> result = new ArrayList<Pair<PsiMethod, PsiSubstitutor>>();
/* 256 */     for (List<CandidateInfo> infos : allMethodsMap.values()) {
/* 257 */       for (CandidateInfo info : infos) {
/* 258 */         result.add(new Pair(info.getElement(), info.getSubstitutor()));
/*     */       }
/*     */     } 
/*     */     
/* 262 */     return result;
/*     */   }
/*     */   
/*     */   public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases) {
/* 266 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getLBrace() {
/* 270 */     return null;
/*     */   }
/*     */   
/*     */   public PsiJavaToken getRBrace() {
/* 274 */     return null;
/*     */   }
/*     */   
/*     */   public PsiElement getScope() {
/* 278 */     return getParent();
/*     */   }
/*     */   
/*     */   public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
/* 282 */     return InheritanceImplUtil.isInheritor((PsiClass)this, baseClass, checkDeep);
/*     */   }
/*     */   
/*     */   public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
/* 286 */     return InheritanceImplUtil.isInheritorDeep((PsiClass)this, baseClass, classToByPass);
/*     */   }
/*     */   
/*     */   public PsiClass getContainingClass() {
/* 290 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
/* 295 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public PsiDocComment getDocComment() {
/* 299 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDeprecated() {
/* 303 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasTypeParameters() {
/* 307 */     return ((getTypeParameters()).length > 0);
/*     */   }
/*     */   
/*     */   public PsiTypeParameterList getTypeParameterList() {
/* 311 */     return (PsiTypeParameterList)findChildByClass(PsiTypeParameterList.class);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiTypeParameter[] getTypeParameters() {
/* 316 */     PsiTypeParameterList list = getTypeParameterList();
/* 317 */     if (list != null) {
/* 318 */       return list.getTypeParameters();
/*     */     }
/*     */     
/* 321 */     return PsiTypeParameter.EMPTY_ARRAY;
/*     */   }
/*     */   
/*     */   protected IElementType getBodyElementType() {
/* 325 */     return FanElementTypes.CLASS_BODY;
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/FanClassDefinitionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */