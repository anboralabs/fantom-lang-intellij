package co.anbora.labs.fantom.ide.icons

import com.intellij.icons.AllIcons
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object FantomIcons {

    val FILE = getIcon("icon_file.svg")

    val FUNCTION = AllIcons.Nodes.Function
    val PARAMETER = AllIcons.Nodes.Parameter

    val VARIABLE = AllIcons.Nodes.Variable

    private fun getIcon(path: String): Icon {
        return IconLoader.findIcon("/icons/$path", FantomIcons::class.java.classLoader) as Icon
    }
}
