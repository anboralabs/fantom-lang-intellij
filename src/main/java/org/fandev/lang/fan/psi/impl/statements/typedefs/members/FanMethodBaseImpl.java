package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import com.intellij.lang.ASTNode;
import com.intellij.psi.*;


import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStub;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Icon;

import com.intellij.psi.util.MethodSignatureUtil;
import org.fandev.icons.Icons;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanPsiImplUtil;
import org.jetbrains.annotations.NotNull;

public abstract class FanMethodBaseImpl<T extends NamedStub> extends FanSlotElementImpl<T> implements FanMethod {
    public FanMethodBaseImpl(T t, @NotNull IStubElementType iStubElementType) {
        super(t, iStubElementType);
    }

    public FanMethodBaseImpl(ASTNode astNode) {
        super(astNode);
    }


    public Icon getIconInner() {
        return Icons.METHOD;
    }

    public PsiType getReturnType() {
        FanTypeElement typeElement = findChildByClass(FanTypeElement.class);
        if (typeElement != null) {
            return typeElement.getType();
        }
        return PsiType.VOID;
    }

    public PsiType getReturnTypeNoResolve() {
        return getReturnType();
    }

    public PsiTypeElement getReturnTypeElement() {
        return null;
    }

    @NotNull
    public PsiParameterList getParameterList() {
        FanParameterList parameterList = findChildByClass(FanParameterList.class);
        assert parameterList != null;
        return parameterList;
    }

    @NotNull
    public PsiReferenceList getThrowsList() {
        return null;
    }

    public PsiCodeBlock getBody() {
        return findChildByClass(PsiCodeBlock.class);
    }

    public boolean isVarArgs() {
        return false;
    }

    @NotNull
    public MethodSignature getSignature(@NotNull PsiSubstitutor substitutor) {
        return (MethodSignature) MethodSignatureBackedByPsiMethod.create((PsiMethod) this, substitutor);
    }

    @NotNull
    public PsiMethod[] findSuperMethods() {
        PsiClass containingClass = getContainingClass();
        if (containingClass == null) return PsiMethod.EMPTY_ARRAY;

        Set<PsiMethod> methods = new HashSet<>();
        findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<>(), createMethodSignature(this), new HashSet<>());


        return methods.toArray(new PsiMethod[methods.size()]);
    }

    @NotNull
    public PsiMethod[] findSuperMethods(boolean checkAccess) {
        PsiClass containingClass = getContainingClass();

        Set<PsiMethod> methods = new HashSet<>();
        findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<>(), createMethodSignature(this), new HashSet<>());

        return methods.toArray(new PsiMethod[methods.size()]);
    }

    @NotNull
    public PsiMethod[] findSuperMethods(PsiClass parentClass) {
        Set<PsiMethod> methods = new HashSet<>();
        findSuperMethodRecursilvely(methods, parentClass, false, new HashSet<>(), createMethodSignature(this), new HashSet<>());

        return methods.toArray(new PsiMethod[methods.size()]);
    }

    @NotNull
    public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean checkAccess) {
        PsiClass containingClass = getContainingClass();

        Set<PsiMethod> methods = new HashSet<>();
        MethodSignature signature = createMethodSignature(this);
        findSuperMethodRecursilvely(methods, containingClass, true, new HashSet<>(), signature, new HashSet<>());

        List<MethodSignatureBackedByPsiMethod> result = new ArrayList<>();
        for (PsiMethod method : methods) {
            result.add(method.getHierarchicalMethodSignature());
        }

        return result;
    }


    public PsiMethod findDeepestSuperMethod() {
        return null;
    }

    @NotNull
    public PsiMethod[] findDeepestSuperMethods() {
        return new PsiMethod[0];
    }

    @NotNull
    public HierarchicalMethodSignature getHierarchicalMethodSignature() {
        return PsiSuperMethodImplUtil.getHierarchicalMethodSignature((PsiMethod) this);
    }

    public void setBlock(PsiCodeBlock newBlock) {
        ASTNode newNode = newBlock.getNode().copyElement();
        PsiCodeBlock oldBlock = getBody();
        if (oldBlock == null) {
            getNode().addChild(newNode);
            return;
        }
        getNode().replaceChild(oldBlock.getNode(), newNode);
    }

    public static MethodSignature createMethodSignature(PsiMethod method) {
        PsiParameter[] parameters = method.getParameterList().getParameters();
        PsiType[] types = new PsiType[parameters.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = parameters[i].getType();
        }
        return MethodSignatureUtil.createMethodSignature(method.getName(), types, PsiTypeParameter.EMPTY_ARRAY, PsiSubstitutor.EMPTY);
    }


    private void findSuperMethodRecursilvely(Set<PsiMethod> methods, PsiClass psiClass, boolean allowStatic, Set<PsiClass> visited, MethodSignature signature, @NotNull Set<MethodSignature> discoveredSupers) {
        if (psiClass == null)
            return;
        if (visited.contains(psiClass))
            return;
        visited.add(psiClass);
        PsiClassType[] superClassTypes = psiClass.getSuperTypes();

        for (PsiClassType superClassType : superClassTypes) {
            PsiClass resolvedSuperClass = superClassType.resolve();

            if (resolvedSuperClass != null) {
                PsiMethod[] superClassMethods = resolvedSuperClass.getMethods();
                HashSet<MethodSignature> supers = new HashSet<MethodSignature>(3);

                for (PsiMethod superClassMethod : superClassMethods) {
                    MethodSignature superMethodSignature = createMethodSignature(superClassMethod);

                    if (FanPsiImplUtil.isExtendsSignature(superMethodSignature, signature) && (
                            allowStatic || !superClassMethod.getModifierList().hasExplicitModifier("static"))) {
                        methods.add(superClassMethod);
                        supers.add(superMethodSignature);
                        discoveredSupers.add(superMethodSignature);
                    }
                }


                findSuperMethodRecursilvely(methods, resolvedSuperClass, allowStatic, visited, signature, discoveredSupers);
                discoveredSupers.removeAll(supers);
            }
        }
    }
}