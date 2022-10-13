package org.fandev.lang.fan.parsing.auxiliary.modifiers;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class Modifiers {
    public static TokenSet parse(PsiBuilder builder, DeclarationType stmtType) {
        TokenSet modifiers = TokenSet.create(new IElementType[0]);

        ParserUtils.removeNls(builder);
        PsiBuilder.Marker modifiersMarker = builder.mark();

        while (!builder.eof()) {

            if (stmtType.getKeyword() != null) {
                if (stmtType.getKeyword().equals(builder.getTokenType())) {
                    modifiersMarker.done(FanElementTypes.MODIFIERS);
                    return modifiers;
                }

            } else if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
                modifiersMarker.done(FanElementTypes.MODIFIERS);
                return modifiers;
            }

            IElementType possibleModifier = builder.getTokenType();
            if (!Modifier.parse(builder, stmtType)) {
                if (FanTokenTypes.ALL_MODIFIERS.contains(possibleModifier)) {

                    String tokenText = builder.getTokenText();
                    builder.error(FanBundle.message("illegal.modifier", new Object[]{tokenText, stmtType}));
                    builder.advanceLexer();
                    continue;
                }
                modifiersMarker.done(FanElementTypes.MODIFIERS);

                break;
            }
            modifiers = TokenSet.orSet(new TokenSet[]{modifiers, TokenSet.create(new IElementType[]{possibleModifier})});
        }

        return modifiers;
    }
}