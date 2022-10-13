package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanClosureExpression;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;


public class FanClosureExpressionImpl
        extends FanBaseElementImpl
        implements FanClosureExpression {
    public FanClosureExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public FanFuncTypeElement getFunction() {
        return findChildByClass(FanFuncTypeElement.class);
    }
}