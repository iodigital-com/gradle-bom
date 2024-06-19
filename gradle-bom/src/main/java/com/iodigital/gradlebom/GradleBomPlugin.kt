package com.iodigital.gradlebom

import com.iodigital.gradlebom.tasks.GenerateBuildEnvironmentBomTask
import com.iodigital.gradlebom.tasks.SpecificGenerateModuleBomTask
import com.iodigital.gradlebom.tasks.GenericGenerateModuleBomTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleBomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            val suffix = "RuntimeClasspath"
            project.configurations
                .filter { it.name.endsWith(suffix) }
                .forEach { config ->
                    project.tasks.register(
                        "generate${config.name.removeSuffix(suffix)}Bom",
                        SpecificGenerateModuleBomTask::class.java,
                        config.name
                    )
                }
        }

        project.tasks.register(
            "generateBuildEnvironmentBom",
            GenerateBuildEnvironmentBomTask::class.java
        )

        project.tasks.register("generateBom", GenericGenerateModuleBomTask::class.java)
    }
}
