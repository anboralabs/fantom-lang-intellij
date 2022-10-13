package org.fandev.lang.fan.parsing.auxiliary;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanTokenTypes;


public class Separators {
    public static boolean parse(PsiBuilder builder) {
        boolean result = false;
        while (!builder.eof() && FanTokenTypes.SEPARATOR.contains(builder.getTokenType())) {
            builder.advanceLexer();
            result = true;
        }
        return result;
    }
}