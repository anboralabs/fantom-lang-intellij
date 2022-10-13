package org.fandev.lang.fan.psi.impl.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanMapType;
import org.jetbrains.annotations.NotNull;


public class FanMapTypeElementImpl
        extends FanBaseElementImpl
        implements FanMapTypeElement {
    public FanMapTypeElementImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public PsiType getType() {
        FanTypeElement[] keyValueTypes = findChildrenByClass(FanTypeElement.class);
        if (keyValueTypes.length == 2) {
            return new FanMapType(this, keyValueTypes[0], keyValueTypes[1]);
        }
        return null;
    }

    public FanTypeDefinition getMapType() {
        return getFanTypeByName("Map");
    }
}
