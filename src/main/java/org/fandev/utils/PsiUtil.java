/*    */ package org.fandev.utils;
/*    */ 
/*    */

import com.intellij.compiler.CompilerConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PsiUtil
/*    */ {
/*    */   public static boolean isAccessible(PsiElement place, PsiMember member) {
/* 21 */     return com.intellij.psi.util.PsiUtil.isAccessible(member, place, null);
/*    */   }
/*    */   
/*    */   public static int getFlags(PsiModifierListOwner paramPsiModifierListOwner, boolean paramBoolean) {
/* 25 */     PsiFile localPsiFile = paramPsiModifierListOwner.getContainingFile();
/*    */     
/* 27 */     VirtualFile localVirtualFile = (localPsiFile == null) ? null : localPsiFile.getVirtualFile();
/*    */     
/* 29 */     int enumFlag = (paramPsiModifierListOwner instanceof PsiClass && ((PsiClass)paramPsiModifierListOwner).isEnum()) ? 1 : 0;
/*    */     
/* 31 */     int mainFlag = ((paramPsiModifierListOwner.hasModifierProperty("final") && enumFlag == 0) ? 1024 : 0) | ((paramPsiModifierListOwner.hasModifierProperty("static") && enumFlag == 0) ? 512 : 0) | (paramBoolean ? 2048 : 0) | (isExcluded(localVirtualFile, paramPsiModifierListOwner.getProject()) ? 4096 : 0);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     if (paramPsiModifierListOwner instanceof PsiClass && 
/* 37 */       paramPsiModifierListOwner.hasModifierProperty("abstract") && !((PsiClass)paramPsiModifierListOwner).isInterface()) {
/* 38 */       mainFlag |= 0x100;
/*    */     }
/*    */     
/* 41 */     return mainFlag;
/*    */   }
/*    */   
/*    */   public static boolean isExcluded(VirtualFile paramVirtualFile, Project paramProject) {
/* 45 */     return (paramVirtualFile != null && ProjectRootManager.getInstance(paramProject).getFileIndex().isInSource(paramVirtualFile) && CompilerConfiguration.getInstance(paramProject).isExcludedFromCompilation(paramVirtualFile));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static FanTopLevelDefintion findPreviousTopLevelElementByThisElement(PsiElement element) {
/* 50 */     PsiElement parent = element.getParent();
/*    */     
/* 52 */     while (parent != null && !(parent instanceof FanTopLevelDefintion)) {
/* 53 */       parent = parent.getParent();
/*    */     }
/*    */     
/* 56 */     if (parent == null) return null; 
/* 57 */     return (FanTopLevelDefintion)parent;
/*    */   }
/*    */ }


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/utils/PsiUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */