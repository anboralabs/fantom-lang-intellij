package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class EqualityExpression
        extends SeparatorRepeatExpression {
    private static final EqualityExpression instance = new EqualityExpression();

    public EqualityExpression() {
        super(FanElementTypes.EQUALITY_EXPR, FanTokenTypes.EQUALITY_OP);
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return RelationalExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}