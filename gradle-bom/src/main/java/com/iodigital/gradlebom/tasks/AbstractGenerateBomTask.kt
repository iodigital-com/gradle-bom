package com.iodigital.gradlebom.tasks

import com.iodigital.gradlebom.logic.CdxBomWriter
import com.iodigital.gradlebom.logic.GradleDependencyTreeGenerator
import com.iodigital.gradlebom.logic.GradleOutputParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class AbstractGenerateBomTask : DefaultTask() {

    @TaskAction
    fun generateBom() {
        val generator = GradleDependencyTreeGenerator()
        val parser = GradleOutputParser()
        val writer = CdxBomWriter()
        val command = createdNestedGradleCommand()

        val (root, dependencies) = generator.generateDependencyTree(command, project) {
            parser.parse(
                projectName = project.name,
                input = it
            )
        }

        val outputFile = project.layout.buildDirectory.file("outputs/bom.json").get().asFile
        outputFile.parentFile.mkdirs()
        outputFile.outputStream().use {
            writer.writeCdxBom(
                output = it,
                projectName = project.name,
                dependencies = dependencies,
                root = root,
            )
        }

        println("Wrote BOM file to: ${outputFile.absolutePath}")
    }

    protected abstract fun createdNestedGradleCommand(): String

}