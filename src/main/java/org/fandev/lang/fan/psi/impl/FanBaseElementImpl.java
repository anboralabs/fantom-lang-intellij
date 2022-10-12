package org.fandev.lang.fan.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.FantomLanguage;
import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.NotNull;


public class FanBaseElementImpl<T extends StubElement>
        extends StubBasedPsiElementBase<T>
        implements FanElement {
    public FanBaseElementImpl(T t, @NotNull IStubElementType iStubElementType) {
        super(t, iStubElementType);
    }

    public FanBaseElementImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Language getLanguage() {
        return FantomLanguage.INSTANCE;
    }


    public PsiElement getParent() {
        return getParentByStub();
    }

    protected FanTypeDefinition getFanObjType() {
        return getFanTypeByName("Obj");
    }

    protected FanTypeDefinition getVoidType() {
        return getFanTypeByName("Void");
    }

    protected FanTypeDefinition getFanTypeByName(String name) {
        FanIndex index = (FanIndex) getProject().getComponent("Fantom Index");
        FanFile objFile = index.getFanFileByTypeName(name);

        if (objFile != null) {
            FanTypeDefinition[] typeDefinitions = objFile.getTypeDefinitions();
            if (typeDefinitions != null && typeDefinitions.length == 1) {
                return typeDefinitions[0];
            }
        }
        return null;
    }
}