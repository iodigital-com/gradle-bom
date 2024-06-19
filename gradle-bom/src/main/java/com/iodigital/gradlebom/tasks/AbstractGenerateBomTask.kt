package com.iodigital.gradlebom.tasks

import com.iodigital.gradlebom.logic.CdxBomWriter
import com.iodigital.gradlebom.logic.GradleDependencyTreeGenerator
import com.iodigital.gradlebom.logic.GradleOutputParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class AbstractGenerateBomTask : DefaultTask() {

    override fun getGroup() = "Software Bill-of-Materials"

    @Input
    private val projectName = project.name

    @Input
    private val rootDir = project.rootDir

    @Input
    private val buildDir = project.layout.buildDirectory

    @TaskAction
    fun generateBom() {
        val generator = GradleDependencyTreeGenerator()
        val parser = GradleOutputParser()
        val writer = CdxBomWriter()
        val command = createdNestedGradleCommand()

        val (root, dependencies) = generator.generateDependencyTree(command, rootDir) {
            parser.parse(
                projectName = projectName,
                input = it
            )
        }

        val outputFile = buildDir.file("outputs/bom.json").get().asFile
        outputFile.parentFile.mkdirs()
        outputFile.outputStream().use {
            writer.writeCdxBom(
                output = it,
                projectName = projectName,
                dependencies = dependencies,
                root = root,
            )
        }

        println("Wrote BOM file to: ${outputFile.absolutePath}")
    }

    protected abstract fun createdNestedGradleCommand(): String

}