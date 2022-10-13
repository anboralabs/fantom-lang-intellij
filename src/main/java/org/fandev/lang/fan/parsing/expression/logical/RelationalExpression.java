package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.types.TypeSpec;


public class RelationalExpression
        extends SeparatorRepeatExpression {
    private static final RelationalExpression instance = new RelationalExpression();

    public RelationalExpression() {
        super(FanElementTypes.RELATIONAL_EXPR, FanTokenTypes.RELATIONAL_OP);
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return ElvisExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }


    protected boolean rheParse(PsiBuilder builder, TokenSet newStopper, IElementType separator) {
        if (FanTokenTypes.TYPE_COMPARE.contains(separator)) {
            return TypeSpec.parse(builder);
        }
        return super.rheParse(builder, newStopper, separator);
    }
}