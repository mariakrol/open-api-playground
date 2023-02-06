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
dependencies {
    //OpenApi client
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2") // remove if gson is used
    // implementation("com.google.code.gson:gson:2.10.1") uncomment if serializationLibrary == gson

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
    // configOptions.set(mapOf("serializationLibrary" to "gson")) uncomment to use gson
    configOptions.set(mapOf("serializationLibrary" to "jackson")) //remove to use gson
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