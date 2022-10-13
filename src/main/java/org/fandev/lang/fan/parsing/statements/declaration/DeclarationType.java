package org.fandev.lang.fan.parsing.statements.declaration;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.fandev.lang.fan.FanTokenTypes;


public enum DeclarationType {
    CLASS(FanTokenTypes.CLASS_KEYWORD, FanTokenTypes.CLASS_MODIFIERS),
    MIXIN(FanTokenTypes.MIXIN_KEYWORD, FanTokenTypes.MIXIN_MODIFIERS),
    ENUM(FanTokenTypes.ENUM_KEYWORD, FanTokenTypes.PROTECTION),
    CONSTRUCTOR(FanTokenTypes.NEW_KEYWORD, FanTokenTypes.PROTECTION),
    INNER_SET(FanTokenTypes.PROTECTION),
    METHOD(FanTokenTypes.METHOD_MODIFIERS),
    FIELD(FanTokenTypes.FIELD_MODIFIERS);

    private final IElementType keyword;
    private final TokenSet modifiersSet;

    DeclarationType(IElementType keyword, TokenSet modifiersSet) {
        this.keyword = keyword;
        this.modifiersSet = modifiersSet;
    }

    DeclarationType(TokenSet modifiersSet) {
        this.keyword = null;
        this.modifiersSet = modifiersSet;
    }

    public IElementType getKeyword() {
        return this.keyword;
    }

    public TokenSet getModifiersSet() {
        return this.modifiersSet;
    }

    public String errorMessage() {
        StringBuilder builder = new StringBuilder("Modifiers ( ");
        this.modifiersSet.getTypes();
        boolean first = true;
        for (IElementType type : this.modifiersSet.getTypes()) {
            if (!first) {
                builder.append(", ");
            }
            builder.append(type.toString());
            first = false;
        }
        builder.append(" )");
        if (getKeyword() != null) {
            builder.append(" or keyword ").append(getKeyword());
        }
        return builder.toString();
    }
}