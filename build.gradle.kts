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
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/src/main/resources/teamCityRestApi-v2018.1-swagger2.0.json")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.makrol.teamcity.api.client.api")
    invokerPackage.set("com.makrol.teamcity.api.client.invoker")
    modelPackage.set("com.makrol.teamcity.api.client.model")
}