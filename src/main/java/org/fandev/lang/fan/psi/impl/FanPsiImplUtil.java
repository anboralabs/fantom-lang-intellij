/*    */ package org.fandev.lang.fan.psi.impl;
/*    */ 
/*    */ import com.intellij.psi.PsiArrayType;
/*    */ import com.intellij.psi.PsiElement;
/*    */ import com.intellij.psi.PsiIdentifier;
/*    */ import com.intellij.psi.PsiManager;
/*    */ import com.intellij.psi.PsiMethod;
/*    */ import com.intellij.psi.PsiType;
/*    */ import com.intellij.psi.infos.CandidateInfo;
/*    */ import com.intellij.psi.util.MethodSignature;
/*    */ import com.intellij.psi.util.TypeConversionUtil;
/*    */ import com.intellij.util.IncorrectOperationException;
/*    */ import java.util.List;
/*    */ import org.fandev.lang.fan.psi.FanPsiElementFactory;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public class FanPsiImplUtil
/*    */ {
/*    */   public static PsiMethod[] mapToMethods(@Nullable List<CandidateInfo> list) {
/* 29 */     if (list == null) return PsiMethod.EMPTY_ARRAY; 
/* 30 */     PsiMethod[] result = new PsiMethod[list.size()];
/* 31 */     for (int i = 0; i < list.size(); i++) {
/* 32 */       result[i] = (PsiMethod)((CandidateInfo)list.get(i)).getElement();
/*    */     }
/*    */     
/* 35 */     return result;
/*    */   }
/*    */   
/*    */   public static boolean isExtendsSignature(MethodSignature superSignatureCandidate, MethodSignature subSignature) {
/* 39 */     String name1 = superSignatureCandidate.getName();
/* 40 */     String name2 = subSignature.getName();
/* 41 */     if (!name1.equals(name2)) return false;
/*    */     
/* 43 */     PsiType[] superTypes = superSignatureCandidate.getParameterTypes();
/* 44 */     PsiType[] subTypes = subSignature.getParameterTypes();
/* 45 */     if (subTypes.length != superTypes.length) return false; 
/* 46 */     for (int i = 0; i < subTypes.length - 1; i++) {
/* 47 */       PsiType superType = TypeConversionUtil.erasure(superTypes[i]);
/* 48 */       PsiType subType = TypeConversionUtil.erasure(subTypes[i]);
/* 49 */       if (!superType.isAssignableFrom(subType)) return false;
/*    */     
/*    */     } 
/* 52 */     if (superTypes.length > 0) {
/* 53 */       PsiType lastSuperType = TypeConversionUtil.erasure(superTypes[superTypes.length - 1]);
/* 54 */       PsiType lastSubType = TypeConversionUtil.erasure(subTypes[superTypes.length - 1]);
/* 55 */       if (lastSuperType instanceof PsiArrayType && !(lastSubType instanceof PsiArrayType))
/* 56 */       { PsiType componentType = ((PsiArrayType)lastSuperType).getComponentType();
/* 57 */         if (!lastSubType.isConvertibleFrom(componentType)) return false;
/*    */          }
/* 59 */       else if (!lastSuperType.isAssignableFrom(lastSubType)) { return false; }
/*    */     
/*    */     } 
/*    */     
/* 63 */     return true;
/*    */   }
/*    */   
/*    */   public static PsiElement setName(PsiElement element, String name) throws IncorrectOperationException {
/* 67 */     PsiManager manager = element.getManager();
/* 68 */     FanPsiElementFactory factory = FanPsiElementFactory.getInstance(manager.getProject());
/* 69 */     PsiIdentifier newNameIdentifier = factory.createIdentifier(manager, element.getContainingFile(), name);
/* 70 */     return element.replace((PsiElement)newNameIdentifier);
/*    */   }
/*    */   
/*    */   public static PsiIdentifier getFanIdentifier(PsiElement element) {
/* 74 */     if (element != null) {
/* 75 */       if (element instanceof FanIdentifierImpl) {
/* 76 */         return (FanIdentifierImpl)element;
/*    */       }
/* 78 */       if (element instanceof org.fandev.lang.fan.psi.impl.synthetic.FanLightIdentifier) {
/* 79 */         return (PsiIdentifier)element;
/*    */       }
/* 81 */       return new FanIdentifierImpl(element.getNode());
/*    */     } 
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/FanPsiImplUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */