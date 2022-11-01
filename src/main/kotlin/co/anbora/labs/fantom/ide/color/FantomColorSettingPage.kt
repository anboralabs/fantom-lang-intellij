package co.anbora.labs.fantom.ide.color

import co.anbora.labs.fantom.ide.highlight.FantomSyntaxHighlighter
import co.anbora.labs.fantom.ide.icons.FantomIcons
import co.anbora.labs.fantom.lang.FantomLanguage.LANGUAGE_DEMO_TEXT
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class FantomColorSettingPage: ColorSettingsPage {

    private val ATTRS = FantomColors.values().map { it.attributesDescriptor }.toTypedArray()

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = ATTRS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "Fantom"

    override fun getIcon(): Icon = FantomIcons.FILE

    override fun getHighlighter(): SyntaxHighlighter = FantomSyntaxHighlighter()

    override fun getDemoText(): String = LANGUAGE_DEMO_TEXT

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> = mutableMapOf()
}
