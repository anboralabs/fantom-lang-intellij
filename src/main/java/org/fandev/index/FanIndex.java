package org.fandev.index;


import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.stubs.StubIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import org.fandev.utils.FanUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class FanIndex {
    private final Project project;
    private final PsiManager psiManager;
    private final Map<String, VirtualFile> typeToFile = new HashMap<>();
    private final Map<String, Set<String>> libNameToTypesSet = new HashMap<>();
    private final Map<String, String> podNameToLibName = new HashMap<>();
    private final Map<String, VirtualFile> podToFile = new HashMap<>();

    private static final Logger logger = Logger.getInstance("org.fandev.index.FanIndex");
    public static final String COMPONENT_NAME = "Fantom Index";

    public FanIndex(Project project) {
        this.project = project;
        this.psiManager = PsiManager.getInstance(project);
    }

    public VirtualFile getVirtualFileByTypeName(@NotNull String typeName) {
        Collection<FanTypeDefinition> typeDefs = getProjectTypes(typeName);
        if (typeDefs != null && typeDefs.size() > 0) {
            return (typeDefs.iterator().next()).getContainingFile().getVirtualFile();
        }
        return this.typeToFile.get(typeName);
    }

    public VirtualFile getVirtualFileByPodName(@NotNull String podName) {
        return this.podToFile.get(podName);
    }

    @Nullable
    public FanFile getFanFileByTypeName(@NotNull String typeName) {
        VirtualFile typeFile = getVirtualFileByTypeName(typeName);
        if (typeFile != null) {
            return (FanFile) this.psiManager.findFile(typeFile);
        }
        return null;
    }


    public Set<String> getPodTypeNames(@NotNull String podName) {
        String libName = this.podNameToLibName.get(podName);
        if (libName != null && this.libNameToTypesSet.containsKey(libName)) {
            return this.libNameToTypesSet.get(libName);
        }
        return new HashSet<String>();
    }


    public Set<String> getAllPodNames() {
        return this.podToFile.keySet();
    }

    @Nullable
    public FanFile getFanFileByPodName(@NotNull String podName) {
        VirtualFile typeFile = getVirtualFileByPodName(podName);
        if (typeFile != null) {
            return (FanFile) this.psiManager.findFile(typeFile);
        }
        return null;
    }

    public Set<FanTypeDefinition> getPodStartingWith(@NotNull String prefix) {
        Set<FanTypeDefinition> matching = new HashSet<>();

        for (String podName : getAllPodNames()) {
            FanFile file = getFanFileByPodName(podName);
            for (FanTypeDefinition def : file.getTypeDefinitions()) {
                if (FanUtil.isFanPod(def) && def.getName().startsWith(prefix)) {
                    matching.add(def);
                }
            }
        }
        return matching;
    }

    public Set<String> getAllTypeNames() {
        Set<String> allTypes = new HashSet<>();
        for (Set<String> libTypes : this.libNameToTypesSet.values()) {
            allTypes.addAll(libTypes);
        }
        return allTypes;
    }

    public Set<FanTypeDefinition> getAllTypesStartingWith(@NotNull String prefix) {
        Set<FanTypeDefinition> matching = new HashSet<>();
        for (String name : getAllTypeNames()) {
            if (name.startsWith(prefix)) {
                FanFile typeFile = getFanFileByTypeName(name);
                if (typeFile != null) {
                    for (FanTypeDefinition def : typeFile.getTypeDefinitions()) {
                        if (def.getName().startsWith(prefix)) {
                            matching.add(def);
                        }
                    }
                }
            }
        }
        return matching;
    }

    public Set<FanTypeDefinition> getPodTypesStartingWith(@NotNull String podName, @NotNull String prefix) {
        Set<FanTypeDefinition> matching = new HashSet<>();
        for (String name : getPodTypeNames(podName)) {
            if (name.startsWith(prefix)) {
                FanFile typeFile = getFanFileByTypeName(name);
                if (typeFile != null) {
                    for (FanTypeDefinition def : typeFile.getTypeDefinitions()) {
                        if (def.getName().startsWith(prefix)) {
                            matching.add(def);
                        }
                    }
                }
            }
        }
        return matching;
    }

    private Collection<FanTypeDefinition> getProjectTypes(@NotNull String typeName) {
        return StubIndex.getInstance().get(FanShortClassNameIndex.KEY, typeName, this.project, null);
    }
}