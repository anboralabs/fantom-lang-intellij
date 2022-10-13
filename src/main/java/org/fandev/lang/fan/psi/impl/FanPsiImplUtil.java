package org.fandev.lang.fan.psi.impl;

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.TypeConversionUtil;
import com.intellij.util.IncorrectOperationException;

import java.util.List;

import org.fandev.lang.fan.psi.FanPsiElementFactory;
import org.jetbrains.annotations.Nullable;


public class FanPsiImplUtil {
    public static PsiMethod[] mapToMethods(@Nullable List<CandidateInfo> list) {
        if (list == null) return PsiMethod.EMPTY_ARRAY;
        PsiMethod[] result = new PsiMethod[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = (PsiMethod) list.get(i).getElement();
        }

        return result;
    }

    public static boolean isExtendsSignature(MethodSignature superSignatureCandidate, MethodSignature subSignature) {
        String name1 = superSignatureCandidate.getName();
        String name2 = subSignature.getName();
        if (!name1.equals(name2)) return false;

        PsiType[] superTypes = superSignatureCandidate.getParameterTypes();
        PsiType[] subTypes = subSignature.getParameterTypes();
        if (subTypes.length != superTypes.length) return false;
        for (int i = 0; i < subTypes.length - 1; i++) {
            PsiType superType = TypeConversionUtil.erasure(superTypes[i]);
            PsiType subType = TypeConversionUtil.erasure(subTypes[i]);
            if (!superType.isAssignableFrom(subType)) return false;

        }
        if (superTypes.length > 0) {
            PsiType lastSuperType = TypeConversionUtil.erasure(superTypes[superTypes.length - 1]);
            PsiType lastSubType = TypeConversionUtil.erasure(subTypes[superTypes.length - 1]);
            if (lastSuperType instanceof PsiArrayType && !(lastSubType instanceof PsiArrayType)) {
                PsiType componentType = ((PsiArrayType) lastSuperType).getComponentType();
                if (!lastSubType.isConvertibleFrom(componentType)) return false;
            } else if (!lastSuperType.isAssignableFrom(lastSubType)) {
                return false;
            }

        }

        return true;
    }

    public static PsiElement setName(PsiElement element, String name) throws IncorrectOperationException {
        PsiManager manager = element.getManager();
        FanPsiElementFactory factory = FanPsiElementFactory.getInstance(manager.getProject());
        PsiIdentifier newNameIdentifier = factory.createIdentifier(manager, element.getContainingFile(), name);
        return element.replace(newNameIdentifier);
    }

    public static PsiIdentifier getFanIdentifier(PsiElement element) {
        if (element != null) {
            if (element instanceof FanIdentifierImpl) {
                return (FanIdentifierImpl) element;
            }
            if (element instanceof org.fandev.lang.fan.psi.impl.synthetic.FanLightIdentifier) {
                return (PsiIdentifier) element;
            }
            return new FanIdentifierImpl(element.getNode());
        }
        return null;
    }
}