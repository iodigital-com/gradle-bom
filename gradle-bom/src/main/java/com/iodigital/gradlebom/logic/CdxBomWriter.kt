package com.iodigital.gradlebom.logic

import com.iodigital.gradlebom.models.CdxBom
import com.iodigital.gradlebom.models.GradleDependency
import org.gradle.internal.impldep.com.google.gson.GsonBuilder
import java.io.File

class CdxBomWriter {

    fun writeCdxBom(
        file: File,
        projectName: String,
        root: GradleDependency,
        dependencies: Map<String, GradleDependency>
    ) {
        file.parentFile.mkdirs()

        val cdx = CdxBom(
            metadata = CdxBom.Metadata(
                component = root.component.toCdx().copy(
                    description = projectName,
                    type = "application"
                )
            ),
            components = dependencies.values.map {
                it.component.toCdx()
            }.filter {
                it.`bom-ref` != root.component.reference
            },
            dependencies = dependencies.values.map {
                it.toCdx()
            }
        )

        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(cdx)
            .let { file.writeText(it) }

        println("Wrote BOM file to: ${file.absolutePath}")
    }
}