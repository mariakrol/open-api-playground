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

dependencies {
    testImplementation(kotlin("test"))

    //OpenApi client
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

val generatedSourcesPath = "$buildDir/generated"
openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/src/main/resources/teamCityRestApi-v2018.1-swagger2.0.json")
    outputDir.set(generatedSourcesPath)
    apiPackage.set("com.makrol.teamcity.api.client.api")
    invokerPackage.set("com.makrol.teamcity.api.client.invoker")
    modelPackage.set("com.makrol.teamcity.api.client.model")
}

kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

tasks.withType<KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
    kotlinOptions.jvmTarget = "11"
}