plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.jesseHessej"
version = "1.0.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

intellij {
    version.set("2025.2")
    type.set("IU")
    plugins.set(listOf())
}

tasks {
    patchPluginXml {
        sinceBuild.set("252")
        untilBuild.set("252.*")
    }
    buildSearchableOptions {
        enabled = false
    }
}
tasks.named<org.jetbrains.intellij.tasks.BuildPluginTask>("buildPlugin") {
    archiveBaseName.set("FindInSelectedDirPlugin") // zip 名称
}