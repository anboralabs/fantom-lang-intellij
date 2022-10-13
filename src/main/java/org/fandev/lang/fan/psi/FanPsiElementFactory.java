package org.fandev.lang.fan.psi;

import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import org.fandev.lang.fan.FantomFileType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.topLevel.FanTopStatement;
import org.fandev.lang.fan.psi.impl.synthetic.FanLightIdentifier;
import org.jetbrains.annotations.Nullable;

public class FanPsiElementFactory {
    private Project myProject;
    private static String DUMMY = "dummy.";

    public FanPsiElementFactory(Project project) {
        this.myProject = project;
    }

    public static FanPsiElementFactory getInstance(Project project) {
        return project.getService(FanPsiElementFactory.class);
    }

    @Nullable
    public FanTopStatement createTopElementFromText(String text) {
        PsiFile dummyFile = PsiFileFactory.getInstance(this.myProject).createFileFromText(DUMMY + FantomFileType.INSTANCE.getDefaultExtension(), text);

        PsiElement firstChild = dummyFile.getFirstChild();
        if (!(firstChild instanceof FanTopStatement)) return null;

        return (FanTopStatement) firstChild;
    }

    public PsiCodeBlock createMethodBodyFromText(String text) {
        StringBuilder sb = new StringBuilder();
        sb.append("class foo {\n");
        sb.append("public Void bar() {\n");
        sb.append(text);
        sb.append("}");
        FanFile file = createDummyFile(sb.toString());
        FanTypeDefinition type = (FanTypeDefinition) file.getTopLevelDefinitions()[0];
        PsiMethod method = type.getMethods()[0];
        return method.getBody();
    }

    private FanFile createDummyFile(String s, boolean isPhisical) {
        return (FanFile) PsiFileFactory.getInstance(this.myProject).createFileFromText("DUMMY__." + FantomFileType.INSTANCE.getDefaultExtension(), (FileType) FantomFileType.INSTANCE, s, System.currentTimeMillis(), isPhisical);
    }

    private FanFile createDummyFile(String s) {
        return createDummyFile(s, false);
    }

    public PsiIdentifier createIdentifier(PsiManager manager, PsiFile file, String name) {
        return (PsiIdentifier) new FanLightIdentifier(manager, file, name);
    }
}