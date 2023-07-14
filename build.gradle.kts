import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application

    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

group = "dev.orewaee"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}

application.mainClass.set("MainKt")
