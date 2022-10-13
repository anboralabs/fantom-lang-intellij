package org.fandev.lang.fan.parsing.statements;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Block {
    public static boolean parse(PsiBuilder builder, IElementType statementType) {
        PsiBuilder.Marker m = builder.mark();
        if (ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
            ParserUtils.removeNls(builder);
            while (!builder.eof() && !FanTokenTypes.RBRACE.equals(builder.getTokenType())) {
                if (!Statement.parse(builder, (statementType == FanElementTypes.WITH_BLOCK_EXPR))) {

                    builder.error(FanBundle.message("rcurly.expected", new Object[0]));
                    ParserUtils.advanceNoNls(builder);
                    continue;
                }
                ParserUtils.removeNls(builder);
            }

            ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]));
            m.done(statementType);
            return true;
        }

        if (Statement.parse(builder)) {
            m.done(statementType);
            return true;
        }
        m.drop();
        return false;
    }
}