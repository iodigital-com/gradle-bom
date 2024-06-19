package com.iodigital.gradlebom.logic

import org.gradle.api.Project
import java.io.BufferedReader
import java.io.File
import java.util.concurrent.CompletableFuture

class GradleDependencyTreeGenerator {
    fun <T> generateDependencyTree(
        command: String,
        project: Project,
        withOutput: (BufferedReader) -> T,
    ): T {
        // Mock for testing....ugly but ok for now
        // We can't launch a real subprocess in tests
        System.getenv()["mockDependencyTreeFile"]?.takeIf { it.isNotBlank() }?.let {
            return withOutput(File(it).bufferedReader())
        }

        val updatedCommand = modifyCommandLine(command, project.rootDir)
        println("Starting nested gradle invocation to generate dependency tree: $updatedCommand")
        println("============")
        val rt = Runtime.getRuntime()
        val pr = rt.exec(updatedCommand)
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
        root: File,
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