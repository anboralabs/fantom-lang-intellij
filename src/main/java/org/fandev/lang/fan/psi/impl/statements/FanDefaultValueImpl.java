package org.fandev.lang.fan.psi.impl.statements;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;


public class FanDefaultValueImpl
        extends FanBaseElementImpl
        implements FanDefaultValue {
    public FanDefaultValueImpl(ASTNode astNode) {
        super(astNode);
    }
}
