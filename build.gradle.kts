
plugins {
    application
}

repositories {
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

dependencies {
    implementation("org.gradle:gradle-tooling-api:6.0")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.28") {
        because("The tooling API need an SLF4J implementation available at runtime.")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "fr.jcgay.gw.Main"
}
