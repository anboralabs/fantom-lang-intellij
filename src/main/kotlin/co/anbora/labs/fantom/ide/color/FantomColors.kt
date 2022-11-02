package co.anbora.labs.fantom.ide.color

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.options.colors.AttributesDescriptor
import java.awt.Color
import java.awt.Font

enum class FantomColors(humanName: String, default: TextAttributesKey) {

    KEY_WORD("Keywords", DefaultLanguageHighlighterColors.KEYWORD),
    BAD_CHAR("Bad Character", HighlighterColors.BAD_CHARACTER),
    ANNOTATIONS("Annotations",
        TextAttributesKey.createTempTextAttributesKey(
        "FANTOM_ANNOTATIONS",
        TextAttributes(Color.decode("#BBB529"), null, null, null, Font.PLAIN)
    )),
    METHOD_CALL(
        "Method Call",
        TextAttributesKey.createTempTextAttributesKey(
            "FANTOM_METHOD_CALL",
            TextAttributes(Color.decode("#E8C32A"), null, null, null, Font.PLAIN)
        )
    ),
    TYPES(
        "Types",
        TextAttributesKey.createTempTextAttributesKey(
            "FANTOM_TYPES",
            TextAttributes(Color.decode("#507874"), null, null, null, Font.PLAIN)
        )
    ),
    STRINGS("Literals//Strings", DefaultLanguageHighlighterColors.STRING),
    NUMBERS("Literals//Numbers", DefaultLanguageHighlighterColors.NUMBER),
    COMMENTS("Comments//Line Comment", DefaultLanguageHighlighterColors.LINE_COMMENT),
    DOC_COMMENTS("Comments//Doc Comment", DefaultLanguageHighlighterColors.DOC_COMMENT);


    val textAttributesKey = TextAttributesKey.createTextAttributesKey("co.anbora.labs.fantom.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
    val testSeverity: HighlightSeverity = HighlightSeverity(name, HighlightSeverity.INFORMATION.myVal)
}
