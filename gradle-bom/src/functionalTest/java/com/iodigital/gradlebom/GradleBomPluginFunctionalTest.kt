package com.iodigital.gradlebom

import org.gradle.testkit.runner.GradleRunner
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files

class GradleBomPluginFunctionalTest {
    @Test
    @Throws(IOException::class)
    fun canRunTask() {
        // Setup the test build
        val projectDir = File("../test-target")
//        Files.createDirectories(projectDir.toPath())
//        writeString(File(projectDir, "settings.gradle"), "")
//        writeString(
//            File(projectDir, "build.gradle"),
//            """
//                plugins {
//                    id('com.iodigital.gradlebom.plugin')
//                }
//            """.trimIndent()
//        )

        // Run the build
        val result = GradleRunner.create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments(":app:generateBom")
            .withProjectDir(projectDir)
            .build()

        // Verify the result
        Assert.assertTrue(result.output.contains("Hello from plugin 'com.example.plugin.greeting'"))
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        FileWriter(file).use { writer ->
            writer.write(string)
        }
    }
}
