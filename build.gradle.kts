plugins {
    java
    application
}

group = "org.sirox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.sirox.Main")
}

dependencies {
    implementation("net.minestom:minestom:2026.01.08-1.21.11")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}