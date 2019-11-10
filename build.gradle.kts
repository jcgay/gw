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

    val junitVersion = "5.5.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClassName = "fr.jcgay.gw.Main"
}
