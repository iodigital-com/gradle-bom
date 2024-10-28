package com.iodigital.gradlebom.tasks

import javax.inject.Inject

open class SpecificGenerateModuleBomTask @Inject constructor(
    private val configName: String,
) : AbstractGenerateBomTask() {

    private val projectPath = project.path

    override fun getDescription() = "Creates a CycloneDX BOM for configuration $configName"

    override fun createdNestedGradleCommand(): String {
        val command = "${projectPath.removeSuffix(":")}:dependencies"
        return "./gradlew $command --configuration ${configName}RuntimeClasspath --no-daemon"
    }
}