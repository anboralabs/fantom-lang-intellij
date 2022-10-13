package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.logical.SeparatorRepeatExpression;


public class ShiftExpression
        extends SeparatorRepeatExpression {
    private static final ShiftExpression instance = new ShiftExpression();

    public ShiftExpression() {
        super(FanElementTypes.SHIFT_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.GTGT, FanTokenTypes.LTLT}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return AdditiveExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}