plugins {
    `java-library`
    kotlin("jvm")
    id("io.quarkus")
}

group = "my.clients"
version = "1.0.0"

repositories {
    mavenCentral()
}

repositories {
    mavenCentral()
    mavenLocal()
}

val oas by configurations.creating {
    isCanBeConsumed = false
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val quarkusOpenApiGeneratorVersion: String by project
val javaVersion = JavaVersion.VERSION_11.toString()
val javaVersionValue: JavaVersion = JavaVersion.VERSION_11

dependencies {
    // so client generation can find the openAPI spec file
    oas(
        project(
            mapOf(
                "path" to ":",
                "configuration" to "oas",
            ),
        ),
    )
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))

    implementation("io.quarkiverse.openapi.generator:quarkus-openapi-generator:$quarkusOpenApiGeneratorVersion")

    // needed for runtime. api so users would have them in their dependencies tree.
    api("io.quarkus:quarkus-rest-client-oidc-filter")
    api("io.quarkus:quarkus-rest-client-reactive-jackson")
    api("io.quarkus:quarkus-resteasy-reactive-jackson")
    api("org.eclipse.microprofile.fault-tolerance:microprofile-fault-tolerance-api")
    api("io.smallrye:smallrye-fault-tolerance-api")
}

java {
    sourceCompatibility = javaVersionValue
    targetCompatibility = javaVersionValue
}

// We don't want to package the `application.properties` used to generate the client
sourceSets { main { resources { exclude("**") } } }

val copyOasFileLocally =
    task<Copy>("copyOasFileLocally") {
        inputs.files(oas)
        from(oas.singleFile.parentFile.absolutePath) {
            include("openapi.json")
            rename("openapi.json", "my-service.json")
        }
        into("src/main/openapi")
    }

tasks {
    quarkusGenerateCode {
        dependsOn(copyOasFileLocally)
    }
    compileJava { dependsOn(compileQuarkusGeneratedSourcesJava) }
}
