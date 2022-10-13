package org.fandev.lang.fan.parsing.types;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class FuncTypeSpec {
    public static TypeType parseFuncType(PsiBuilder builder, boolean forLiteral) {
        if (FanTokenTypes.OR != builder.getTokenType()) {
            return TypeType.NONE;
        }

        PsiBuilder.Marker funcMarker = builder.mark();
        builder.advanceLexer();
        parseFormals(builder);


        if (FanTokenTypes.DYN_CALL == builder.getTokenType()) {
            builder.advanceLexer();
            if (TypeSpec.parseType(builder, false) != TypeType.NONE) {
                return parseClosingOr(builder, funcMarker, forLiteral);
            }
            funcMarker.error(FanBundle.message("type.expected", new Object[0]));
        } else {

            return parseClosingOr(builder, funcMarker, forLiteral);
        }
        return TypeType.NONE;
    }


    public static TypeType parseClosingOr(PsiBuilder builder, PsiBuilder.Marker funcMarker, boolean forLiteral) {
        if (FanTokenTypes.OR == builder.getTokenType()) {
            builder.advanceLexer();
            funcMarker.done(FanElementTypes.FUNC_TYPE);
            return TypeSpec.endOfTypeParse(builder, builder.mark(), forLiteral, TypeType.FUNCTION);
        }
        funcMarker.error(FanBundle.message("or.expected", new Object[0]));
        return TypeType.NONE;
    }


    public static boolean parseFormals(PsiBuilder builder) {
        PsiBuilder.Marker formalsMarker = builder.mark();
        boolean commaExpected = false;
        while (!builder.eof() && !FanTokenTypes.DYN_CALL.equals(builder.getTokenType())) {
            if (commaExpected) {
                if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {
                    builder.advanceLexer();
                } else {
                    if (FanTokenTypes.OR.equals(builder.getTokenType())) {
                        break;
                    }
                    formalsMarker.error(FanBundle.message("comma.expected", new Object[0]));
                    return false;
                }


            } else if (FanTokenTypes.COMMA.equals(builder.getTokenType())) {

                builder.advanceLexer();

                break;
            }
            PsiBuilder.Marker formalMarker = builder.mark();
            if (TypeSpec.parseType(builder, false) != TypeType.NONE) {
                commaExpected = true;
                if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
                    ParserUtils.parseName(builder);
                }
                formalMarker.done(FanElementTypes.FORMAL);
                continue;
            }
            formalMarker.rollbackTo();
            formalsMarker.error(FanBundle.message("type.expected", new Object[0]));
            return false;
        }

        formalsMarker.done(FanElementTypes.FORMALS);
        return true;
    }
}