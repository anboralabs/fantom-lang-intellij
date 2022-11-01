package co.anbora.labs.fantom.lang

import com.intellij.lang.Language
import com.intellij.openapi.util.io.StreamUtil

object FantomLanguage: Language("fantom_lang") {
    const val LANGUAGE_NAME = "Fantom"
    val LANGUAGE_DEMO_TEXT by lazy {
        val stream = javaClass.classLoader.getResourceAsStream("demo/JsonConverter.fan")
            ?: error("No such file")
        val text = stream.bufferedReader().use { it.readText() }
        StreamUtil.convertSeparators(text)
    }
}