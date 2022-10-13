package org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.members.SlotDefinition;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class ClassBlock {
    public static boolean parse(PsiBuilder builder, boolean isBuiltInType) {
        ParserUtils.removeNls(builder);
        PsiBuilder.Marker blockMark = builder.mark();
        if (!ParserUtils.getToken(builder, FanTokenTypes.LBRACE)) {
            blockMark.error(FanBundle.message("lcurly.expected", new Object[0]));
            return false;
        }
        ParserUtils.removeNls(builder);
        while (!builder.eof() && builder.getTokenType() != FanTokenTypes.RBRACE &&
                SlotDefinition.parse(builder, DeclarationType.CLASS, isBuiltInType)) {

            ParserUtils.removeNls(builder);
        }
        if (ParserUtils.getToken(builder, FanTokenTypes.RBRACE, FanBundle.message("rcurly.expected", new Object[0]))) {
            blockMark.done(FanElementTypes.CLASS_BODY);
            return true;
        }
        ParserUtils.cleanAfterErrorInBlock(builder);
        blockMark.done(FanElementTypes.CLASS_BODY);
        return false;
    }
}