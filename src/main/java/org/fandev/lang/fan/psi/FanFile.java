package org.fandev.lang.fan.psi;

import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiFile;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FanFile extends PsiFile, PsiClassOwner {
    String getPodName();

    FanTypeDefinition[] getTypeDefinitions();

    @Nullable
    FanTypeDefinition getTypeByName(@NotNull String paramString);

    FanTopLevelDefintion[] getTopLevelDefinitions();
}