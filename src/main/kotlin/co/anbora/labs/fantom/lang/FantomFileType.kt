package co.anbora.labs.fantom.lang

import com.intellij.openapi.fileTypes.LanguageFileType
import co.anbora.labs.fantom.lang.FantomLanguage.LANGUAGE_NAME
import co.anbora.labs.fantom.ide.icons.FantomIcons
import javax.swing.Icon

object FantomFileType: LanguageFileType(FantomLanguage) {

    private const val EXTENSION = "fan"

    override fun getName(): String = LANGUAGE_NAME

    override fun getDescription(): String = "Fantom lang support"

    override fun getDefaultExtension(): String = EXTENSION

    override fun getIcon(): Icon = FantomIcons.FILE
}