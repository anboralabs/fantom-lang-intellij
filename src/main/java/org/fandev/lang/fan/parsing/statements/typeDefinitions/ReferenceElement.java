package org.fandev.lang.fan.parsing.statements.typeDefinitions;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class ReferenceElement {
    public static boolean parseReferenceElement(PsiBuilder builder) {
        PsiBuilder.Marker refelMark = builder.mark();
        boolean res = parse(builder);
        if (res && FanTokenTypes.COLON_COLON == builder.getTokenType()) {

            builder.advanceLexer();
            res = parse(builder);
        }
        if (res) {
            refelMark.done(FanElementTypes.REFERENCE_ELEMENT);
        } else {
            refelMark.drop();
        }
        return res;
    }

    public static boolean parse(PsiBuilder builder) {
        if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        return false;
    }
}