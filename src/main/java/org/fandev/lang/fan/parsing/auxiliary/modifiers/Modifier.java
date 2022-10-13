package org.fandev.lang.fan.parsing.auxiliary.modifiers;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Modifier {
    public static boolean parse(PsiBuilder builder, DeclarationType stmtType) {
        if (stmtType.getModifiersSet().contains(builder.getTokenType())) {
            ParserUtils.advanceNoNls(builder);
            return true;
        }
        return false;
    }
}