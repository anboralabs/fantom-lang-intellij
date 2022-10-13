package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;


public class EnumValueDefinition {
    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();


        if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            marker.drop();
            return false;
        }
        PsiBuilder.Marker idMark = builder.mark();
        builder.advanceLexer();
        idMark.done(FanElementTypes.NAME_ELEMENT);


        if (FanTokenTypes.LPAR.equals(builder.getTokenType())) {
            if (Arguments.parse(builder)) {
                marker.done(FanElementTypes.ENUM_VALUE);
                return true;
            }
            marker.error(FanBundle.message("argument.expected", new Object[0]));
            return false;
        }

        marker.done(FanElementTypes.ENUM_VALUE);
        return true;
    }
}