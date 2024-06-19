package com.iodigital.gradlebom.tasks

open class GenerateBuildEnvironmentBomTask : AbstractGenerateBomTask() {

    override fun getDescription() = "Creates a CycloneDX BOM for the build environment"

    override fun createdNestedGradleCommand() =
        "./gradlew buildEnvironment"
}