package org.fandev.lang.fan.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.util.Key;


public class FanParserContext {
    private static final Key<FanParserContext> KEY = new Key("FanParser");

    public static FanParserContext get(PsiBuilder builder) {
        FanParserContext context = (FanParserContext) builder.getUserData(KEY);
        if (context == null) {
            context = new FanParserContext();
            builder.putUserData(KEY, context);
        }
        return context;
    }
}