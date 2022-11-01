package co.anbora.labs.fantom.ide.actions

import co.anbora.labs.fantom.ide.icons.FantomIcons
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory

private const val CAPTION = "Fantom Class"

class CreateNewClassAction: CreateFileFromTemplateAction(CAPTION, "", FantomIcons.FILE) {
    override fun buildDialog(
        project: Project,
        directory: PsiDirectory,
        builder: CreateFileFromTemplateDialog.Builder
    ) {
        builder.setTitle(CAPTION).addKind("Class file", FantomIcons.FILE, "Fantom Class")
    }

    override fun getActionName(
        directory: PsiDirectory?,
        newName: String,
        templateName: String?
    ): String = CAPTION
}