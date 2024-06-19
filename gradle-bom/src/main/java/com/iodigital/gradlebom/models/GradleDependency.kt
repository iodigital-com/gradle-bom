package com.iodigital.gradlebom.models

import java.util.UUID

data class GradleDependency(
    val component: Component,
    val dependencies: List<GradleDependency>
) {
    fun toCdx() = CdxBom.Dependency(
        dependsOn = dependencies.map { it.component.reference }.distinct(),
        ref = component.reference
    )


    sealed class Component {
        companion object {
            private val idMap = mutableMapOf<String, String>()
            private val simpleLibrary =
                Regex("^(?<group>.+?):(?<name>.+?):(?<version>[^>]+?)( \\(.+?\\))?\$")
            private val versionChangeLibrary =
                Regex("^(?<group>.+?):(?<name>.+?)(:(.+?))? -> (?<version>.+?)( \\(.+\\))?\$")
            private val libraryChangeLibrary =
                Regex("^(.+?):(.+?)(:(.+?))? -> (?<group>.+):(?<name>.+?):(?<version>.+?)( \\(.+?\\))?\$")

            fun parse(component: String) = when {
                component.endsWith("(c)") -> null
                component.startsWith("project ") -> Project(component.split(" ")[1])
                else -> simpleLibrary.createLibrary(component)
                    ?: versionChangeLibrary.createLibrary(component)
                    ?: libraryChangeLibrary.createLibrary(component)
            }

            private fun Regex.createLibrary(component: String) = matchEntire(component)?.let {
                Library(
                    group = it.groups["group"]!!.value,
                    name = it.groups["name"]!!.value,
                    version = it.groups["version"]!!.value
                )
            }

            private fun createId(key: String) = idMap.getOrPut(key) { UUID.randomUUID().toString() }
        }

        abstract val key: String
        val reference: String get() = createId(key)

        abstract fun toCdx(): CdxBom.Component

        data class Project(
            val projectName: String,
        ) : Component() {
            override val key = projectName
            override fun toCdx() = CdxBom.Component(
                `bom-ref` = reference,
                name = projectName,
                type = "library",
                version = "SNAPSHOT",
            )
        }

        data class Library(
            val group: String,
            val name: String,
            val version: String
        ) : Component() {
            override val key = "$group:$name"
            override fun toCdx() = CdxBom.Component(
                `bom-ref` = reference,
                name = name,
                group = group,
                type = "library",
                version = version,
            )
        }
    }
}