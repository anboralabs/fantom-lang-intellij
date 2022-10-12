package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;

import java.util.*;


import com.intellij.psi.stubs.StubIndex;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.expressions.*;


import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.impl.*;


import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import org.fandev.utils.FanUtil;
import org.fandev.utils.PsiUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class FanReferenceExpressionImpl extends FanReferenceElementImpl implements FanReferenceExpression {
    private static final OurResolver RESOLVER = new OurResolver();

    private static final Logger logger = Logger.getInstance(FanReferenceExpressionImpl.class.getName());

    public FanReferenceExpressionImpl(StubElement stubElement, @NotNull IStubElementType iStubElementType) {
        super(stubElement, iStubElementType);
    }

    public FanReferenceExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public PsiElement getQualifier() {
        return (PsiElement) this;
    }

    public boolean isReferenceTo(PsiElement psiElement) {
        for (Object obj : getVariants()) {
            if (psiElement.equals(obj)) return true;
        }
        return false;
    }

    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        FanPsiImplUtil.setName(getReferenceNameElement(), name);
        return null;
    }

    public Object[] getVariants() {
        List<PsiElement> result = new ArrayList<PsiElement>();
        FanReferenceExpression qualifier = (FanReferenceExpression) getQualifier();
        if (qualifier != null) {
            ResolveResult[] results = qualifier.multiResolve(true);
            if (results.length > 0) {
                for (ResolveResult tmp : results) {
                    result.add(tmp.getElement());
                }
                return result.toArray((Object[]) new PsiElement[result.size()]);
            }
        }
        return new Object[0];
    }

    public boolean isSoft() {
        return false;
    }

    public String getCanonicalText() {
        return null;
    }


    @NotNull
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        final ResolveCache manager = ResolveCache.getInstance(getProject());
        return manager.resolveWithCaching(this, RESOLVER, false, incompleteCode);
    }

    public PsiElement resolve() {
        final ResolveCache manager = ResolveCache.getInstance(getProject());
        ResolveResult[] results = manager.resolveWithCaching(this, RESOLVER, false, false);
        return (results.length == 1) ? results[0].getElement() : null;
    }

    public FanExpression getQualifierExpression() {
        return (FanExpression) findChildByClass(FanExpression.class);
    }

    @NotNull
    public FanResolveResult[] getSameNameVariants() {
        return RESOLVER.resolve(this, true);
    }

    private static class OurResolver implements ResolveCache.PolyVariantResolver<FanReferenceExpressionImpl> {
        private OurResolver() {
        }

        public FanResolveResult[] resolve(FanReferenceExpressionImpl refExpr, boolean incompleteCode) {
            List<FanResolveResult> results = new ArrayList<FanResolveResult>();

            ResolveHint hint = new ResolveHint(refExpr);

            FanTypeDefinition typeDefinition = null;
            FanClassReferenceType referenceType = null;

            if (hint.isSuperRefrenceExpression() || hint.isThisRefrenceExpression()) {

                if (hint.gotoNonFieldOrMethodDecleration()) return FanResolveResult.EMPTY_ARRAY;

                typeDefinition = ((FanTypeReferenceExpression) hint.idElement).getReferencedType();
            } else if (hint.someTypeFieldOrMethodRef()) {
                typeDefinition = resolveTypeDefinition(hint, typeDefinition);
            } else {

                if (hint.containingTypeFieldOrMethodRef()) {
                    PsiElement maybeClazz = PsiTreeUtil.getParentOfType((PsiElement) refExpr, new Class[]{FanTypeDefinition.class, FanFile.class});
                    if (FanUtil.isFanType(maybeClazz)) {
                        resolveByType(results, hint, (FanTypeDefinition) maybeClazz);
                    }
                }

                PsiElement containingBlock = refExpr.getParent();
                while (!FanUtil.isFanFile(containingBlock)) {
                    if (FanUtil.isFanClosure(containingBlock)) {
                        FanFormals fanFormals = ((FanClosureExpression) containingBlock).getFunction().getFormals();
                        String toMatch = stripRefElement(hint.idElement);
                        for (PsiParameter formal : fanFormals.getParameters()) {
                            if (toMatch.equals(formal.getName())) {
                                if (hint.gotoNonFieldOrMethodDecleration()) {
                                    return new FanResolveResult[]{(FanResolveResult) new FanResolveResultImpl((PsiElement) formal, true)};
                                }
                                referenceType = (FanClassReferenceType) formal.getType();
                                break;
                            }
                            if (hint.completeIdentifier() && formal.getName().startsWith(toMatch)) {
                                results.add(new FanResolveResultImpl((PsiElement) formal, true));
                            }
                        }
                    } else if (FanUtil.isFanMethod(containingBlock)) {
                        PsiParameterList params = ((FanMethod) containingBlock).getParameterList();
                        for (PsiParameter param : params.getParameters()) {
                            String elementText = hint.idElement.getText();
                            if (elementText.indexOf("(") != -1) {
                                elementText = elementText.substring(0, elementText.indexOf("("));
                            }
                            if (param.getName().equals(elementText)) {
                                if (hint.gotoNonFieldOrMethodDecleration()) {
                                    return new FanResolveResult[]{(FanResolveResult) new FanResolveResultImpl((PsiElement) param, true)};
                                }
                                referenceType = (FanClassReferenceType) param.getType();

                                break;
                            }
                        }
                    } else if (FanUtil.isPsiCodeBlock(containingBlock)) {


                        if (hint.gotoDecleration && !hint.fieldOrMethodeRef && !hint.isMethodRef) {
                            List<PsiVariable> variables = findVariablesByName(hint.idElement, containingBlock, true);

                            if (variables.size() > 0) {
                                return new FanResolveResult[]{(FanResolveResult) new FanResolveResultImpl((PsiElement) variables.get(0), true)};
                            }
                        } else if (hint.gotoDecleration && !hint.fieldOrMethodeRef && hint.isMethodRef) {

                            List<PsiVariable> variables = findVariablesByName(hint.idElement, containingBlock, true);
                            if (variables.size() > 0) {
                                PsiType type = ((PsiVariable) variables.get(0)).getType();
                                if (type instanceof FanFuncType) {
                                    PsiType returnType = ((FanFuncType) type).getReturnType();
                                    return new FanResolveResult[]{(FanResolveResult) new FanResolveResultImpl((PsiElement) ((FanClassReferenceType) returnType).resolveFanType(), true)};
                                }

                            }
                        } else if (!hint.gotoDecleration && !hint.fieldOrMethodeRef) {
                            List<PsiVariable> variables = findVariablesByName(hint.idElement, containingBlock, false);
                            if (variables.size() > 0) {
                                results.addAll(Arrays.asList(createResults(refExpr, (PsiElement[]) variables.toArray((Object[]) new PsiVariable[0]))));
                            }
                        } else {

                            List<PsiVariable> variables = findVariablesByName(hint.idElement, containingBlock, true);
                            if (variables.size() > 0) {
                                PsiType type = ((PsiVariable) variables.get(0)).getType();
                                if (type instanceof FanClassReferenceType) {
                                    referenceType = (FanClassReferenceType) type;
                                }
                                break;
                            }
                        }
                    } else if (FanUtil.isFanType(containingBlock)) {

                        FanTypeDefinition myClass = (FanTypeDefinition) containingBlock;
                        while (myClass instanceof com.intellij.psi.PsiClass) {
                            if (hint.isMethodRef && !hint.fieldOrMethodeRef) {
                                FanMethod method = myClass.getMethodByName(hint.toMatch);
                                if (method != null && hint.gotoDecleration) {
                                    return createResults(refExpr, new PsiElement[]{(PsiElement) method});
                                }
                            } else {
                                FanField field = myClass.getFieldByName(hint.idElement.getText());
                                if (field != null) {

                                    if (hint.gotoNonFieldOrMethodDecleration()) {
                                        return createResults(refExpr, new PsiElement[]{(PsiElement) field});
                                    }

                                    referenceType = (FanClassReferenceType) field.getType();
                                    break;
                                }
                                FanMethod method = myClass.getMethodByName(hint.toMatch);
                                if (method != null && hint.gotoDecleration) {
                                    return createResults(refExpr, new PsiElement[]{(PsiElement) method});
                                }
                            }

                            myClass = myClass.getSuperType();
                        }
                    }
                    containingBlock = containingBlock.getParent();
                }

                if (referenceType != null) {
                    typeDefinition = referenceType.resolveFanType();
                }
            }

            if (typeDefinition != null) {
                resolveByType(results, hint, typeDefinition);
            } else if (referenceType != null) {
                FanReferenceExpressionImpl.logger.warn("Could not resolve type " + referenceType.getPresentableText());
            } else if (results.size() == 0 && !hint.fieldOrMethodeRef) {
                resolveTypes(results, hint);
            }

            return results.<FanResolveResult>toArray(new FanResolveResult[0]);
        }

        private FanTypeDefinition resolveTypeDefinition(ResolveHint hint, FanTypeDefinition typeDefinition) {
            if (!(hint.idElement instanceof FanReferenceExpression)) {
                throw new IllegalArgumentException("hint idElement must be instance of FanReferenceExpression");
            }
            PsiElement typeProvider = ((FanReferenceExpression) hint.idElement).resolve();
            PsiType type = null;
            if (FanUtil.isFanMethod(typeProvider)) {
                type = ((FanMethod) typeProvider).getReturnType();
            } else if (FanUtil.isFanField(typeProvider)) {
                type = ((FanField) typeProvider).getType();
            } else if (FanUtil.isFanVariable(typeProvider)) {
                type = ((FanVariable) typeProvider).getType();
            } else if (FanUtil.isFanTypeDefinition(typeProvider)) {

                if (!hint.idElement.getText().contains("(")) {
                    hint.isStatic = true;
                }
                typeDefinition = (FanTypeDefinition) typeProvider;
            }

            if (type instanceof FanClassReferenceType) {
                typeDefinition = ((FanClassReferenceType) type).resolveFanType();
            } else if (type instanceof FanFuncType) {

                if (hint.idElement.getText().contains("(")) {
                    PsiType returnType = ((FanFuncType) type).getReturnType();
                    typeDefinition = ((FanClassReferenceType) returnType).resolveFanType();
                } else {

                    typeDefinition = ((FanFuncType) type).getFuncType();
                }
            } else if (FanUtil.isFanMapType(type)) {
                typeDefinition = ((FanMapType) type).getMapType();
            } else if (FanUtil.isFanListType(type)) {
                if (hint.isIndex) {
                    typeDefinition = (FanTypeDefinition) ((FanListReferenceType) type).getTypeElement().getReferenceElement().resolve();
                } else {
                    typeDefinition = ((FanListReferenceType) type).getListType();
                }
            }
            return typeDefinition;
        }

        private FanResolveResult[] createResults(FanReferenceExpressionImpl refExpr, PsiElement... element) {
            List<FanResolveResult> results = new ArrayList<FanResolveResult>();
            for (PsiElement elem : element) {
                boolean isAccessible = true;
                if (PsiMember.class.isAssignableFrom(elem.getClass())) {
                    isAccessible = PsiUtil.isAccessible((PsiElement) refExpr, (PsiMember) elem);
                }
                results.add(new FanResolveResultImpl(elem, isAccessible));
            }
            return results.<FanResolveResult>toArray(new FanResolveResult[0]);
        }


        private void resolveByType(List<FanResolveResult> results, ResolveHint hint, FanTypeDefinition typeDefinition) {
            FanReferenceExpressionImpl context = hint.refExpr;

            boolean isSuperType = false;
            while (typeDefinition != null) {
                FanMethod[] methods = hint.isStatic ? typeDefinition.getFanMethods("static") : typeDefinition.getFanMethods();
                for (FanMethod fanMethod : methods) {
                    if ((!hint.gotoDecleration && fanMethod.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && fanMethod.getName().equals(hint.toMatch))) {
                        if (!isSuperType || !(fanMethod instanceof org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor)) {

                            boolean isAccessible = PsiUtil.isAccessible((PsiElement) context, (PsiMember) fanMethod);
                            results.add(new FanResolveResultImpl((PsiElement) fanMethod, isAccessible));
                        }
                    }
                }
                if (!hint.isMethodRef) {
                    FanField[] fields = hint.isStatic ? typeDefinition.getFanFields("static") : typeDefinition.getFanFields();
                    for (FanField fanField : fields) {
                        if ((!hint.gotoDecleration && fanField.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && fanField.getName().equals(hint.toMatch))) {

                            boolean isAccessible = PsiUtil.isAccessible((PsiElement) context, (PsiMember) fanField);
                            results.add(new FanResolveResultImpl((PsiElement) fanField, isAccessible));
                        }
                    }
                }
                if (hint.isStatic && FanUtil.isFanEnumDefinition((PsiElement) typeDefinition)) {
                    FanEnumValue[] enumValues = ((FanEnumDefinition) typeDefinition).getEnumValues();
                    for (FanEnumValue enumValue : enumValues) {
                        if ((!hint.gotoDecleration && enumValue.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && enumValue.getName().equals(hint.toMatch))) {

                            boolean isAccessible = PsiUtil.isAccessible((PsiElement) context, (PsiMember) enumValue);
                            results.add(new FanResolveResultImpl((PsiElement) enumValue, isAccessible));
                        }
                    }
                }
                typeDefinition = (FanTypeDefinition) typeDefinition.getSuperClass();
                isSuperType = true;
            }
        }

        private List<PsiVariable> findVariablesByName(PsiElement idElement, PsiElement containingBlock, boolean exectMatch) {
            List<PsiVariable> variables = new ArrayList<PsiVariable>();
            String toMatch = stripRefElement(idElement);
            for (PsiElement psiElement : containingBlock.getChildren()) {
                if (psiElement instanceof PsiVariable) {
                    String name = ((PsiVariable) psiElement).getName();
                    if (name != null) {
                        if (name.equals(toMatch)) {
                            if (variables.size() > 0) {
                                variables.set(0, (PsiVariable) psiElement);
                            } else {
                                variables.add((PsiVariable) psiElement);
                            }
                        } else if (name.startsWith(toMatch) && !exectMatch) {
                            variables.add((PsiVariable) psiElement);
                        }
                    }
                }
            }
            return variables;
        }

        private String stripRefElement(PsiElement idElement) {
            int idx = idElement.getText().indexOf("IntellijIdeaRulezzz");
            int idx2 = idElement.getText().indexOf("(");
            if (idx2 >= 0 && idx2 > idx) idx = idx2;

            String toMatch = (idx >= 0) ? idElement.getText().substring(0, idx) : idElement.getText();
            return toMatch;
        }

        private void resolvePodTypes(FanReferenceExpressionImpl refExpr, String podName, String toMatch, boolean gotoDecleration, List<FanResolveResult> results) {
            FanIndex index = (FanIndex) refExpr.getProject().getService(FanIndex.class);

            Set<FanTypeDefinition> typesStartingWith = index.getPodTypesStartingWith(podName, toMatch);
            for (FanTypeDefinition def : typesStartingWith) {
                if (!gotoDecleration || (gotoDecleration && def.getName().equals(refExpr.getText()))) {
                    boolean isAccessible = PsiUtil.isAccessible((PsiElement) refExpr, (PsiMember) def);
                    results.add(new FanResolveResultImpl((PsiElement) def, isAccessible));
                }
            }
        }

        private void resolveTypes(List<FanResolveResult> results, ResolveHint hint) {
            FanIndex index = (FanIndex) hint.refExpr.getProject().getService(FanIndex.class);;


            if (!hint.isFqn()) {
                Set<FanTypeDefinition> podDefinitions = index.getPodStartingWith(hint.toMatch);
                for (FanTypeDefinition def : podDefinitions) {
                    if (!hint.gotoDecleration || (hint.gotoDecleration && def.getName().equals(hint.refExpr.getText()))) {
                        results.add(new FanResolveResultImpl((PsiElement) def, true));
                    }
                }
            }


            Collection<FanTypeDefinition> typesStartingWith = StubIndex.getInstance().get(FanShortClassNameIndex.KEY, hint.toMatch, hint.refExpr.getProject(), null);


            if (hint.isFqn()) {
                String podName = ((PodReferenceExpression) hint.idElement).getPodName();
                typesStartingWith.addAll(index.getPodTypesStartingWith(podName, hint.toMatch));
            } else {
                typesStartingWith.addAll(index.getAllTypesStartingWith(hint.toMatch));
            }

            for (FanTypeDefinition def : typesStartingWith) {
                if (!hint.gotoDecleration || (hint.gotoDecleration && def.getName().equals(hint.refExpr.getText()))) {
                    boolean isAccessible = PsiUtil.isAccessible((PsiElement) hint.refExpr, (PsiMember) def);
                    results.add(new FanResolveResultImpl((PsiElement) def, isAccessible));
                }
            }
        }
    }


    private static class ResolveHint {
        final boolean gotoDecleration;
        final boolean isMethodRef;
        final boolean fieldOrMethodeRef;
        final boolean isIndex;
        boolean isStatic;
        final String toMatch;
        final PsiElement idElement;
        final FanReferenceExpressionImpl refExpr;

        ResolveHint(FanReferenceExpressionImpl refExpr) {
            PsiElement psiElement1 = null;
            int idx = refExpr.getText().indexOf("IntellijIdeaRulezzz");

            this.gotoDecleration = (idx == -1);
            String toMatch = (idx >= 0) ? refExpr.getText().substring(0, idx) : refExpr.getText();
            this.isMethodRef = toMatch.contains("(");
            if (this.isMethodRef) {
                toMatch = toMatch.substring(0, toMatch.indexOf("("));
            }

            PsiElement parent = refExpr.getParent();

            FanReferenceExpressionImpl fanReferenceExpressionImpl = refExpr;
            int index = -1;
            int idxExpIndex = -1;
            for (PsiElement child : parent.getChildren()) {
                index++;
                if (child == refExpr)
                    break;
                if (FanUtil.isOfType(child, FanIndexExpression.class)) {
                    idxExpIndex = index;
                } else {

                    psiElement1 = child;
                }
            }
            this.idElement = (psiElement1 instanceof FanReferenceExpression || psiElement1 instanceof FanTypeReferenceExpression || psiElement1 instanceof PodReferenceExpression) ? psiElement1 : (PsiElement) refExpr;


            this.fieldOrMethodeRef = (!refExpr.equals(this.idElement) && !FanUtil.isOfType(this.idElement, PodReferenceExpression.class));
            this.isIndex = (index == idxExpIndex + 1);

            this.toMatch = toMatch;
            this.refExpr = refExpr;
            this.isStatic = false;
        }

        boolean isThisRefrenceExpression() {
            return this.idElement instanceof org.fandev.lang.fan.psi.api.statements.expressions.FanThisReferenceExpression;
        }

        boolean isSuperRefrenceExpression() {
            return this.idElement instanceof org.fandev.lang.fan.psi.api.statements.expressions.FanSuperReferenceExpression;
        }

        boolean gotoNonFieldOrMethodDecleration() {
            return (this.gotoDecleration && !this.fieldOrMethodeRef);
        }

        boolean completeIdentifier() {
            return (!this.gotoDecleration && !this.fieldOrMethodeRef);
        }


        boolean someTypeFieldOrMethodRef() {
            return (this.idElement instanceof FanReferenceExpression && this.fieldOrMethodeRef);
        }

        boolean containingTypeFieldOrMethodRef() {
            return (this.idElement instanceof FanReferenceExpression && !this.fieldOrMethodeRef);
        }

        boolean isFqn() {
            return FanUtil.isOfType(this.idElement, PodReferenceExpression.class);
        }
    }
}