package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinitionBody;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;


public class FanTypeDefinitionBodyImpl
        extends FanBaseElementImpl
        implements FanTypeDefinitionBody {
    public FanTypeDefinitionBodyImpl(ASTNode astNode) {
        super(astNode);
    }
}