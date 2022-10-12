package org.fandev.utils;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.VariableKind;
import com.intellij.psi.util.PsiTreeUtil;
/*import fan.sys.Map;
import fan.sys.Sys;
import org.fandev.actions.generation.FanTemplatesFactory;
import org.fandev.actions.generation.TemplateProperty;*/
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.expressions.FanClosureExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanPodDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.impl.FanListReferenceType;
import org.fandev.lang.fan.psi.impl.FanMapType;
/*import org.fandev.module.FanModuleType;
import org.fandev.module.pod.PodModel;
import org.fandev.module.wizard.FanModuleBuilder;*/
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.EnumMap;

public class FanUtil {
    private static final Logger logger = Logger.getInstance("org.fandev.utils.FanUtil");

    static {
        System.setProperty("fan.deug", "true");
    }

    /*public static boolean isFanModuleType(@Nullable Module module) {
        return (module != null && FanModuleType.getInstance() == module.getModuleType());
    }

    public static Sdk getSdk(Module module) {
        if (module != null &&
                isFanModuleType(module)) {
            return ModuleRootManager.getInstance(module).getSdk();
        }

        return null;
    }

    public static void setFanHome(Module module) {
        setFanHome(getSdk(module));
    }*/

    public static void setFanHome(@NotNull Sdk moduleSdk) {
        System.setProperty("fan.home", moduleSdk.getHomePath());
    }

    public static void setFanHome(@NotNull String home) {
        System.setProperty("fan.home", home);
    }

    @Nullable
    public static URLClassLoader getSysClassloader(@NotNull String sdkHome) {
        VirtualFile sysJar = VirtualFileUtil.refreshAndFindFileByLocalPath(sdkHome + "/lib/java/sys.jar");
        try {
            if (sysJar.exists()) {
                return new URLClassLoader(new URL[]{(new File(sysJar.getPath())).toURI().toURL()});
            }
        } catch (Exception e) {
            logger.error("Could load sys.jar", e);
        }
        return null;
    }

    /*public static File getJdkHome(Sdk moduleSdk) {
        setFanHome(moduleSdk);
        Map env = Sys.env();
        String fanJavaHome = (String) env.get("java.home");
        if (fanJavaHome != null) {
            fanJavaHome = System.getProperty("java.home");
        }
        return new File(fanJavaHome);
    }

    public static Sdk createFanJdk(Sdk moduleSdk) {
        return JavaSdk.getInstance().createJdk("Fantom JDK", getJdkHome(moduleSdk).getAbsolutePath());
    }*/

    public static VariableKind getVariableKind(@NotNull PsiVariable paramPsiVariable) {
        if (paramPsiVariable instanceof com.intellij.psi.PsiField) {
            if (paramPsiVariable.hasModifierProperty("static")) {
                if (paramPsiVariable.hasModifierProperty("final")) {
                    return VariableKind.STATIC_FINAL_FIELD;
                }

                return VariableKind.STATIC_FIELD;
            }
            return VariableKind.FIELD;
        }

        if (paramPsiVariable instanceof PsiParameter) {
            if (((PsiParameter) paramPsiVariable).getDeclarationScope() instanceof com.intellij.psi.PsiForeachStatement) {
                return VariableKind.LOCAL_VARIABLE;
            }

            return VariableKind.PARAMETER;
        }
        if (paramPsiVariable instanceof com.intellij.psi.PsiLocalVariable) {
            return VariableKind.LOCAL_VARIABLE;
        }
        return VariableKind.LOCAL_VARIABLE;
    }


