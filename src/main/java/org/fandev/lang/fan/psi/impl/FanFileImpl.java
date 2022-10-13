package org.fandev.lang.fan.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.fandev.lang.fan.FantomFileType;
import org.fandev.lang.fan.FantomLanguage;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.jetbrains.annotations.NotNull;


public class FanFileImpl
        extends PsiFileBase
        implements FanFile {
    private String podName;
    private static final Logger logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.FanFileImpl");

    public FanFileImpl(FileViewProvider fileViewProvider) {
        super(fileViewProvider, FantomLanguage.INSTANCE);
    }

    @NotNull
    public FileType getFileType() {
        return FantomFileType.INSTANCE;
    }

    public String getPodName() {
        if (this.podName != null) {
            return this.podName;
        }

        if ("build.fan".equals(getName())) {

            FanClassDefinition[] classDef = findChildrenByClass(FanClassDefinition.class);
            if (classDef.length > 0) {
                FanMethod[] methods = classDef[0].getFanMethods();
                if (methods.length > 0) {
                    for (FanMethod method : methods) {

                        if (method.getName().equals("setup")) {
                            String setupBody = method.getBody().getText();
                            int podNameIdx = setupBody.indexOf("podName");
                            if (podNameIdx != -1) {
                                int firstDQ = setupBody.indexOf('"', podNameIdx + "podName".length() + 1);
                                int lastDQ = setupBody.indexOf('"', firstDQ + 1);
                                this.podName = setupBody.substring(firstDQ + 1, lastDQ);
                                return this.podName;
                            }
                        }
                    }
                }
            }
            logger.warn("Did not find pod name in " + ((getVirtualFile() != null) ? getVirtualFile().getPath() : toString()));
            return "NotFound";
        }


        VirtualFile myVirtualFile = getVirtualFile();
        if (myVirtualFile != null && myVirtualFile.getUrl().contains(".pod")) {
            String url = myVirtualFile.getUrl();
            String podPath = url.substring(0, url.indexOf(".pod"));
            int podNameStartIndex = podPath.lastIndexOf('/');
            podNameStartIndex = (podNameStartIndex < 0) ? 0 : (podNameStartIndex + 1);
            this.podName = podPath.substring(podNameStartIndex);
            return this.podName;
        }


        PsiDirectory psiDirectory = getParent();
        int upMax = 4;
        while (psiDirectory != null && upMax > 0) {
            PsiFile[] files = psiDirectory.getFiles();
            if (files != null) {
                for (PsiFile file : files) {
                    if ("build.fan".equals(file.getName())) {
                        this.podName = ((FanFile) file).getPodName();
                        return this.podName;
                    }
                }
            }
            psiDirectory = psiDirectory.getParentDirectory();
            upMax--;
        }
        return "NotFound";
    }

    public FanTypeDefinition[] getTypeDefinitions() {
        return findChildrenByClass(FanTypeDefinition.class);
    }

    public FanTypeDefinition getTypeByName(String name) {
        for (FanTypeDefinition typeDef : getTypeDefinitions()) {
            if (name.equals(typeDef.getName())) {
                return typeDef;
            }
        }
        return null;
    }

    @NotNull
    public PsiClass[] getClasses() {
        return getTypeDefinitions();
    }

    public String getPackageName() {
        return this.podName;
    }

    public void setPackageName(String s) throws IncorrectOperationException {
        this.podName = s;
    }

    public FanTopLevelDefintion[] getTopLevelDefinitions() {
        return findChildrenByClass(FanTopLevelDefintion.class);
    }
}