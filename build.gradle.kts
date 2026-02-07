plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "org.sirox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

application {
    mainClass.set("org.sirox.Main")
}

dependencies {
    implementation("net.minestom:minestom:2026.01.08-1.21.11")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("de.articdive:jnoise-pipeline:4.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "org.sirox.Main"
        }
    }

    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        mergeServiceFiles()
        archiveClassifier.set("")
    }
}