package com.iodigital.gradlebom.tasks

open class GenerateBuildEnvironmentBomTask : AbstractGenerateBomTask() {

    override fun createdNestedGradleCommand() =
        "./gradlew buildEnvironment"
}