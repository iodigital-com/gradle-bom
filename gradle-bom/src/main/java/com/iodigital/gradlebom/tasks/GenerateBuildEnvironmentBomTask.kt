package com.iodigital.gradlebom.tasks

open class GenerateBuildEnvironmentBomTask : AbstractGenerateBomTask() {

    override fun generateNestedGradleCommand() =
        "./gradlew buildEnvironment"
}