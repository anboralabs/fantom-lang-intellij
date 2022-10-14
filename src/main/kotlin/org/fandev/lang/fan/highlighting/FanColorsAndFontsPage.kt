package org.fandev.lang.fan.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.fandev.icons.Icons
import org.fandev.lang.fan.FantomLanguage
import org.fandev.lang.fan.FantomLanguage.LANGUAGE_NAME
import javax.swing.Icon

class FanColorsAndFontsPage: ColorSettingsPage {

    private val ATTRS = arrayOf<AttributesDescriptor>()

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = ATTRS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = LANGUAGE_NAME

    override fun getIcon(): Icon? = Icons.FILE

    override fun getHighlighter(): SyntaxHighlighter {
        return SyntaxHighlighterFactory.getSyntaxHighlighter(FantomLanguage, null, null)
    }

    override fun getDemoText(): String = ""

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> = mutableMapOf()
}