    /*public static VirtualFile generateBuildScript(final FanModuleBuilder mySettingsHolder, Project project, Object creator) {
        final PodModel pod = mySettingsHolder.getModulePod();
        VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
        final VirtualFile contentEntryPath = virtualFileManager.refreshAndFindFileByUrl(VirtualFileUtil.constructLocalUrl(mySettingsHolder.getContentEntryPath()));


        try {
            VirtualFile buildScript = (VirtualFile) ApplicationManager.getApplication().runWriteAction(new Computable<VirtualFile>() {
                public VirtualFile compute() {
                    EnumMap<TemplateProperty, String> parameters = new EnumMap<TemplateProperty, String>(TemplateProperty.class);

                    parameters.put(TemplateProperty.NAME, pod.getName());
                    VirtualFile buildScript = FanTemplatesFactory.createFromTemplate(contentEntryPath, pod.getBuildScriptName(), "FanBuildScript.fan", parameters, mySettingsHolder);


                    return buildScript;
                }
            });

            if (buildScript == null) {
                return null;
            }

            return buildScript;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static VirtualFile generatePod(final FanModuleBuilder mySettingsHolder, Project project, Object creator) {
        final PodModel pod = mySettingsHolder.getModulePod();
        final VirtualFile contentEntryPath = VirtualFileManager.getInstance().refreshAndFindFileByUrl(VirtualFileUtil.constructLocalUrl(mySettingsHolder.getContentEntryPath()));


        try {
            VirtualFile buildScript = (VirtualFile) ApplicationManager.getApplication().runWriteAction(new Computable<VirtualFile>() {
                public VirtualFile compute() {
                    StringBuilder podSrcDirs = new StringBuilder();
                    int index = 0;
                    for (Pair<String, String> path : (Iterable<Pair<String, String>>) mySettingsHolder.getSourcePaths()) {
                        String fullSrcPath = (String) path.getFirst();
                        if (fullSrcPath.startsWith("/") || fullSrcPath.startsWith("\\")) {
                            fullSrcPath = fullSrcPath.substring(1);
                        }
                        if (!fullSrcPath.endsWith("/") && !fullSrcPath.endsWith("\\")) {
                            fullSrcPath = fullSrcPath + "/";
                        }
                        podSrcDirs.append("`" + fullSrcPath.substring(mySettingsHolder.getContentEntryPath().length()) + "`");

                        if (++index < mySettingsHolder.getSourcePaths().size()) {
                            podSrcDirs.append(",");
                        }
                    }
                    if (mySettingsHolder.getSourcePaths().size() == 0) {
                        podSrcDirs.append("`fan/`");
                    }

                    String podDepends = "Depend(\"sys 1.0\")";

                    EnumMap<TemplateProperty, String> parameters = new EnumMap<TemplateProperty, String>(TemplateProperty.class);

                    parameters.put(TemplateProperty.NAME, pod.getName());
                    parameters.put(TemplateProperty.POD_SRC_DIRS, podSrcDirs.toString());
                    parameters.put(TemplateProperty.POD_DEPENDS, podDepends);
                    parameters.put(TemplateProperty.VERSION, pod.getVersion());

                    VirtualFile buildScript = FanTemplatesFactory.createFromTemplate(contentEntryPath, "pod.fan", "FanPod.fan", parameters, mySettingsHolder);


                    return buildScript;
                }
            });

            if (buildScript == null) {
                return null;
            }

            return buildScript;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }*/

    public static String getFanUriStr(@NotNull String filePath) {
        if (!filePath.startsWith("file:/")) {
            filePath = "file:/" + filePath;
        }
        return filePath;
    }

    @Nullable
    public static FanTypeDefinition getContainingType(@NotNull PsiElement element) {
        PsiElement maybeClazz = PsiTreeUtil.getParentOfType(element, new Class[]{FanTypeDefinition.class, FanFile.class});
        if (isFanType(maybeClazz)) {
            return (FanTypeDefinition) maybeClazz;
        }
        return null;
    }

    public static boolean isFanTypeDefinition(PsiElement element) {
        return isOfType(element, FanTypeDefinition.class);
    }

    public static boolean isFanEnumDefinition(PsiElement element) {
        return isOfType(element, FanEnumDefinition.class);
    }

    public static boolean isFanClosure(PsiElement element) {
        return isOfType(element, FanClosureExpression.class);
    }

    public static boolean isPsiCodeBlock(PsiElement element) {
        return isOfType(element, PsiCodeBlock.class);
    }

    public static boolean isFanMethod(PsiElement element) {
        return isOfType(element, FanMethod.class);
    }

    public static boolean isFanField(PsiElement element) {
        return isOfType(element, FanField.class);
    }

    public static boolean isFanVariable(PsiElement element) {
        return isOfType(element, FanVariable.class);
    }

    public static boolean isFanFile(PsiElement element) {
        return isOfType(element, FanFile.class);
    }

    public static boolean isFanType(PsiElement element) {
        return isOfType(element, FanTypeDefinition.class);
    }

    public static boolean isFanPod(PsiElement element) {
        return isOfType(element, FanPodDefinition.class);
    }

    public static boolean isFanMapType(PsiType element) {
        return isOfType(element, FanMapType.class);
    }

    public static boolean isFanListType(PsiType element) {
        return isOfType(element, FanListReferenceType.class);
    }

    public static boolean isOfType(PsiElement element, Class<?> type) {
        return (element == null) ? false : type.isAssignableFrom(element.getClass());
    }

    public static boolean isOfType(PsiType element, Class<?> type) {
        return (element == null) ? false : type.isAssignableFrom(element.getClass());
    }
}