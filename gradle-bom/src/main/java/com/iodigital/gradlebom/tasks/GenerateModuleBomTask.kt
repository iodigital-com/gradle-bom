package com.iodigital.gradlebom.tasks

open class GenerateModuleBomTask : AbstractGenerateBomTask() {

    override fun generateNestedGradleCommand(): String {
        val configuration = project.findProperty("configuration")?.toString()
            ?: "releaseRuntimeClasspath"
        val command = "${project.path.removeSuffix(":")}:dependencies"
        return "./gradlew $command --configuration $configuration --no-daemon"
    }
}