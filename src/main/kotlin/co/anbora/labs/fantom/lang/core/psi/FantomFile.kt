package co.anbora.labs.fantom.lang.core.psi

import co.anbora.labs.fantom.lang.FantomFileType
import co.anbora.labs.fantom.lang.FantomLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class FantomFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FantomLanguage) {
    override fun getFileType(): FileType = FantomFileType
    override fun toString(): String = "Fantom file"
}
