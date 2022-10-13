package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class RangeExpression
        extends SeparatorRepeatExpression {
    private static final RangeExpression instance = new RangeExpression();

    public RangeExpression() {
        super(FanElementTypes.RANGE_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.RANGE_SEP_INCL, FanTokenTypes.RANGE_SEP_EXCL}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return BitOrExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}