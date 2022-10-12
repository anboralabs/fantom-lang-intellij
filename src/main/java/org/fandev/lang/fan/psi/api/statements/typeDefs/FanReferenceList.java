package org.fandev.lang.fan.psi.api.statements.typeDefs;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;

public interface FanReferenceList extends StubBasedPsiElement<FanReferenceListStub>, PsiElement {
  FanCodeReferenceElement[] getReferenceElements();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/typeDefs/FanReferenceList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */