import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.openapi.generator") version "6.3.0"
}

group = "com.makrol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.9.2"
val ktorVersion = "2.2.3"

dependencies {
    //OpenAPI client
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("com.google.code.gson:gson:2.10.1")

    //Tests organization
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

tasks.test {
    useJUnitPlatform()
}

val jvmVersion = 11
kotlin {
    jvmToolchain(jvmVersion)
}

val generatedSourcesPath = "$buildDir/generated"
val apiDescriptionFile = getFilePath(fileName = "teamCityRestApi-v2018.1-swagger2.0.json")
val apiRootName = "com.makrol.teamcity.api.client"

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set(apiDescriptionFile)
    outputDir.set(generatedSourcesPath)
    apiPackage.set("$apiRootName.api")
    invokerPackage.set("$apiRootName.invoker")
    modelPackage.set("$apiRootName.model")
    configOptions.set(mapOf("library" to "jvm-ktor"))
}

kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
    kotlinOptions.jvmTarget = jvmVersion.toString()
}

fun getFilePath(baseDir: String = "$rootDir/src", fileName: String): String {
    return fileTree(baseDir)
        .filter { it.isFile }.files
        .first { it.name == fileName }
        .absolutePath
}