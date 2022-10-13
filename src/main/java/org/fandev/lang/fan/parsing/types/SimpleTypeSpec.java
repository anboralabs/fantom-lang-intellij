package org.fandev.lang.fan.parsing.types;

import com.intellij.lang.PsiBuilder;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.ReferenceElement;


public class SimpleTypeSpec {
    public static TypeType parseSimpleType(PsiBuilder builder, boolean forLiteral) {
        if (FanTokenTypes.FAN_SYS_TYPE == builder.getTokenType())
            return parseBuiltInType(builder, forLiteral);
        if (FanTokenTypes.IDENTIFIER_TOKENS_SET.contains(builder.getTokenType())) {
            return parseClassOrInterfaceType(builder, forLiteral);
        }
        return TypeType.NONE;
    }

    public static TypeType parseBuiltInType(PsiBuilder builder, boolean forLiteral) {
        PsiBuilder.Marker builtInTypeMarker = builder.mark();

        if (!ReferenceElement.parseReferenceElement(builder)) {
            builtInTypeMarker.drop();
            return TypeType.NONE;
        }

        PsiBuilder.Marker arrMarker = builtInTypeMarker.precede();
        builtInTypeMarker.done(FanElementTypes.CLASS_TYPE_ELEMENT);
        TypeType result = TypeSpec.endOfTypeParse(builder, arrMarker, forLiteral, TypeType.SIMPE);

        return result;
    }

    static TypeType parseClassOrInterfaceType(PsiBuilder builder, boolean forLiteral) {
        PsiBuilder.Marker arrMarker = builder.mark();
        PsiBuilder.Marker typeElementMarker = builder.mark();

        if (!ReferenceElement.parseReferenceElement(builder)) {
            typeElementMarker.drop();
            arrMarker.rollbackTo();
            return TypeType.NONE;
        }
        typeElementMarker.done(FanElementTypes.CLASS_TYPE_ELEMENT);
        return TypeSpec.endOfTypeParse(builder, arrMarker, forLiteral, TypeType.SIMPE);
    }
}