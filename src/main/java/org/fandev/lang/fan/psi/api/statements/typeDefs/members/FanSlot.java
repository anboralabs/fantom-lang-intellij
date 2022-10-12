package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import com.intellij.psi.PsiDocCommentOwner;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiTypeParameterListOwner;
import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.api.modifiers.FanFacet;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;

public interface FanSlot extends FanElement, PsiMember, PsiTypeParameterListOwner, PsiNameIdentifierOwner, PsiDocCommentOwner, FanTopLevelDefintion {
  FanFacet[] getFacets();
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/psi/api/statements/typeDefs/members/FanSlot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */