package com.iodigital.gradlebom

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class GradleBomPluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.iodigital.gradlebom")

        // Verify the result
        println(project.tasks.map { it.name }.joinToString())
        Assert.assertNotNull(project.tasks.findByName("generateBuildEnvironmentBom"))
    }
}
