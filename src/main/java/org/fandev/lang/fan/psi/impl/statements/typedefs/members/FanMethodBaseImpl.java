/*     */ package org.fandev.lang.fan.psi.impl.statements.typedefs.members;
/*     */ import com.intellij.lang.ASTNode;
/*     */ import com.intellij.psi.*;
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
/*     */ import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.stubs.IStubElementType;
/*     */ import com.intellij.psi.stubs.NamedStub;
/*     */ import com.intellij.psi.util.MethodSignature;
/*     */ import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.swing.Icon;
/*     */ import com.intellij.psi.util.MethodSignatureUtil;
import org.fandev.icons.Icons;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
/*     */ import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
/*     */ import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class FanMethodBaseImpl<T extends NamedStub> extends FanSlotElementImpl<T> implements FanMethod {
/*     */   public FanMethodBaseImpl(T t, @NotNull IStubElementType iStubElementType) {
/*  30 */     super(t, iStubElementType);
/*     */   }
/*     */   
/*     */   public FanMethodBaseImpl(ASTNode astNode) {
/*  34 */     super(astNode);
/*     */   }
/*     */ 
/*     */   
/*     */   public Icon getIconInner() {
/*  39 */     return Icons.METHOD;
/*     */   }
/*     */   
/*     */   public PsiType getReturnType() {
/*  43 */     FanTypeElement typeElement = (FanTypeElement)findChildByClass(FanTypeElement.class);
/*  44 */     if (typeElement != null) {
/*  45 */       return typeElement.getType();
/*     */     }
/*  47 */     return PsiType.VOID;
/*     */   }
/*     */   
/*     */   public PsiType getReturnTypeNoResolve() {
/*  51 */     return getReturnType();
/*     */   }
/*     */   
/*     */   public PsiTypeElement getReturnTypeElement() {
/*  55 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiParameterList getParameterList() {
/*  60 */     FanParameterList parameterList = (FanParameterList)findChildByClass(FanParameterList.class);
/*  61 */     assert parameterList != null;
/*  62 */     return (PsiParameterList)parameterList;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiReferenceList getThrowsList() {
/*  67 */     return null;
/*     */   }
/*     */   
/*     */   public PsiCodeBlock getBody() {
/*  71 */     return (PsiCodeBlock)findChildByClass(PsiCodeBlock.class);
/*     */   }
/*     */   
/*     */   public boolean isVarArgs() {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MethodSignature getSignature(@NotNull PsiSubstitutor substitutor) {
/*  80 */     return (MethodSignature)MethodSignatureBackedByPsiMethod.create((PsiMethod)this, substitutor);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findSuperMethods() {
/*  85 */     PsiClass containingClass = getContainingClass();
/*  86 */     if (containingClass == null) return PsiMethod.EMPTY_ARRAY;
/*     */     
/*  88 */     Set<PsiMethod> methods = new HashSet<PsiMethod>();
/*  89 */     findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<PsiClass>(), createMethodSignature((PsiMethod)this), new HashSet<MethodSignature>());
/*     */ 
/*     */     
/*  92 */     return methods.<PsiMethod>toArray(new PsiMethod[methods.size()]);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findSuperMethods(boolean checkAccess) {
/*  97 */     PsiClass containingClass = getContainingClass();
/*     */     
/*  99 */     Set<PsiMethod> methods = new HashSet<PsiMethod>();
/* 100 */     findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<PsiClass>(), createMethodSignature((PsiMethod)this), new HashSet<MethodSignature>());
/*     */ 
/*     */     
/* 103 */     return methods.<PsiMethod>toArray(new PsiMethod[methods.size()]);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findSuperMethods(PsiClass parentClass) {
/* 108 */     Set<PsiMethod> methods = new HashSet<PsiMethod>();
/* 109 */     findSuperMethodRecursilvely(methods, parentClass, false, new HashSet<PsiClass>(), createMethodSignature((PsiMethod)this), new HashSet<MethodSignature>());
/*     */     
/* 111 */     return methods.<PsiMethod>toArray(new PsiMethod[methods.size()]);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean checkAccess) {
/* 116 */     PsiClass containingClass = getContainingClass();
/*     */     
/* 118 */     Set<PsiMethod> methods = new HashSet<PsiMethod>();
/* 119 */     MethodSignature signature = createMethodSignature((PsiMethod)this);
/* 120 */     findSuperMethodRecursilvely(methods, containingClass, true, new HashSet<PsiClass>(), signature, new HashSet<MethodSignature>());
/*     */     
/* 122 */     List<MethodSignatureBackedByPsiMethod> result = new ArrayList<MethodSignatureBackedByPsiMethod>();
/* 123 */     for (PsiMethod method : methods) {
/* 124 */       result.add(method.getHierarchicalMethodSignature());
/*     */     }
/*     */     
/* 127 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PsiMethod findDeepestSuperMethod() {
/* 134 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public PsiMethod[] findDeepestSuperMethods() {
/* 139 */     return new PsiMethod[0];
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public HierarchicalMethodSignature getHierarchicalMethodSignature() {
/* 144 */     return PsiSuperMethodImplUtil.getHierarchicalMethodSignature((PsiMethod)this);
/*     */   }
/*     */   
/*     */   public void setBlock(PsiCodeBlock newBlock) {
/* 148 */     ASTNode newNode = newBlock.getNode().copyElement();
/* 149 */     PsiCodeBlock oldBlock = getBody();
/* 150 */     if (oldBlock == null) {
/* 151 */       getNode().addChild(newNode);
/*     */       return;
/*     */     } 
/* 154 */     getNode().replaceChild(oldBlock.getNode(), newNode);
/*     */   }
/*     */   
/*     */   public static MethodSignature createMethodSignature(PsiMethod method) {
/* 158 */     PsiParameter[] parameters = method.getParameterList().getParameters();
/* 159 */     PsiType[] types = new PsiType[parameters.length];
/* 160 */     for (int i = 0; i < types.length; i++) {
/* 161 */       types[i] = parameters[i].getType();
/*     */     }
/* 163 */     return MethodSignatureUtil.createMethodSignature(method.getName(), types, PsiTypeParameter.EMPTY_ARRAY, PsiSubstitutor.EMPTY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void findSuperMethodRecursilvely(Set<PsiMethod> methods, PsiClass psiClass, boolean allowStatic, Set<PsiClass> visited, MethodSignature signature, @NotNull Set<MethodSignature> discoveredSupers) {
/* 172 */     if (psiClass == null)
/* 173 */       return;  if (visited.contains(psiClass))
/* 174 */       return;  visited.add(psiClass);
/* 175 */     PsiClassType[] superClassTypes = psiClass.getSuperTypes();
/*     */     
/* 177 */     for (PsiClassType superClassType : superClassTypes) {
/* 178 */       PsiClass resolvedSuperClass = superClassType.resolve();
/*     */       
/* 180 */       if (resolvedSuperClass != null) {
/* 181 */         PsiMethod[] superClassMethods = resolvedSuperClass.getMethods();
/* 182 */         HashSet<MethodSignature> supers = new HashSet<MethodSignature>(3);
/*     */         
/* 184 */         for (PsiMethod superClassMethod : superClassMethods) {
/* 185 */           MethodSignature superMethodSignature = createMethodSignature(superClassMethod);
/*     */           
/* 187 */           if (FanPsiImplUtil.isExtendsSignature(superMethodSignature, signature) && (
/* 188 */             allowStatic || !superClassMethod.getModifierList().hasExplicitModifier("static"))) {
/* 189 */             methods.add(superClassMethod);
/* 190 */             supers.add(superMethodSignature);
/* 191 */             discoveredSupers.add(superMethodSignature);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 196 */         findSuperMethodRecursilvely(methods, resolvedSuperClass, allowStatic, visited, signature, discoveredSupers);
/* 197 */         discoveredSupers.removeAll(supers);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/members/FanMethodBaseImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */