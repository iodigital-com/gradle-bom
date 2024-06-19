package com.iodigital.gradlebom.tasks

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.options.Option

abstract class GenericGenerateModuleBomTask : AbstractGenerateBomTask() {

    override fun getDescription() = "Creates a CycloneDX BOM"

    @Input
    @Option(option = "configuration", description = "The configuration to create a bom for. Run \"./gradlew dependencies\" to get a list.")
    abstract fun getConfiguration(): Property<String>

    override fun createdNestedGradleCommand(): String {
        val configuration = getConfiguration().get()
        val command = "${project.path.removeSuffix(":")}:dependencies"
        return "./gradlew $command --configuration $configuration --no-daemon"
    }
}