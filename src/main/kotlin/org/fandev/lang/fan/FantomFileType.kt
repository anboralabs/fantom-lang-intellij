package org.fandev.lang.fan

import com.intellij.openapi.fileTypes.LanguageFileType
import org.fandev.icons.Icons
import org.fandev.lang.fan.FantomLanguage.LANGUAGE_NAME
import javax.swing.Icon

object FantomFileType: LanguageFileType(FantomLanguage) {

    private const val EXTENSION = "fan"

    override fun getName(): String = LANGUAGE_NAME

    override fun getDescription(): String = "Fantom lang support"

    override fun getDefaultExtension(): String = EXTENSION

    override fun getIcon(): Icon? = Icons.POD
}