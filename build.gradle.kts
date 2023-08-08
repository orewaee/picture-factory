plugins {
    application

    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.orewaee"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}

application.mainClass.set("MainKt")

tasks {
    shadowJar {
        archiveFileName.set("picture-factory-$version.jar")
    }
}
