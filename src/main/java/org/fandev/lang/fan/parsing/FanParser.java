package org.fandev.lang.fan.parsing;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.text.BlockSupport;
import com.intellij.psi.tree.IElementType;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.parsing.topLevel.CompilationUnit;
import org.jetbrains.annotations.NotNull;


public class FanParser
        implements PsiParser {
    private static final Logger logger = Logger.getInstance(FanParser.class.getName());

    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder psiBuilder) {
        psiBuilder.setDebugMode(true);
        PsiBuilder.Marker rootMarker = psiBuilder.mark();

        CompilationUnit.parse(psiBuilder);

        if (!psiBuilder.eof()) {
            PsiBuilder.Marker errorMark = psiBuilder.mark();
            while (!psiBuilder.eof()) {
                psiBuilder.advanceLexer();
            }
            errorMark.error(FanBundle.message("typedef.expected", new Object[0]));
        }

        rootMarker.done(root);
        try {
            return psiBuilder.getTreeBuilt();
        } catch (BlockSupport.ReparsedSuccessfullyException e) {
            throw e;
        } catch (Throwable t) {
            StringBuilder sb = new StringBuilder();
            while (!psiBuilder.eof()) {
                sb.append(psiBuilder.getTokenText());
                psiBuilder.advanceLexer();
            }
            logger.error("Parsing error, current offset is: " + psiBuilder.getCurrentOffset() + " Remaining text is: " + sb.toString(), t);

            throw new RuntimeException(t);
        }
    }
}
