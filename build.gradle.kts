plugins {
    application
}

repositories {
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

dependencies {
    implementation("org.gradle:gradle-tooling-api:6.0")
    implementation("org.jline:jline:3.13.1")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.28") {
        because("The tooling API need an SLF4J implementation available at runtime.")
    }

    val junitVersion = "5.5.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}

tasks.distZip {
    archiveFileName.set("gw.zip")
}

application {
    mainClassName = "fr.jcgay.gw.Main"
}
