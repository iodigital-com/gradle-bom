plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    kotlin("jvm")
    id("com.gradle.plugin-publish") version "1.2.1"
    `maven-publish`
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    testImplementation("junit:junit:4.13")
    testImplementation("com.google.truth:truth:1.4.2")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.11.0")
}

val buildNumber = (project.findProperty("AzureBuildNumber") ?: "debug")
    .toString().replace(".", "-")

group = "com.iodigital.gradlebom"
version = "1.0.$buildNumber"

File(System.getenv("GITHUB_OUTPUT")).appendText("build_version=$version")
println("##vso[build.updatebuildnumber]name=${version},code=${buildNumber},buildId=${buildNumber}")

@Suppress("UnstableApiUsage")
gradlePlugin {
    website = "https://github.com/iodigital-com/gradle-bom"
    vcsUrl = "https://github.com/iodigital-com/gradle-bom"

    val gradleBom by plugins.creating {
        id = "com.iodigital.gradlebom"
        implementationClass = "com.iodigital.gradlebom.GradleBomPlugin"
        displayName = "Gradle BOM Generation"
        description = "CyclonDX Software Bill-of-Material generation"
        tags = listOf("bom", "sbom", "cyclonedx", "dependencytrack")
    }
}

// Add a source set and a task for a functional test suite
val functionalTest by sourceSets.creating
gradlePlugin.testSourceSets(functionalTest)

configurations[functionalTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())

val functionalTestTask = tasks.register<Test>("functionalTest") {
    testClassesDirs = functionalTest.output.classesDirs
    classpath =
        configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
}

tasks.check {
// Run the functional tests as part of `check`
    dependsOn(functionalTestTask)
}

kotlin {
    jvmToolchain(17)
}