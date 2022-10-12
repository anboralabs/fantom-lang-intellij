package org.fandev.lang.fan.parsing.expression;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.TokenSet;

public interface ExpressionParser {
  boolean innerParse(PsiBuilder paramPsiBuilder, TokenSet paramTokenSet);
}


/* Location:              /Users/dalgarins/Downloads/fan4idea-0.0.6/lib/fan4idea.jar!/org/fandev/lang/fan/parsing/expression/ExpressionParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */