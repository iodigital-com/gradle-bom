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
        val projectDir = File("build/functionalTest")
        val mockFile = File("test-inputs/gradle-output.txt")
        Files.createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(
            File(projectDir, "build.gradle"),
            """
                plugins {
                    id('com.iodigital.gradlebom')
                }
            """.trimIndent()
        )

        writeString(
            File(projectDir, "child/build.gradle"),
            """
               dependencies {
               }
            """.trimIndent()
        )

        writeString(
            File(projectDir, "child/child/build.gradle"),
            """
               dependencies {
               }
            """.trimIndent()
        )

        writeString(
            File(projectDir, "settings.gradle"),
            """
                include(":child")
                include(":child:child")
            """.trimIndent()
        )

        // Run the build
        val result = GradleRunner.create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments(":generateBom", "--configuration=test")
            .withProjectDir(projectDir)
            .withEnvironment(mapOf("mockDependencyTreeFile" to mockFile.absolutePath))
            .build()


        // Verify the result
        val bomFile = File("${projectDir.absolutePath}/build/outputs/bom.json")
        val bom = bomFile.reader().readText().stabilizeUuid()
        val expectedBom = File("test-inputs/expected-bom.json").reader().readText().stabilizeUuid()
        val expectedOutput = "Wrote BOM file to: $bomFile"
        Assert.assertTrue(
            "Didn't find in output: \"$expectedOutput\"",
            result.output.contains(expectedOutput),
        )
        Assert.assertEquals(
            "Expected BOM to be correct, but got",
            expectedBom,
            bom
        )
    }

    private fun String.stabilizeUuid(): String {
        val uuids = mutableMapOf<String, String>()
        val uuidRegex = Regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")
        val timestampRegex = Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")
        val result = uuidRegex.replace(this) { matchResult ->
            uuids.getOrPut(matchResult.value) { "{uuid:${uuids.size}}" }
        }
        return timestampRegex.replace(result, "{timestamp}")
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        file.parentFile.mkdirs()
        FileWriter(file).use { writer ->
            writer.write(string)
        }
    }
}
