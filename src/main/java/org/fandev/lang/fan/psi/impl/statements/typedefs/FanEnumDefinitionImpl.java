package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.HierarchicalMethodSignature;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassInitializer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiTypeParameterList;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.Icon;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FanEnumDefinitionImpl
        extends FanTypeDefinitionImpl
        implements FanEnumDefinition {
    FanEnumValue[] fanEnumValues;

    public FanEnumDefinitionImpl(FanTypeDefinitionStub stubElement) {
        super(stubElement, FanElementTypes.CLASS_DEFINITION);
    }

    public FanEnumDefinitionImpl(ASTNode astNode) {
        super(astNode);
    }

    public String toString() {
        return "Enum definition";
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
        this.fanSlots = null;
        this.fanFields = null;
        this.fanMethods = null;
        super.subtreeChanged();
    }

    @NotNull
    public PsiField[] getFields() {
        return getFanFields();
    }

    public FanEnumValue[] getEnumValues() {
        if (this.fanEnumValues == null) {
            PsiElement element = findChildByType(FanElementTypes.ENUM_BODY);
            List<FanEnumValue> list = new ArrayList<>();
            if (element != null) {
                PsiElement[] bodyEls = element.getChildren();
                for (PsiElement bodyEl : bodyEls) {
                    if (bodyEl instanceof FanEnumValue) {
                        list.add((FanEnumValue) bodyEl);
                    }
                }
            }
            this.fanEnumValues = list.toArray(new FanEnumValue[list.size()]);
        }
        return this.fanEnumValues;
    }

    @NotNull
    public PsiMethod[] getMethods() {
        return getFanMethods();
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
        return Icons.ENUM;
    }

    protected IElementType getBodyElementType() {
        return FanElementTypes.ENUM_BODY;
    }
}