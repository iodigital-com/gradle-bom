package com.iodigital.gradlebom

import com.iodigital.gradlebom.tasks.GenerateModuleBomTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleBomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("generateBom", GenerateModuleBomTask::class.java)
        project.tasks.register("generateBuildEnvironmentBom", GenerateModuleBomTask::class.java)
    }
}
