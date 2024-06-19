package com.iodigital.gradlebom.tasks

import com.iodigital.gradlebom.logic.CdxBomWriter
import com.iodigital.gradlebom.logic.GradleOutputParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.BufferedReader
import java.io.File
import java.util.concurrent.CompletableFuture

abstract class AbstractGenerateBomTask : DefaultTask() {

    @TaskAction
    fun generateBom() {
        val parser = GradleOutputParser()
        val writer = CdxBomWriter()

        val (root, dependencies) = generateDependencyTree {
            parser.parse(
                projectName = project.name,
                input = it
            )
        }

        writer.writeCdxBom(
            file = project.layout.buildDirectory.file("outputs/bom.json").get().asFile,
            projectName = project.name,
            dependencies = dependencies,
            root = root,
        )
    }

    protected abstract fun generateNestedGradleCommand(): String

    private fun <T> generateDependencyTree(
        withOutput: (BufferedReader) -> T,
    ): T {
        val command = modifyCommandLine(generateNestedGradleCommand())
        println("Starting nested gradle invocation to generate dependency tree: $command")
        println("============")
        val rt = Runtime.getRuntime()
        val pr = rt.exec(command)
        val copyStdErr = pr.copyStdErr()
        val result = pr.parseOutput(withOutput)
        val status = pr.waitFor()
        println("Nested gradle invocation done: $status")
        println("============")
        require(status == 0) { "Nested invocation failed" }
        copyStdErr.join()
        return result.join()
    }

    private fun modifyCommandLine(
        command: String,
        root: File = project.rootProject.rootDir
    ): String {
        require(root.absolutePath != "/") { "Did not find gradlew executable!" }
        val isWindows = System.getProperty("os.name").contains("win", ignoreCase = true)
        val executableName = if (isWindows) "gradlew.bat" else "gradlew"
        val executable = File(root, executableName)
        return if (executable.exists()) {
            command.replace("./gradlew", executable.absolutePath)
        } else {
            modifyCommandLine(command, root.parentFile)
        }
    }

    private fun Process.copyStdErr() = CompletableFuture.supplyAsync {
        do {
            val line = errorReader().readLine()?.also {
                System.err.println("NESTED: $it")
            }
        } while (line != null)
        System.err.flush()
    }

    private fun <T> Process.parseOutput(
        withOutput: (BufferedReader) -> T
    ) = CompletableFuture.supplyAsync {
        withOutput(inputReader())
    }
}