pluginManagement {
    plugins {
        kotlin("jvm") version "2.3.10"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "gradleBOM"

include("gradle-bom")
