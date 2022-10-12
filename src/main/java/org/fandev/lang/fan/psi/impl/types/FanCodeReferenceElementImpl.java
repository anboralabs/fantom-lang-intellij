package org.fandev.lang.fan.psi.impl.types;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;


import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;

import java.util.ArrayList;
import java.util.List;

import com.intellij.psi.util.PsiUtil;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.FantomFileType;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.impl.FanReferenceElementImpl;
import org.fandev.lang.fan.psi.impl.FanResolveResultImpl;
import org.jetbrains.annotations.NotNull;

public class FanCodeReferenceElementImpl extends FanReferenceElementImpl implements FanCodeReferenceElement {
    private static final FanResolver RESOLVER = new FanResolver();

    public FanCodeReferenceElementImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
        super(stubElement, iStubElementType);
    }

    public FanCodeReferenceElementImpl(ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Reference element";
    }

    public PsiElement getQualifier() {
        return (PsiElement) this;
    }

    public PsiElement resolve() {
        final ResolveCache manager = ResolveCache.getInstance(getProject());
        ResolveResult[] results = manager.resolveWithCaching(this, RESOLVER, false, false);
        return (results.length == 1) ? results[0].getElement() : null;
    }

    public String getCanonicalText() {
        PsiElement resolved = resolve();
        if (resolved instanceof PsiClass) {
            return ((PsiClass) resolved).getQualifiedName();
        }
        if (resolved instanceof PsiPackage) {
            return ((PsiPackage) resolved).getQualifiedName();
        }
        return null;
    }

    public boolean isReferenceTo(PsiElement psiElement) {
        return getManager().areElementsEquivalent(psiElement, resolve());
    }

    public Object[] getVariants() {
        FanCodeReferenceElement qualifier = (FanCodeReferenceElement) getQualifier();
        if (qualifier != null) {
            PsiElement resolve = qualifier.resolve();
            if (resolve instanceof PsiClass) {
                PsiClass clazz = (PsiClass) resolve;
                List<PsiElement> result = new ArrayList<PsiElement>();

                for (PsiField field : clazz.getFields()) {
                    if (field.hasModifierProperty("static")) {
                        result.add(field);
                    }
                }

                for (PsiMethod method : clazz.getMethods()) {
                    if (method.hasModifierProperty("static")) {
                        result.add(method);
                    }
                }

                return result.toArray((Object[]) new PsiElement[result.size()]);
            }
        }
        return new Object[0];
    }

    public boolean isSoft() {
        return false;
    }

    @NotNull
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final ResolveCache manager = ResolveCache.getInstance(getProject());
        return manager.resolveWithCaching(this, RESOLVER, false, incompleteCode);
    }

    private static class FanResolver implements ResolveCache.PolyVariantResolver<FanCodeReferenceElementImpl> {
        private FanResolver() {
        }

        public ResolveResult[] resolve(FanCodeReferenceElementImpl fanCodeReferenceElement, boolean incompleteCode) {
            if (fanCodeReferenceElement.getReferenceName() == null)
                return (ResolveResult[]) FanResolveResult.EMPTY_ARRAY;

            FanResolveResult[] results = _resolve(fanCodeReferenceElement, (PsiManager) fanCodeReferenceElement.getManager());

            return (ResolveResult[]) results;
        }


        private FanResolveResult[] _resolve(final FanCodeReferenceElementImpl ref, final PsiManager manager) {
            final String refName = ref.getReferenceName();
            FanCodeReferenceElement qualifier = (FanCodeReferenceElement) ref.getQualifier();
            if (qualifier != null) {
                final List<FanResolveResult> results = new ArrayList<FanResolveResult>();
                ProjectRootManager.getInstance(manager.getProject()).getFileIndex().iterateContent(new ContentIterator() {
                    public boolean processFile(VirtualFile virtualFile) {
                        if (FantomFileType.INSTANCE == virtualFile.getFileType()) {
                            FanFile psiFile = (FanFile) manager.findFile(virtualFile);
                            PsiClass[] classes = psiFile.getClasses();
                            for (PsiClass aClass : classes) {
                                if (refName.equals(aClass.getName())) {
                                    boolean isAccessible = PsiUtil.isAccessible(aClass, ref, aClass);
                                    results.add(new FanResolveResultImpl((PsiElement) aClass, isAccessible));
                                }
                            }
                        }
                        return true;
                    }
                });

                if (results.size() > 0) {
                    return results.<FanResolveResult>toArray(new FanResolveResult[0]);
                }

                FanIndex fanIndex = (FanIndex) manager.getProject().getService(FanIndex.class);
                FanFile fanFile = fanIndex.getFanFileByTypeName(refName);
                if (fanFile != null) {
                    FanFile psiFile = fanFile;
                    PsiClass[] classes = psiFile.getClasses();
                    for (PsiClass aClass : classes) {
                        try {
                            if (refName.equals(aClass.getName())) {
                                boolean isAccessible = PsiUtil.isAccessible((PsiMember) aClass, (PsiElement) ref, null);
                                results.add(new FanResolveResultImpl((PsiElement) aClass, isAccessible));
                            }
                        } catch (Exception e) {
                        }
                    }
                }


                return results.<FanResolveResult>toArray(new FanResolveResult[0]);
            }
            return FanResolveResult.EMPTY_ARRAY;
        }
    }
}