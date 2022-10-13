package org.fandev.lang.fan.parsing.statements.expressions.arguments;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Arguments {
    public static final TokenSet ARGUMENTS_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.RPAR, FanTokenTypes.COMMA});

    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        if (FanTokenTypes.LPAR == builder.getTokenType()) {
            ParserUtils.advanceNoNls(builder);
            while (!builder.eof() && FanTokenTypes.RPAR != builder.getTokenType()) {
                boolean res = Expression.parseExpr(builder, ARGUMENTS_STOPPER, FanElementTypes.ARGUMENT_EXPR);
                ParserUtils.removeNls(builder);
                if (!res || FanTokenTypes.RPAR == builder.getTokenType()) {
                    break;
                }
                ParserUtils.getToken(builder, FanTokenTypes.COMMA, FanBundle.message("comma.expected", new Object[0]));
                ParserUtils.removeNls(builder);
            }
            ParserUtils.getToken(builder, FanTokenTypes.RPAR, FanBundle.message("rpar.expected", new Object[0]));
            marker.done(FanElementTypes.ARGUMENT_LIST);
            return true;
        }
        marker.drop();
        return false;
    }
}