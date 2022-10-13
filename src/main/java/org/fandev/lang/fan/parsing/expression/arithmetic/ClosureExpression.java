package org.fandev.lang.fan.parsing.expression.arithmetic;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.Block;
import org.fandev.lang.fan.parsing.types.FuncTypeSpec;
import org.fandev.lang.fan.parsing.types.TypeType;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class ClosureExpression {
    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        if (FuncTypeSpec.parseFuncType(builder, false) == TypeType.FUNCTION) {
            ParserUtils.removeNls(builder);
            if (FanTokenTypes.LBRACE == builder.getTokenType()) {
                Block.parse(builder, FanElementTypes.CLOSURE_BODY);
            } else {
                builder.error(FanBundle.message("lcurly.expected", new Object[0]));
            }
            marker.done(FanElementTypes.CLOSURE_EXPR);
            return true;
        }
        marker.rollbackTo();
        return false;
    }
}