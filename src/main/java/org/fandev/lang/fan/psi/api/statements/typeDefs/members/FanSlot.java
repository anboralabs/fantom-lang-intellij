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
