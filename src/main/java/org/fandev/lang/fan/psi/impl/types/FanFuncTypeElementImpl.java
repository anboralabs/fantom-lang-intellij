package org.fandev.lang.fan.psi.impl.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanFuncType;
import org.jetbrains.annotations.NotNull;


public class FanFuncTypeElementImpl
        extends FanBaseElementImpl
        implements FanFuncTypeElement {
    public FanFuncTypeElementImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiType getType() {
        return new FanFuncType(this);
    }

    public FanFormals getFormals() {
        return findChildByClass(FanFormals.class);
    }

    public FanTypeElement getReturnType() {
        FanTypeElement returnType = findChildByClass(FanTypeElement.class);
        if (returnType == null) ;


        return returnType;
    }

    public FanTypeDefinition getFuncType() {
        return getFanTypeByName("Func");
    }
}
