package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.util.Key;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.statements.Block;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class FieldDefinition {
    public static final Key<String> FIELD_NAME = new Key("fan.parser.fieldName");

    private static final TokenSet FIELD_DEF_STOPPER = TokenSet.create(new IElementType[]{FanTokenTypes.SEMICOLON, FanTokenTypes.NLS, FanTokenTypes.LBRACE});

    public static boolean parse(PsiBuilder builder) {
        PsiBuilder.Marker declMarker = builder.mark();

        Facet.parse(builder);

        TokenSet modifiers = Modifiers.parse(builder, DeclarationType.FIELD);
        boolean modifiersParsed = ((modifiers.getTypes()).length > 0);

        PsiBuilder.Marker beforeType = builder.mark();
        if (!TypeSpec.parse(builder)) {
            declMarker.drop();
            return false;
        }

        if (!FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {

            beforeType.rollbackTo();
        } else {
            beforeType.drop();
        }
        if (!ParserUtils.parseName(builder)) {
            declMarker.drop();
            return false;
        }

        boolean hasInitValue = false;

        IElementType firstTokenAfter = ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS});

        if (FanTokenTypes.COLON_EQ.equals(firstTokenAfter)) {
            ParserUtils.removeNls(builder);
            ParserUtils.advanceNoNls(builder);
            builder.putUserData(FIELD_NAME, "on");
            Expression.parseExpr(builder, FIELD_DEF_STOPPER, FanElementTypes.FIELD_DEFAULT);
            builder.putUserData(FIELD_NAME, null);
            firstTokenAfter = ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS});
            hasInitValue = true;
        }


        if (FanTokenTypes.LBRACE.equals(firstTokenAfter)) {
            ParserUtils.removeNls(builder);
            if (hasInitValue) ;


            PsiBuilder.Marker getterSetter = builder.mark();

            ParserUtils.advanceNoNls(builder);
            parseGetterSetter(builder, getterSetter);
        }

        if (builder.eof() || !FanTokenTypes.EOS.contains(builder.getTokenType())) {
            declMarker.error(FanBundle.message("separator.expected", new Object[0]));
            return false;
        }


        ParserUtils.removeStoppers(builder, FanTokenTypes.SEPARATOR, FanTokenTypes.SEPARATOR);

        declMarker.done(FanElementTypes.FIELD_DEFINITION);

        return true;
    }

    private static void parseGetterSetter(PsiBuilder builder, PsiBuilder.Marker getterSetter) {
        if (testEndGetterSetter(builder, getterSetter)) {
            return;
        }
        PropertyBlock blockType = findPropertyBlockType(builder);
        if (blockType == PropertyBlock.GETTER) {
            parseGetBlock(builder);
            if (testEndGetterSetter(builder, getterSetter)) {
                return;
            }
            blockType = findPropertyBlockType(builder);
        }
        if (blockType == PropertyBlock.SETTER) {
            ParserUtils.removeNls(builder);
            PsiBuilder.Marker defMark = builder.mark();
            TokenSet modifiers = Modifiers.parse(builder, DeclarationType.INNER_SET);

            if (ParserUtils.parseName(builder)) {
                parseGetSetBlock(builder);
                defMark.done(FanElementTypes.SETTER_FIELD_DEFINITION);
            } else if ((modifiers.getTypes()).length > 0) {
                defMark.error("Found modifiers for setter but no set");
            } else {
                defMark.error("set block error");
            }
        }

        if (!testEndGetterSetter(builder, getterSetter)) {
            getterSetter.error("Did not find } or <eos>");
        }
    }


    public static PropertyBlock findPropertyBlockType(PsiBuilder builder) {
        PropertyBlock blockType = PropertyBlock.NONE;
        PsiBuilder.Marker rb = builder.mark();
        ParserUtils.removeNls(builder);

        if (FanTokenTypes.LBRACE == builder.getTokenType()) {
            ParserUtils.advanceNoNls(builder);
        }
        while (!builder.eof()) {
            if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
                String anId = builder.getTokenText();
                if ("get".equals(anId)) {
                    blockType = PropertyBlock.GETTER;
                    break;
                }
                if ("set".equals(anId)) {
                    blockType = PropertyBlock.SETTER;

                    break;
                }
                ParserUtils.advanceNoNls(builder);
                continue;
            }
            if (FanTokenTypes.ALL_MODIFIERS.contains(builder.getTokenType()) || FanTokenTypes.NLS == builder.getTokenType()) {
                ParserUtils.advanceNoNls(builder);
            }
        }


        rb.rollbackTo();
        return blockType;
    }

    private static boolean parseGetSetBlock(PsiBuilder builder) {
        ParserUtils.removeNls(builder);
        if (FanTokenTypes.LBRACE.equals(builder.getTokenType()))
            return Block.parse(builder, FanElementTypes.METHOD_BODY);
        if (FanTokenTypes.SEMICOLON.equals(builder.getTokenType())) {
            builder.advanceLexer();
        }
        return true;
    }


    private static boolean testEndGetterSetter(PsiBuilder builder, PsiBuilder.Marker getterSetter) {
        IElementType firstAfter = ParserUtils.firstAfter(builder, new IElementType[]{FanTokenTypes.NLS});
        if (FanTokenTypes.RBRACE.equals(firstAfter)) {
            ParserUtils.removeNls(builder);

            builder.advanceLexer();
            getterSetter.done(FanElementTypes.GETTER_SETTER_FIELD_DEFINITION);
            return true;
        }
        return false;
    }


    private static boolean parseGetBlock(PsiBuilder builder) {
        PsiBuilder.Marker defMark = builder.mark();
        boolean res = ParserUtils.parseName(builder);
        if (res) {
            res = parseGetSetBlock(builder);
        }
        defMark.done(FanElementTypes.GETTER_FIELD_DEFINITION);
        if (!res) {
            builder.error("Expected get block");
        }
        return false;
    }
}