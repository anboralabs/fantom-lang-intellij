package org.fandev.index;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.stubs.StubIndex;
/*import fan.sys.List;
import fan.sys.Pod;
import fan.sys.Type;
import org.fandev.lang.fan.PodFileType;*/
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
//import org.fandev.module.FanModuleType;
import org.fandev.utils.FanUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class FanIndex {
    private final Project project;
    private final PsiManager psiManager;
    private final Map<String, VirtualFile> typeToFile = new HashMap<String, VirtualFile>();
    private final Map<String, Set<String>> libNameToTypesSet = new HashMap<String, Set<String>>();
    private final Map<String, String> podNameToLibName = new HashMap<String, String>();
    private final Map<String, VirtualFile> podToFile = new HashMap<String, VirtualFile>();

    private static final Logger logger = Logger.getInstance("org.fandev.index.FanIndex");
    public static final String COMPONENT_NAME = "Fantom Index";

    public FanIndex(Project project) {
        this.project = project;
        this.psiManager = PsiManager.getInstance(project);
    }

    public VirtualFile getVirtualFileByTypeName(@NotNull String typeName) {
        Collection<FanTypeDefinition> typeDefs = getProjectTypes(typeName);
        if (typeDefs != null && typeDefs.size() > 0) {
            return ((FanTypeDefinition) typeDefs.iterator().next()).getContainingFile().getVirtualFile();
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

    public Set<String> getLibraryTypeNames(@NotNull String libName) {
        if (this.libNameToTypesSet.containsKey(libName)) {
            return this.libNameToTypesSet.get(libName);
        }
        return new HashSet<String>();
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

    @Nullable
    public FanTypeDefinition getPod(@NotNull String podName) {
        FanFile file = getFanFileByPodName(podName);
        if (file != null) {
            for (FanTypeDefinition def : file.getTypeDefinitions()) {
                if (FanUtil.isFanPod((PsiElement) def) && podName.equals(def.getName())) {
                    return def;
                }
            }
        }
        return null;
    }

    public Set<FanTypeDefinition> getPodStartingWith(@NotNull String prefix) {
        Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();

        for (String podName : getAllPodNames()) {
            FanFile file = getFanFileByPodName(podName);
            for (FanTypeDefinition def : file.getTypeDefinitions()) {
                if (FanUtil.isFanPod((PsiElement) def) && def.getName().startsWith(prefix)) {
                    matching.add(def);
                }
            }
        }
        return matching;
    }

    public Set<String> getAllTypeNames() {
        Set<String> allTypes = new HashSet<String>();
        for (Set<String> libTypes : this.libNameToTypesSet.values()) {
            allTypes.addAll(libTypes);
        }
        return allTypes;
    }

    public Set<FanTypeDefinition> getAllTypesStartingWith(@NotNull String prefix) {
        Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();
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
        Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();
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

    public Set<VirtualFile> getLibraryVirtualFiles(@NotNull String libName) {
        Set<String> types = getLibraryTypeNames(libName);
        Set<VirtualFile> files = new HashSet<VirtualFile>(types.size());

        for (String type : types) {
            VirtualFile file = getVirtualFileByTypeName(type);
            if (file != null) {
                files.add(file);
            }
        }
        return files;
    }

    public Set<PsiFile> getLibraryPsiFiles(@NotNull String libName) {
        return getPsiFiles(getLibraryVirtualFiles(libName));
    }

    public Set<VirtualFile> getAllVirtualFiles() {
        return new HashSet<VirtualFile>(this.typeToFile.values());
    }

    public Set<PsiFile> getAllPsiFiles() {
        return getPsiFiles(getAllVirtualFiles());
    }

    private Set<PsiFile> getPsiFiles(Set<VirtualFile> files) {
        Set<PsiFile> psiFiles = new HashSet<PsiFile>(files.size());

        for (VirtualFile file : files) {
            PsiFile psiFile = this.psiManager.findFile(file);
            if (psiFile != null) psiFiles.add(psiFile);
        }
        return psiFiles;
    }

    private FanTypeDefinition getProjectType(@NotNull String podName, @NotNull String typeName) {
        Collection<FanTypeDefinition> types = getProjectTypes(typeName);
        if (types.size() > 0) {
            for (FanTypeDefinition typeDef : types) {
                if (typeName.equals(typeDef.getName()) && podName.equals(typeDef.getPodName())) {
                    return typeDef;
                }
            }
        }
        return null;
    }

    private Collection<FanTypeDefinition> getProjectTypes(@NotNull String typeName) {
        return StubIndex.getInstance().get(FanShortClassNameIndex.KEY, typeName, this.project, null);
    }

    /*public void projectOpened() {
        Module[] modules = ModuleManager.getInstance(this.project).getModules();
        for (Module module : modules) {
            if (module.getModuleType() == FanModuleType.getInstance()) {
                Sdk sdk = FanUtil.getSdk(module);
                if (sdk == null) {
                    logger.warn("Module " + module.getName() + " has no valid Fantom sdk");
                } else {

                    FanUtil.setFanHome(sdk);


                    ModifiableRootModel modifiableRootModel = ModuleRootManager.getInstance(module).getModifiableModel();
                    LibraryTable libraryTable = modifiableRootModel.getModuleLibraryTable();
                    Library[] libraries = libraryTable.getLibraries();
                    for (Library library : libraries)
                        indexLibrary(library);
                }
            }
        }
    }*/

    /*public void indexLibrary(final Library library) {
        logger.debug("Indexing library " + library.getName());
        ApplicationManager.getApplication().runReadAction(new Runnable() {
            public void run() {
                VirtualFile[] files = library.getFiles(OrderRootType.SOURCES);
                VirtualFile[] arr$ = files;
                int len$ = arr$.length, i$ = 0;
                while (true) {
                    if (i$ < len$) {
                        VirtualFile libFile = arr$[i$];
                        if (libFile.getFileType() == PodFileType.POD_FILE_TYPE)
                            try {
                                String podName;
                                VirtualFile jarFile = VirtualFileManager.getInstance().findFileByUrl("jar://" + libFile.getPath() + "!/");

                                String libName = libFile.getName();
                                if (!FanIndex.this.libNameToTypesSet.containsKey(libName)) {
                                    FanIndex.this.libNameToTypesSet.put(libName, new HashSet());
                                }
                                Set<String> libTypes = (Set<String>) FanIndex.this.libNameToTypesSet.get(libName);


                                if (libName.indexOf(".pod") > 0) {
                                    podName = libName.substring(0, libName.indexOf(".pod"));
                                    FanIndex.this.podNameToLibName.put(podName, libName);
                                } else {
                                    FanIndex.logger.warn("Library " + libName + " is not a pod");

                                    i$++;
                                }
                                Pod pod = Pod.find(podName);
                                if (pod != null) {
                                    VirtualFile podSrcFilePath = jarFile.findFileByRelativePath("src/pod.fan");
                                    if (podSrcFilePath != null) {
                                        FanIndex.this.podToFile.put(podName, podSrcFilePath);
                                    }

                                    List podTypes = pod.types();
                                    for (int i = 0; i < podTypes.size(); i++) {
                                        Type type = (Type) podTypes.get(i);
                                        VirtualFile srcFilePath = jarFile.findFileByRelativePath("src/" + type.name() + "." + "fan");
                                        libTypes.add(type.name());
                                        if (srcFilePath != null) {
                                            FanIndex.this.typeToFile.put(type.name(), srcFilePath);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                FanIndex.logger.warn("Could not index library " + libFile.getPath() + ":" + e.getMessage());
                            }
                    } else {
                        break;
                    }

                    i$++;
                }
            }
        });
    }*/

    public void projectClosed() {
        this.typeToFile.clear();
        this.libNameToTypesSet.clear();
        this.podNameToLibName.clear();
    }

    @NotNull
    public String getComponentName() {
        return "Fantom Index";
    }
}