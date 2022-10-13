package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.*;


import com.intellij.psi.impl.InheritanceImplUtil;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.TypeConversionUtil;

import java.util.*;


import com.intellij.util.IncorrectOperationException;
import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.lang.fan.resolve.CollectClassMembersUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class FanClassDefinitionImpl extends FanTypeDefinitionImpl implements FanClassDefinition {
    private static final Logger logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.statements.typedefs.FanClassDefinitionImpl");

    public FanClassDefinitionImpl(FanTypeDefinitionStub stubElement) {
        super(stubElement, FanElementTypes.CLASS_DEFINITION);
    }

    public FanClassDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    protected Icon getIconInner() {
        if (hasModifierProperty("abstract")) {
            return Icons.ABSTRACT_CLASS;
        }
        return Icons.CLASS;
    }

    public String toString() {
        return "Class definition";
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        return this;
    }

    public boolean isInterface() {
        return false;
    }

    public boolean isAnnotationType() {
        return false;
    }

    public boolean isEnum() {
        return false;
    }

    public PsiClass[] getInterfaces() {
        return PsiClass.EMPTY_ARRAY;
    }


    public void subtreeChanged() {
        this.fanSlots = null;
        this.fanFields = null;
        this.fanMethods = null;
        super.subtreeChanged();
    }

    @NotNull
    public PsiField[] getFields() {
        return getFanFields();
    }

    @NotNull
    public PsiMethod[] getMethods() {
        return getFanMethods();
    }

    @NotNull
    public PsiMethod[] getConstructors() {
        Set<PsiMethod> constructors = new HashSet<>();
        for (FanMethod method : getFanMethods()) {
            if (method instanceof org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor)
                constructors.add(method);
        }
        return constructors.toArray(new PsiMethod[constructors.size()]);
    }

    @NotNull
    public PsiClass[] getInnerClasses() {
        return PsiClass.EMPTY_ARRAY;
    }

    @NotNull
    public PsiClassInitializer[] getInitializers() {
        return PsiClassInitializer.EMPTY_ARRAY;
    }

    @NotNull
    public PsiField[] getAllFields() {
        return PsiField.EMPTY_ARRAY;
    }

    @NotNull
    public PsiMethod[] getAllMethods() {
        List<PsiMethod> allMethods = new ArrayList<>();
        getAllMethodsInner(this, allMethods, new HashSet<>());

        return allMethods.toArray(new PsiMethod[allMethods.size()]);
    }

    private static void getAllMethodsInner(PsiClass clazz, List<PsiMethod> allMethods, HashSet<PsiClass> visited) {
        if (visited.contains(clazz))
            return;
        visited.add(clazz);

        allMethods.addAll(Arrays.asList(clazz.getMethods()));


        PsiClass[] supers = clazz.getSupers();
        for (PsiClass aSuper : supers) {
            getAllMethodsInner(aSuper, allMethods, visited);
        }
    }

    @NotNull
    public PsiClass[] getAllInnerClasses() {
        return PsiClass.EMPTY_ARRAY;
    }

    @Nullable
    public PsiField findFieldByName(String name, boolean checkBases) {
        if (!checkBases) {
            for (FanField field : getFanFields()) {
                if (name.equals(field.getName())) return field;

            }
            return null;
        }

        Map<String, CandidateInfo> fieldsMap = CollectClassMembersUtil.getAllFields(this);
        CandidateInfo info = fieldsMap.get(name);
        return (info == null) ? null : (PsiField) info.getElement();
    }

    @Nullable
    public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
        MethodSignature patternSignature = patternMethod.getSignature(PsiSubstitutor.EMPTY);
        for (PsiMethod method : findMethodsByName(patternMethod.getName(), checkBases, false)) {
            PsiClass clazz = method.getContainingClass();
            PsiSubstitutor superSubstitutor = TypeConversionUtil.getClassSubstitutor(clazz, this, PsiSubstitutor.EMPTY);
            assert superSubstitutor != null;
            MethodSignature signature = method.getSignature(superSubstitutor);
            if (signature.equals(patternSignature)) return method;

        }
        return null;
    }

    @NotNull
    public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
        return findMethodsBySignature(patternMethod, checkBases, true);
    }

    private PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases, boolean includeSynthetic) {
        ArrayList<PsiMethod> result = new ArrayList<>();
        MethodSignature patternSignature = patternMethod.getSignature(PsiSubstitutor.EMPTY);
        for (PsiMethod method : findMethodsByName(patternMethod.getName(), checkBases, includeSynthetic)) {
            PsiClass clazz = method.getContainingClass();
            if (clazz != null) {
                PsiSubstitutor superSubstitutor = TypeConversionUtil.getClassSubstitutor(clazz, (PsiClass) this, PsiSubstitutor.EMPTY);
                assert superSubstitutor != null;
                MethodSignature signature = method.getSignature(superSubstitutor);
                if (signature.equals(patternSignature)) {
                    result.add(method);
                }
            }
        }
        return result.toArray(new PsiMethod[result.size()]);
    }


    @NotNull
    public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
        return findMethodsByName(name, checkBases, true);
    }

    private PsiMethod[] findMethodsByName(String name, boolean checkBases, boolean includeSyntheticAccessors) {
        if (!checkBases) {
            List<PsiMethod> result = new ArrayList<>();
            for (PsiMethod method : includeSyntheticAccessors ? getMethods() : getFanMethods()) {
                if (name.equals(method.getName())) result.add(method);

            }
            return result.toArray(new PsiMethod[result.size()]);
        }

        Map<String, List<CandidateInfo>> methodsMap = CollectClassMembersUtil.getAllMethods(this, includeSyntheticAccessors);
        return FanPsiImplUtil.mapToMethods(methodsMap.get(name));
    }

    @NotNull
    public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(String name, boolean checkBases) {
        ArrayList<Pair<PsiMethod, PsiSubstitutor>> result = new ArrayList<>();

        if (!checkBases) {
            PsiMethod[] methods = findMethodsByName(name, false);
            for (PsiMethod method : methods) {
                result.add(new Pair(method, PsiSubstitutor.EMPTY));
            }
        } else {
            Map<String, List<CandidateInfo>> map = CollectClassMembersUtil.getAllMethods(this, true);
            List<CandidateInfo> candidateInfos = map.get(name);
            if (candidateInfos != null) {
                for (CandidateInfo info : candidateInfos) {
                    PsiElement element = info.getElement();
                    result.add(new Pair(element, info.getSubstitutor()));
                }
            }
        }

        return result;
    }

    @NotNull
    public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
        Map<String, List<CandidateInfo>> allMethodsMap = CollectClassMembersUtil.getAllMethods(this, true);
        List<Pair<PsiMethod, PsiSubstitutor>> result = new ArrayList<>();
        for (List<CandidateInfo> infos : allMethodsMap.values()) {
            for (CandidateInfo info : infos) {
                result.add(new Pair(info.getElement(), info.getSubstitutor()));
            }
        }

        return result;
    }

    public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases) {
        return null;
    }

    public PsiJavaToken getLBrace() {
        return null;
    }

    public PsiJavaToken getRBrace() {
        return null;
    }

    public PsiElement getScope() {
        return getParent();
    }

    public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
        return InheritanceImplUtil.isInheritor(this, baseClass, checkDeep);
    }

    public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
        return InheritanceImplUtil.isInheritorDeep(this, baseClass, classToByPass);
    }

    public PsiClass getContainingClass() {
        return null;
    }

    @NotNull
    public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
        return Collections.emptyList();
    }

    public PsiDocComment getDocComment() {
        return null;
    }

    public boolean isDeprecated() {
        return false;
    }

    public boolean hasTypeParameters() {
        return ((getTypeParameters()).length > 0);
    }

    public PsiTypeParameterList getTypeParameterList() {
        return findChildByClass(PsiTypeParameterList.class);
    }

    @NotNull
    public PsiTypeParameter[] getTypeParameters() {
        PsiTypeParameterList list = getTypeParameterList();
        if (list != null) {
            return list.getTypeParameters();
        }

        return PsiTypeParameter.EMPTY_ARRAY;
    }

    protected IElementType getBodyElementType() {
        return FanElementTypes.CLASS_BODY;
    }
}