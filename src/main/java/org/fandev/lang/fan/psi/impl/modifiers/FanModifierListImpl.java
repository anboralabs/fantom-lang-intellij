package org.fandev.lang.fan.psi.impl.modifiers;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiAnnotation;
import com.intellij.util.IncorrectOperationException;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class FanModifierListImpl
        extends FanBaseElementImpl
        implements FanModifierList {
    public FanModifierListImpl(ASTNode astNode) {
        super(astNode);
    }

    public boolean hasModifierProperty(String modifier) {
        if (modifier.equals("public")) {
            return (findChildByType(FanTokenTypes.PRIVATE_KEYWORD) == null && findChildByType(FanTokenTypes.PROTECTED_KEYWORD) == null && findChildByType(FanTokenTypes.INTERNAL_KEYWORD) == null);
        }
        return hasExplicitModifier(modifier);
    }

    public boolean hasExplicitModifier(String name) {
        if (name.equals("public")) return (findChildByType(FanTokenTypes.PUBLIC_KEYWORD) != null);
        if (name.equals("abstract")) return (findChildByType(FanTokenTypes.ABSTRACT_KEYWORD) != null);
        if (name.equals("native")) return (findChildByType(FanTokenTypes.NATIVE_KEYWORD) != null);
        return hasOtherModifiers(name);
    }

    private boolean hasOtherModifiers(String name) {
        if (name.equals("private")) return (findChildByType(FanTokenTypes.PRIVATE_KEYWORD) != null);
        if (name.equals("protected")) return (findChildByType(FanTokenTypes.PROTECTED_KEYWORD) != null);
        if (name.equals("packageLocal")) return (findChildByType(FanTokenTypes.INTERNAL_KEYWORD) != null);
        if (name.equals("static")) return (findChildByType(FanTokenTypes.STATIC_KEYWORD) != null);
        if (name.equals("final")) return (findChildByType(FanTokenTypes.FINAL_KEYWORD) != null);
        return (name.equals("volatile") && findChildByType(FanTokenTypes.VOLATILE_KEYWORD) != null);
    }

    public void setModifierProperty(String name, boolean value) throws IncorrectOperationException {
    }

    public void checkSetModifierProperty(String name, boolean value) throws IncorrectOperationException {
    }

    @NotNull
    public PsiAnnotation[] getAnnotations() {
        return PsiAnnotation.EMPTY_ARRAY;
    }

    @Nullable
    public PsiAnnotation findAnnotation(@NotNull String qualifiedName) {
        return null;
    }

    @NotNull
    public PsiAnnotation[] getApplicableAnnotations() {
        return new PsiAnnotation[0];
    }

    @NotNull
    public PsiAnnotation addAnnotation(@NotNull String qualifiedName) {
        return null;
    }

    public String toString() {
        return "Modifiers";
    }
}
