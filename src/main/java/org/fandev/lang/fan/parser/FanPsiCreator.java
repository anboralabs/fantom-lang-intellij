package org.fandev.lang.fan.parser;


import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.impl.FanIdentifierImpl;
import org.fandev.lang.fan.psi.impl.modifiers.FanModifierListImpl;
import org.fandev.lang.fan.psi.impl.statements.FanDefaultValueImpl;
import org.fandev.lang.fan.psi.impl.statements.FanVariableImpl;
import org.fandev.lang.fan.psi.impl.statements.arguments.FanArgumentImpl;
import org.fandev.lang.fan.psi.impl.statements.arguments.FanArgumentListImpl;
import org.fandev.lang.fan.psi.impl.statements.blocks.FanPsiCodeBlockImpl;
import org.fandev.lang.fan.psi.impl.statements.expressions.*;
import org.fandev.lang.fan.psi.impl.statements.params.FanFormalImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanFormalsImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanParameterImpl;
import org.fandev.lang.fan.psi.impl.statements.params.FanParameterListImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.*;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanConstructorImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanEnumValueImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanFieldImpl;
import org.fandev.lang.fan.psi.impl.statements.typedefs.members.FanMethodImpl;
import org.fandev.lang.fan.psi.impl.types.*;


public class FanPsiCreator {
    public static PsiElement createElement(ASTNode node) {
        IElementType elem = node.getElementType();

        if (elem == FanElementTypes.CLASS_DEFINITION) return new FanClassDefinitionImpl(node);
        if (elem == FanElementTypes.MIXIN_DEFINITION) return new FanMixinDefinitionImpl(node);
        if (elem == FanElementTypes.ENUM_DEFINITION) return new FanEnumDefinitionImpl(node);
        if (elem == FanElementTypes.POD_DEFINITION) return new FanPodDefinitionImpl(node);

        if (elem == FanElementTypes.CLASS_BODY || elem == FanElementTypes.ENUM_BODY || elem == FanElementTypes.MIXIN_BODY)
            return new FanTypeDefinitionBodyImpl(node);

        if (elem == FanElementTypes.STATIC_BLOCK) return new FanMethodImpl(node);
        if (elem == FanElementTypes.CTOR_DEFINITION) return new FanConstructorImpl(node);
        if (elem == FanElementTypes.METHOD_DEFINITION) return new FanMethodImpl(node);

        if (FanElementTypes.BLOCK_ELEMENTS.contains(elem)) return new FanPsiCodeBlockImpl(node);

        if (elem == FanElementTypes.FIELD_DEFINITION) return new FanFieldImpl(node);
        if (elem == FanElementTypes.ENUM_VALUE) return new FanEnumValueImpl(node);

        if (elem == FanElementTypes.ID_EXPR) return new FanReferenceExpressionImpl(node);

        if (elem == FanElementTypes.REFERENCE_ELEMENT) return new FanCodeReferenceElementImpl(node);

        if (elem == FanElementTypes.CLASS_TYPE_ELEMENT) return new FanClassTypeElementImpl(node);

        if (elem == FanElementTypes.LIST_TYPE) return new FanListTypeElementImpl(node);

        if (elem == FanElementTypes.FUNC_TYPE) return new FanFuncTypeElementImpl(node);

        if (elem == FanElementTypes.MAP_TYPE) return new FanMapTypeElementImpl(node);

        if (elem == FanElementTypes.INHERITANCE_CLAUSE) return new FanInheritanceClauseImpl(node);

        if (elem == FanElementTypes.MODIFIERS) return new FanModifierListImpl(node);

        if (elem == FanElementTypes.TYPE_PARAMETER_LIST) return new FanParameterListImpl(node);

        if (elem == FanElementTypes.TYPE_PARAMETER) return new FanParameterImpl(node);

        if (elem == FanElementTypes.LOCAL_DEF_STATEMENT) return new FanVariableImpl(node);

        if (elem == FanElementTypes.THIS_REFERENCE_EXPRESSION)
            return new FanThisReferenceExpressionImpl(node);

        if (elem == FanElementTypes.SUPER_REFERENCE_EXPRESSION)
            return new FanSuperReferenceExpressionImpl(node);

        if (elem == FanElementTypes.POD_REFERENCE) return new PodReferenceExpressionImpl(node);

        if (elem == FanElementTypes.CLOSURE_EXPR) return new FanClosureExpressionImpl(node);

        if (elem == FanElementTypes.FORMALS) return new FanFormalsImpl(node);

        if (elem == FanElementTypes.FORMAL) return new FanFormalImpl(node);

        if (elem == FanElementTypes.PARAM_DEFAULT) return new FanDefaultValueImpl(node);

        if (elem == FanElementTypes.INDEX_EXPR) return new FanIndexExpressionImpl(node);

        if (elem == FanElementTypes.ARGUMENT_LIST) return new FanArgumentListImpl(node);

        if (elem == FanElementTypes.ARGUMENT_EXPR) return new FanArgumentImpl(node);

        if (elem == FanElementTypes.NAME_ELEMENT) return new FanIdentifierImpl(node);

        return new ASTWrapperPsiElement(node);
    }
}