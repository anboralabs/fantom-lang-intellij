package org.fandev.lang.fan.psi.impl.statements.typedefs;

import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;


public class FanInheritanceClauseImpl
        extends FanBaseElementImpl<FanReferenceListStub>
        implements FanInheritanceClause, StubBasedPsiElement<FanReferenceListStub> {
    public FanInheritanceClauseImpl(ASTNode astNode) {
        super(astNode);
    }

    public FanInheritanceClauseImpl(FanReferenceListStub fanReferenceListStub) {
        super(fanReferenceListStub, FanElementTypes.INHERITANCE_CLAUSE);
    }

    public String toString() {
        return "Inheritance clause";
    }

    public FanCodeReferenceElement[] getReferenceElements() {
        return findChildrenByClass(FanCodeReferenceElement.class);
    }
}