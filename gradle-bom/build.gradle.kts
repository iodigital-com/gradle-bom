plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    kotlin("jvm")
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

dependencies {
    // Use JUnit test framework for unit tests
    testImplementation("junit:junit:4.13")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.google.code.gson:gson:2.11.0")
}

gradlePlugin {
    val gradleBom by plugins.creating {
        id = "com.iodigital.gradlebom.plugin"
        implementationClass = "com.iodigital.gradlebom.GradleBomPlugin"
    }
}

// Add a source set and a task for a functional test suite
val functionalTest by sourceSets.creating
gradlePlugin.testSourceSets(functionalTest)

configurations[functionalTest.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())

val functionalTestTask = tasks.register<Test>("functionalTest") {
    testClassesDirs = functionalTest.output.classesDirs
    classpath = configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
}

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTestTask)
}

kotlin {
    jvmToolchain(17)
}