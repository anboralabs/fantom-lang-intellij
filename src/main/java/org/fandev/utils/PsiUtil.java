package org.fandev.utils;


import com.intellij.compiler.CompilerConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.jetbrains.annotations.Nullable;


public class PsiUtil {
    public static boolean isAccessible(PsiElement place, PsiMember member) {
        return com.intellij.psi.util.PsiUtil.isAccessible(member, place, null);
    }

    public static int getFlags(PsiModifierListOwner paramPsiModifierListOwner, boolean paramBoolean) {
        PsiFile localPsiFile = paramPsiModifierListOwner.getContainingFile();

        VirtualFile localVirtualFile = (localPsiFile == null) ? null : localPsiFile.getVirtualFile();

        int enumFlag = (paramPsiModifierListOwner instanceof PsiClass && ((PsiClass) paramPsiModifierListOwner).isEnum()) ? 1 : 0;

        int mainFlag = ((paramPsiModifierListOwner.hasModifierProperty("final") && enumFlag == 0) ? 1024 : 0) | ((paramPsiModifierListOwner.hasModifierProperty("static") && enumFlag == 0) ? 512 : 0) | (paramBoolean ? 2048 : 0) | (isExcluded(localVirtualFile, paramPsiModifierListOwner.getProject()) ? 4096 : 0);


        if (paramPsiModifierListOwner instanceof PsiClass &&
                paramPsiModifierListOwner.hasModifierProperty("abstract") && !((PsiClass) paramPsiModifierListOwner).isInterface()) {
            mainFlag |= 0x100;
        }

        return mainFlag;
    }

    public static boolean isExcluded(VirtualFile paramVirtualFile, Project paramProject) {
        return (paramVirtualFile != null && ProjectRootManager.getInstance(paramProject).getFileIndex().isInSource(paramVirtualFile) && CompilerConfiguration.getInstance(paramProject).isExcludedFromCompilation(paramVirtualFile));
    }

    @Nullable
    public static FanTopLevelDefintion findPreviousTopLevelElementByThisElement(PsiElement element) {
        PsiElement parent = element.getParent();

        while (parent != null && !(parent instanceof FanTopLevelDefintion)) {
            parent = parent.getParent();
        }

        if (parent == null) return null;
        return (FanTopLevelDefintion) parent;
    }
}