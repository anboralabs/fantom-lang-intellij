package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.arithmetic.ShiftExpression;


public class BitAndExpression
        extends SeparatorRepeatExpression {
    private static final BitAndExpression instance = new BitAndExpression();

    public BitAndExpression() {
        super(FanElementTypes.BIT_AND_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.AND}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return ShiftExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}