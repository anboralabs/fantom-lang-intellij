plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij") version "1.17.0"
    id("org.jetbrains.grammarkit") version "2022.3.2.1"
}

group = "co.anbora.labs"
version = "1.3.4"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("LATEST-EAP-SNAPSHOT")
    type.set("IU") // Target IDE Platform

    plugins.set(listOf())
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    val generateFantomLexer = task<org.jetbrains.grammarkit.tasks.GenerateLexerTask>("generateFantomLexer") {
        sourceFile.set(file("src/main/grammar/Fantom.flex"))
        targetDir.set("src/main/gen/co/anbora/labs/fantom/lang/")
        targetClass.set("FanLexer")
        purgeOldFiles.set(true)
    }

    val generateFantomParser = task<org.jetbrains.grammarkit.tasks.GenerateParserTask>("generateFantomParser") {
        sourceFile.set(file("src/main/grammar/Fantom.bnf"))
        targetRoot.set("src/main/gen")
        pathToParser.set("/co/anbora/labs/fantom/lang/core/parser/FantomParser.java")
        pathToPsiRoot.set("/co/anbora/labs/fantom/lang/core/psi")
        purgeOldFiles.set(true)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        dependsOn(generateFantomLexer, generateFantomParser)
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("241.*")
        changeNotes.set(file("src/main/html/change-notes.html").inputStream().readBytes().toString(Charsets.UTF_8))
        pluginDescription.set(file("src/main/html/description.html").inputStream().readBytes().toString(Charsets.UTF_8))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    buildSearchableOptions {
        enabled = false
    }
}
