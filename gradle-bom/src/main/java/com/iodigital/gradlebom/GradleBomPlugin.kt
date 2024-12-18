package com.iodigital.gradlebom

import com.iodigital.gradlebom.tasks.GenerateBuildEnvironmentBomTask
import com.iodigital.gradlebom.tasks.SpecificGenerateModuleBomTask
import com.iodigital.gradlebom.tasks.GenericGenerateModuleBomTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleBomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.createTasks()

        project.tasks.register(
            "generateBuildEnvironmentBom",
            GenerateBuildEnvironmentBomTask::class.java
        )
    }

    private fun Project.createTasks(): Unit = afterEvaluate {
        val suffix = "Implementation"

        // Skip if already added
        if (!project.tasks.names.contains("generateBom")) {
            project.tasks.register(
                "generateBom",
                GenericGenerateModuleBomTask::class.java
            )

            configurations
                .filter { it.name.endsWith(suffix) }
                .map { it.name.removeSuffix(suffix) }
                .distinct()
                .sorted()
                .forEach { config ->
                    project.tasks.register(
                        "generate${config.replaceFirstChar { it.uppercase() }}Bom",
                        SpecificGenerateModuleBomTask::class.java,
                        config
                    )
                }
        }

        // Iterate over subprojects
        subprojects.forEach { sub ->
            sub.createTasks()
        }
    }
}
