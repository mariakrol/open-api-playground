import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.10"

    id("org.openapi.generator") version "6.3.0"
}

group = "com.makrol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.9.2"
val ktorVersion = "2.2.3"
val slf4jVersion = "2.0.6"

dependencies {
    //OpenApi client
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("io.ktor:ktor-client-auth:$ktorVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")

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
openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/src/main/resources/teamCityRestApi-v2018.1-swagger2.0.json")
    outputDir.set(generatedSourcesPath)
    apiPackage.set("com.makrol.teamcity.api.client.api")
    invokerPackage.set("com.makrol.teamcity.api.client.invoker")
    modelPackage.set("com.makrol.teamcity.api.client.model")
    configOptions.set(mapOf("library" to "multiplatform"))
}

kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
}