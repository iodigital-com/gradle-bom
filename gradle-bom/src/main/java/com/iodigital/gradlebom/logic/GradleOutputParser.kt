package com.iodigital.gradlebom.logic

import com.iodigital.gradlebom.models.GradleDependency
import java.io.BufferedReader

class GradleOutputParser {

    fun parse(projectName: String, input: BufferedReader) = input.let {
        val lines = input.lineSequence()
            .dropWhile { !it.startsWith("+") }
            .takeWhile { it.isNotEmpty() }

        val (rootDependency, _) = parseDependency(listOf("project :$projectName") + lines)
        val gradleComponents = mutableMapOf<String, GradleDependency>()
        requireNotNull(rootDependency)
        extractDependencies(rootDependency, gradleComponents)
        rootDependency to gradleComponents
    }

    private fun extractDependencies(
        dependency: GradleDependency,
        collection: MutableMap<String, GradleDependency>,
    ) {
        val existing = collection[dependency.component.key]
        if (existing == null || existing.dependencies.isEmpty()) {
            collection[dependency.component.key] = dependency
        }

        dependency.dependencies.forEach {
            extractDependencies(it, collection)
        }
    }

    private fun parseDependency(lines: List<String>): Pair<GradleDependency?, List<String>> {
        val (preamble, content) = lines.first().extractPreamble()
        var myLines = lines.drop(1).takeWhile { line ->
            val (p, _) = line.extractPreamble()
            p.length > preamble.length
        }

        val children = mutableListOf<GradleDependency>()
        val myLinesCount = myLines.size
        while (myLines.isNotEmpty()) {
            val (dependency, newLines) = parseDependency(myLines)
            dependency?.let { children += it }
            myLines = newLines
        }

        return GradleDependency.Component.parse(content)?.let {
            GradleDependency(
                component = it,
                dependencies = children
            )
        } to lines.drop(myLinesCount + 1)
    }


    private fun String.extractPreamble(): Pair<String, String> {
        val preambleEnd = indexOfFirst { it.isLetter() }
        return substring(0, preambleEnd) to substring(preambleEnd)
    }
}
