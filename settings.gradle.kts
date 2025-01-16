pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
    }
}
rootProject.name="code-with-quarkus-kotlin"


include(
    ":clients:jvm",
)
project(":clients:jvm").projectDir = File("clients/jvm")
