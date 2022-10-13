package org.fandev.lang.fan.psi.api.statements.typeDefs;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.util.IncorrectOperationException;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.api.topLevel.FanTopStatement;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FanTypeDefinition extends StubBasedPsiElement<FanTypeDefinitionStub>, PsiNamedElement, PsiClass, FanTopLevelDefintion, FanTopStatement {
    @NotNull
    String getPodName();

    @NotNull
    FanSlot[] getSlots();

    @NotNull
    FanSlot[] getSlots(String paramString);

    @NotNull
    FanMethod[] getFanMethods();

    @NotNull
    FanMethod[] getFanMethods(String paramString);

    @NotNull
    FanField[] getFanFields();

    @NotNull
    FanField[] getFanFields(String paramString);

    FanField getFieldByName(String paramString);

    @Nullable
    FanMethod getMethodByName(@NotNull String paramString);

    FanTypeDefinition getSuperType();

    String getJavaQualifiedName();

    PsiElement getBodyElement();

    PsiElement addMemberDeclaration(@NotNull PsiElement paramPsiElement1, PsiElement paramPsiElement2) throws IncorrectOperationException;
}
