package org.fandev.lang.fan.parsing.util;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;


public class ParserUtils {
    public static boolean getToken(PsiBuilder builder, IElementType elem) {
        if (elem.equals(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        return false;
    }

    public static boolean getToken(PsiBuilder builder, TokenSet tokens) {
        if (tokens.contains(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        return false;
    }

    public static boolean getToken(PsiBuilder builder, IElementType elem, String errorMsg) {
        if (elem.equals(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        if (errorMsg != null) {
            builder.error(errorMsg);
        }
        return false;
    }


    public static boolean getToken(PsiBuilder builder, TokenSet tokens, String errorMsg) {
        if (tokens.contains(builder.getTokenType())) {
            builder.advanceLexer();
            return true;
        }
        if (errorMsg != null) {
            builder.error(errorMsg);
        }
        return false;
    }

    public static IElementType firstAfter(PsiBuilder builder, IElementType... elems) {
        IElementType result;
        TokenSet ignored = TokenSet.create(elems);

        if (ignored.contains(builder.getTokenType())) {
            PsiBuilder.Marker rb = builder.mark();
            while (!builder.eof() && ignored.contains(builder.getTokenType())) {
                builder.advanceLexer();
            }
            result = builder.getTokenType();
            rb.rollbackTo();
        } else {
            result = builder.getTokenType();
        }
        return result;
    }

    public static boolean lookAhead(PsiBuilder builder, IElementType... elems) {
        if (!elems[0].equals(builder.getTokenType())) {
            return false;
        }

        if (elems.length == 1) {
            return true;
        }

        PsiBuilder.Marker rb = builder.mark();
        builder.advanceLexer();
        int i = 1;
        while (!builder.eof() && i < elems.length && elems[i].equals(builder.getTokenType())) {
            builder.advanceLexer();
            i++;
        }
        rb.rollbackTo();
        return (i == elems.length);
    }

    public static boolean lookAheadForElement(PsiBuilder builder, IElementType elem, IElementType... stopElements) {
        TokenSet stopElem = TokenSet.create(stopElements);
        return lookAheadForElement(builder, elem, stopElem);
    }

    public static boolean lookAheadForElement(PsiBuilder builder, IElementType elem, TokenSet stopElem) {
        PsiBuilder.Marker rb = builder.mark();
        while (!builder.eof() && elem != builder.getTokenType() && !stopElem.contains(builder.getTokenType())) {
            builder.advanceLexer();
        }
        if (builder.eof()) {
            rb.rollbackTo();
            return false;
        }
        if (stopElem.contains(builder.getTokenType())) {
            rb.rollbackTo();
            return false;
        }
        rb.rollbackTo();
        return true;
    }


    public static IElementType eatElement(PsiBuilder builder, IElementType elem) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        marker.done(elem);
        return elem;
    }

    public static void advanceToBlockEnd(PsiBuilder builder) {
        advanceToBlockEnd(builder, FanTokenTypes.LBRACE, FanTokenTypes.RBRACE);
    }

    public static void advanceToArgumentsEnd(PsiBuilder builder) {
        advanceToBlockEnd(builder, FanTokenTypes.LPAR, FanTokenTypes.RPAR);
    }

    public static void advanceToArrayEnd(PsiBuilder builder) {
        advanceToBlockEnd(builder, FanTokenTypes.LBRACKET, FanTokenTypes.RBRACKET);
    }

    public static void advanceToBlockEnd(PsiBuilder builder, IElementType opening, IElementType closing) {
        int openLeft = 1;
        while (!builder.eof() && openLeft > 0) {
            builder.advanceLexer();
            if (builder.getTokenType() == opening) {
                openLeft++;
                continue;
            }
            if (builder.getTokenType() == closing) {
                openLeft--;
            }
        }
        builder.advanceLexer();
    }

    public static boolean advanceTo(PsiBuilder builder, TokenSet stopper) {
        while (!builder.eof() && !stopper.contains(builder.getTokenType())) {
            builder.advanceLexer();
        }
        return stopper.contains(builder.getTokenType());
    }

    public static void advanceNoNls(PsiBuilder builder) {
        builder.advanceLexer();
        removeNls(builder);
    }

    public static void removeNls(PsiBuilder builder) {
        while (FanTokenTypes.NLS.equals(builder.getTokenType())) {
            builder.advanceLexer();
        }
    }

    public static void cleanAfterErrorInBlock(PsiBuilder builder) {
        PsiBuilder.Marker em = builder.mark();
        advanceToBlockEnd(builder);
        em.error(FanBundle.message("separator.expected", new Object[0]));
    }

    public static void cleanAfterErrorInArguments(PsiBuilder builder) {
        PsiBuilder.Marker em = builder.mark();
        advanceToArgumentsEnd(builder);
        em.error(FanBundle.message("separator.expected", new Object[0]));
    }

    public static void cleanAfterErrorInArray(PsiBuilder builder) {
        PsiBuilder.Marker em = builder.mark();
        advanceToArrayEnd(builder);
        em.error(FanBundle.message("separator.expected", new Object[0]));
    }


    public static boolean parseName(PsiBuilder builder) {
        if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            builder.error(FanBundle.message("identifier.expected", new Object[0]));
            return false;
        }
        PsiBuilder.Marker idMark = builder.mark();
        builder.advanceLexer();
        idMark.done(FanElementTypes.NAME_ELEMENT);
        return true;
    }

    public static void removeStoppers(PsiBuilder builder, TokenSet stopper, TokenSet reccuring) {
        if (stopper.contains(builder.getTokenType())) {
            builder.advanceLexer();
        }
        while (reccuring.contains(builder.getTokenType()))
            builder.advanceLexer();
    }
}