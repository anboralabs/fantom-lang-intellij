package org.fandev.lang.fan.parsing.expression;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;

public interface ExpressionParser {
  boolean innerParse(PsiBuilder paramPsiBuilder, TokenSet paramTokenSet);
}