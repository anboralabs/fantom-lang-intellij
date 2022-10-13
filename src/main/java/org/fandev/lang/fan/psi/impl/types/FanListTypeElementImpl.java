package org.fandev.lang.fan.psi.impl.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanListReferenceType;
import org.jetbrains.annotations.NotNull;


public class FanListTypeElementImpl
        extends FanBaseElementImpl
        implements FanListTypeElement {
    public FanListTypeElementImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiType getType() {
        FanClassTypeElement fanTypeElem = getTypeElement();
        return new FanListReferenceType(this, fanTypeElem.getType());
    }

    @NotNull
    public FanClassTypeElement getTypeElement() {
        return findChildByClass(FanClassTypeElement.class);
    }

    public FanTypeDefinition getListType() {
        return getFanTypeByName("List");
    }
}