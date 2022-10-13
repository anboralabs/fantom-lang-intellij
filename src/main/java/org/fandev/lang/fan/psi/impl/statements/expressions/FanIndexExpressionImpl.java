package org.fandev.lang.fan.psi.impl.statements.expressions;

import com.intellij.lang.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanIndexExpression;


public class FanIndexExpressionImpl
        extends FanExpressionImpl
        implements FanIndexExpression {
    public FanIndexExpressionImpl(ASTNode astNode) {
        super(astNode);
    }

    public int getIndex() {
        return Integer.valueOf(getText()).intValue();
    }
}