package org.fandev.lang.fan.psi.api.statements.typeDefs;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;

public interface FanReferenceList extends StubBasedPsiElement<FanReferenceListStub>, PsiElement {
    FanCodeReferenceElement[] getReferenceElements();
}
