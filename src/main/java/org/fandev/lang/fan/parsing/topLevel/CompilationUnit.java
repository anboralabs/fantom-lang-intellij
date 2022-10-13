package org.fandev.lang.fan.parsing.topLevel;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs.TypeDefinition;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class CompilationUnit {
    public static void parse(PsiBuilder builder) {
        if (FanTokenTypes.SHABENG == builder.getTokenType()) {
            PsiBuilder.Marker shBeng = builder.mark();
            if (!ParserUtils.advanceTo(builder, FanTokenTypes.EOL)) {
                shBeng.error(FanBundle.message("separator.expected", new Object[0]));
                return;
            }
            shBeng.done(FanTokenTypes.SHABENG);
        }

        ParserUtils.removeNls(builder);

        while (FanTokenTypes.USING_KEYWORD == builder.getTokenType()) {
            parseUsing(builder);
        }

        while (!builder.eof()) {
            if (!TypeDefinition.parse(builder)) {
                ParserUtils.cleanAfterErrorInBlock(builder);
            }
        }
    }

    private static void parseUsing(PsiBuilder builder) {
        PsiBuilder.Marker usingStatement = builder.mark();
        PsiBuilder.Marker usingKeyword = builder.mark();
        builder.advanceLexer();
        usingKeyword.done(FanTokenTypes.USING_KEYWORD);
        if (FanTokenTypes.LBRACKET.equals(builder.getTokenType())) {

            builder.advanceLexer();
            PsiBuilder.Marker ffiMark = builder.mark();
            if (ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]))) {
                ffiMark.done(FanElementTypes.FFI_NAME);
            } else {
                ffiMark.drop();
            }
            ParserUtils.getToken(builder, FanTokenTypes.RBRACKET, FanBundle.message("rbrack.expected", new Object[0]));
        }


        PsiBuilder.Marker podIdExpr = builder.mark();
        PsiBuilder.Marker podRefMark = builder.mark();
        while (true) {
            ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
            if (!ParserUtils.getToken(builder, FanTokenTypes.DOT)) {
                podRefMark.done(FanElementTypes.POD_REFERENCE);

                if (ParserUtils.getToken(builder, FanTokenTypes.COLON_COLON)) {
                    PsiBuilder.Marker usingType = builder.mark();
                    PsiBuilder.Marker m = builder.mark();
                    ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
                    m.done(FanElementTypes.NAME_ELEMENT);
                    usingType.done(FanElementTypes.ID_EXPR);
                }
                podIdExpr.done(FanElementTypes.ID_EXPR);

                if (ParserUtils.getToken(builder, FanTokenTypes.AS_KEYWORD)) {
                    PsiBuilder.Marker m = builder.mark();
                    ParserUtils.getToken(builder, FanTokenTypes.IDENTIFIER_TOKENS_SET, FanBundle.message("identifier.expected", new Object[0]));
                    m.done(FanElementTypes.USING_AS_NAME);
                }
                usingStatement.done(FanElementTypes.USING_STATEMENT);

                if (!FanTokenTypes.EOL.contains(builder.getTokenType())) {
                    builder.error(FanBundle.message("separator.expected", new Object[0]));
                    ParserUtils.advanceTo(builder, FanTokenTypes.EOL);
                } else {
                    builder.advanceLexer();
                }
                ParserUtils.removeNls(builder);
                return;
            }
        }
    }
}