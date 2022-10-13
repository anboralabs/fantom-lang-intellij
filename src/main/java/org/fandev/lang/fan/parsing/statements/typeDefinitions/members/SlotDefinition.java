package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

import java.util.HashSet;
import java.util.Set;

import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
import org.fandev.lang.fan.parsing.statements.Block;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.util.ParserUtils;


public class SlotDefinition {
    public static boolean parse(PsiBuilder builder, DeclarationType type, boolean isBuiltInType) {
        ParserUtils.removeNls(builder);


        PsiBuilder.Marker rb = builder.mark();


        Facet.parse(builder);


        Set<IElementType> modifiers = new HashSet<IElementType>();
        while (FanTokenTypes.ALL_SLOT_MODIFIERS.contains(builder.getTokenType())) {
            modifiers.add(builder.getTokenType());

            ParserUtils.advanceNoNls(builder);
            if (type == DeclarationType.MIXIN && FanTokenTypes.ONCE_KEYWORD == builder.getTokenType()) {
                rb.error(FanBundle.message("mixins.cannot.declare.once.methods", new Object[0]));
                rb = builder.mark();
            }
        }


        if (FanTokenTypes.LBRACE.equals(builder.getTokenType())) {

            rb.rollbackTo();
            PsiBuilder.Marker staticInitMark = builder.mark();
            if (!FanTokenTypes.STATIC_KEYWORD.equals(builder.getTokenType())) {
                staticInitMark.error(FanBundle.message("expecting.keyword.static", new Object[0]));
                return false;
            }
            PsiBuilder.Marker idMark = builder.mark();
            ParserUtils.advanceNoNls(builder);
            idMark.done(FanElementTypes.NAME_ELEMENT);
            if (!FanTokenTypes.LBRACE.equals(builder.getTokenType())) {
                staticInitMark.error(FanBundle.message("expecting.static", new Object[0]));
                return false;
            }
            Block.parse(builder, FanElementTypes.METHOD_BODY);
            staticInitMark.done(FanElementTypes.METHOD_DEFINITION);
            return true;
        }
        if (FanTokenTypes.NEW_KEYWORD.equals(builder.getTokenType())) {

            if (type == DeclarationType.MIXIN) {
                rb.error(FanBundle.message("mixins.cannot.declare.constructors", new Object[0]));
                return false;
            }
            if (type == DeclarationType.ENUM &&
                    !modifiers.contains(FanTokenTypes.PRIVATE_KEYWORD)) {
                rb.error(FanBundle.message("enums.must.have.private.constructors", new Object[0]));
                return false;
            }

            rb.rollbackTo();
            return ConstructorDefinition.parse(builder, isBuiltInType);
        }
        if (ParserUtils.lookAheadForElement(builder, FanTokenTypes.LPAR, new IElementType[]{FanTokenTypes.LBRACE, FanTokenTypes.SEMICOLON, FanTokenTypes.NLS, FanTokenTypes.COLON_EQ})) {

            rb.rollbackTo();
            return MethodDefinition.parse(builder, isBuiltInType);
        }
        rb.rollbackTo();
        return FieldDefinition.parse(builder);
    }
}