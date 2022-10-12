package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.*;


import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.Icon;

import com.intellij.util.IncorrectOperationException;
import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanPodDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.utils.FanUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FanPodDefinitionImpl extends FanTypeDefinitionImpl implements FanPodDefinition {
    public FanPodDefinitionImpl(FanTypeDefinitionStub stubElement) {
        super(stubElement, (IStubElementType) FanElementTypes.POD_DEFINITION);
    }

    FanField[] fanFields;

    public FanPodDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Pod definition";
    }


    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        return (PsiElement) this;
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
        this.fanFields = null;
        super.subtreeChanged();
    }

    @NotNull
    public PsiField[] getFields() {
        return (PsiField[]) getFanFields();
    }


    @NotNull
    public FanField[] getFanFields() {
        if (this.fanFields == null) {
            List<FanField> list = new ArrayList<FanField>();
            PsiElement element = findChildByType(getBodyElementType());
            if (element != null) {
                PsiElement[] bodyEls = element.getChildren();
                for (PsiElement bodyEl : bodyEls) {
                    if (FanUtil.isFanField(bodyEl)) {
                        list.add((FanField) bodyEl);
                    }
                }
            }
            this.fanFields = list.<FanField>toArray(new FanField[list.size()]);
        }
        return this.fanFields;
    }

    @NotNull
    public PsiMethod[] getMethods() {
        return (PsiMethod[]) FanMethod.EMPTY_ARRAY;
    }

    @NotNull
    public FanMethod[] getFanMethods() {
        return FanMethod.EMPTY_ARRAY;
    }

    @NotNull
    public FanSlot[] getSlots() {
        return (FanSlot[]) getFanFields();
    }

    @NotNull
    public PsiMethod[] getConstructors() {
        return PsiMethod.EMPTY_ARRAY;
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
        return PsiMethod.EMPTY_ARRAY;
    }

    @NotNull
    public PsiClass[] getAllInnerClasses() {
        return PsiClass.EMPTY_ARRAY;
    }

    public PsiField findFieldByName(@NonNls String name, boolean checkBases) {
        return null;
    }

    public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
        return null;
    }

    @NotNull
    public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
        return PsiMethod.EMPTY_ARRAY;
    }

    @NotNull
    public PsiMethod[] findMethodsByName(@NonNls String name, boolean checkBases) {
        return PsiMethod.EMPTY_ARRAY;
    }


    @NotNull
    public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String name, boolean checkBases) {
        return null;
    }

    @NotNull
    public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
        return null;
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
        return null;
    }

    public boolean isInheritor(@NotNull PsiClass baseClass, boolean checkDeep) {
        return false;
    }

    public boolean isInheritorDeep(PsiClass baseClass, @Nullable PsiClass classToByPass) {
        return false;
    }

    public PsiClass getContainingClass() {
        return null;
    }

    @NotNull
    public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
        return null;
    }

    public PsiDocComment getDocComment() {
        return null;
    }

    public boolean isDeprecated() {
        return false;
    }

    public boolean hasTypeParameters() {
        return false;
    }

    public PsiTypeParameterList getTypeParameterList() {
        return null;
    }

    @NotNull
    public PsiTypeParameter[] getTypeParameters() {
        return PsiTypeParameter.EMPTY_ARRAY;
    }


    protected Icon getIconInner() {
        return Icons.POD;
    }

    protected IElementType getBodyElementType() {
        return FanElementTypes.POD_BODY;
    }


    public boolean hasModifierProperty(String name) {
        return false;
    }
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/impl/statements/typedefs/FanPodDefinitionImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */