package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class IdExpression {
    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        boolean symbol = ParserUtils.getToken(builder, FanTokenTypes.AT);

        boolean field = ParserUtils.getToken(builder, FanTokenTypes.MULT);
        if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            PsiBuilder.Marker pod = builder.mark();
            builder.advanceLexer();
            if (builder.getTokenType() == FanTokenTypes.COLON_COLON) {

                pod.done(FanElementTypes.POD_REFERENCE);
                builder.advanceLexer();

                parse(builder);
            } else {
                pod.drop();
            }
            if (!field && !symbol) {
                boolean res = true;
                while (!builder.eof() && res && ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}) == FanTokenTypes.LPAR) {
                    ParserUtils.removeNls(builder);
                    res = Arguments.parse(builder);
                }
                if (ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS}) == FanTokenTypes.OR) {
                    ParserUtils.removeNls(builder);
                    ClosureExpression.parse(builder);
                }
            }
            marker.done(FanElementTypes.ID_EXPR);
            return true;
        }
        marker.rollbackTo();
        return false;
    }
}