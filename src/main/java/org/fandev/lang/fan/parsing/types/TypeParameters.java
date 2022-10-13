package org.fandev.lang.fan.parsing.types;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class TypeParameters {
    public static IElementType parse(PsiBuilder builder) {
        if (FanTokenTypes.LPAR == builder.getTokenType()) {
            PsiBuilder.Marker marker = builder.mark();
            ParserUtils.getToken(builder, FanTokenTypes.LPAR);
            ParserUtils.removeNls(builder);
            if (!ParserUtils.getToken(builder, FanTokenTypes.RPAR)) {
                while (parseTypeParameter(builder) != FanElementTypes.WRONGWAY &&
                        ParserUtils.getToken(builder, FanTokenTypes.COMMA)) {

                    eatCommas(builder);
                }
                eatCommas(builder);
                if (!ParserUtils.getToken(builder, FanTokenTypes.RPAR)) {
                    builder.error(FanBundle.message("rpar.expected", new Object[0]));
                    ParserUtils.cleanAfterErrorInArguments(builder);
                }
            }
            ParserUtils.removeNls(builder);
            marker.done(FanElementTypes.TYPE_PARAMETER_LIST);
            return FanElementTypes.TYPE_PARAMETER_LIST;
        }

        return FanElementTypes.WRONGWAY;
    }

    private static void eatCommas(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        while (FanTokenTypes.COMMA == builder.getTokenType()) {
            builder.error(FanBundle.message("type.parameter.expected", new Object[0]));
            ParserUtils.getToken(builder, FanTokenTypes.COMMA);
            ParserUtils.removeNls(builder);
        }
    }


    private static IElementType parseTypeParameter(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        if (TypeSpec.parse(builder)) {
            if (ParserUtils.parseName(builder)) {
                ParserUtils.removeNls(builder);
                if (FanTokenTypes.COLON_EQ.equals(builder.getTokenType())) {

                    ParserUtils.advanceNoNls(builder);
                    Expression.parseExpr(builder, Arguments.ARGUMENTS_STOPPER, FanElementTypes.PARAM_DEFAULT);
                }
                marker.done(FanElementTypes.TYPE_PARAMETER);
                return FanElementTypes.TYPE_PARAMETER;
            }
        }
        marker.error(FanBundle.message("type.parameter.expected", new Object[0]));
        return FanElementTypes.WRONGWAY;
    }
}
