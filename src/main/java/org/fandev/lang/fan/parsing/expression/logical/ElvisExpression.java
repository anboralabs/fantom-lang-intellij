package org.fandev.lang.fan.parsing.expression.logical;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class ElvisExpression
        extends SeparatorRepeatExpression {
    private static final ElvisExpression instance = new ElvisExpression();

    public ElvisExpression() {
        super(FanElementTypes.ELVIS_EXPR, TokenSet.create(new IElementType[]{FanTokenTypes.QUEST_COLON}));
    }

    public boolean innerParse(PsiBuilder builder, TokenSet stopper) {
        return RangeExpression.parse(builder, stopper);
    }

    public static boolean parse(PsiBuilder builder, TokenSet stopper) {
        return instance.parseThis(builder, stopper);
    }
}