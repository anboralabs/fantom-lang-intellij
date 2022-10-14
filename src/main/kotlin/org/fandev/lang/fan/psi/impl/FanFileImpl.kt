package org.fandev.lang.fan.psi.impl

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiClass
import org.fandev.lang.fan.FantomFileType
import org.fandev.lang.fan.FantomLanguage
import org.fandev.lang.fan.psi.FanFile
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanClassDefinition
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition

class FanFileImpl(viewProvider: FileViewProvider): PsiFileBase(viewProvider, FantomLanguage), FanFile {

    private var podName: String? = null
    private val logger = Logger.getInstance("org.fandev.lang.fan.psi.impl.FanFileImpl")

    override fun getFileType(): FileType = FantomFileType

    override fun getClasses(): Array<PsiClass> = typeDefinitions as Array<PsiClass>

    override fun getPackageName(): String = podName.orEmpty()

    override fun setPackageName(packageName: String?) {
        this.podName = packageName
    }

    override fun getPodName(): String {
        if (podName != null) {
            return podName.orEmpty()
        }

        if ("build.fan" == name) {
            val classDef = findChildrenByClass(FanClassDefinition::class.java).firstOrNull()
            if (classDef != null) {
                val methods = classDef.fanMethods
                if (methods.isNotEmpty()) {
                    for (method in methods) {
                        if (method.name == "setup") {
                            val setupBody = method.body?.text.orEmpty()
                            val podNameIdx = setupBody.indexOf("podName")
                            if (podNameIdx != -1) {
                                val firstDQ = setupBody.indexOf('"', podNameIdx + "podName".length + 1)
                                val lastDQ = setupBody.indexOf('"', firstDQ + 1)
                                podName = setupBody.substring(firstDQ + 1, lastDQ)
                                return podName.orEmpty()
                            }
                        }
                    }
                }
            }
            logger.warn("Did not find pod name in " + if (virtualFile != null) virtualFile.path else toString())
            return "NotFound"
        }


        val myVirtualFile = virtualFile
        if (myVirtualFile != null && myVirtualFile.url.contains(".pod")) {
            val url = myVirtualFile.url
            val podPath = url.substring(0, url.indexOf(".pod"))
            var podNameStartIndex = podPath.lastIndexOf('/')
            podNameStartIndex = if (podNameStartIndex < 0) 0 else podNameStartIndex + 1
            podName = podPath.substring(podNameStartIndex)
            return podName.orEmpty()
        }


        var psiDirectory = parent
        var upMax = 4
        while (psiDirectory != null && upMax > 0) {
            val files = psiDirectory.files
            for (file in files) {
                if ("build.fan" == file.name) {
                    podName = (file as FanFile).podName
                    return podName.orEmpty()
                }
            }
            psiDirectory = psiDirectory.parentDirectory
            upMax--
        }
        return "NotFound"
    }

    override fun getTypeDefinitions(): Array<FanTypeDefinition> = findChildrenByClass(FanTypeDefinition::class.java)

    override fun getTypeByName(paramString: String): FanTypeDefinition? {
        return typeDefinitions.firstOrNull {
            name == it.name
        }
    }

    override fun getTopLevelDefinitions(): Array<FanTopLevelDefintion> = findChildrenByClass(FanTopLevelDefintion::class.java)
}