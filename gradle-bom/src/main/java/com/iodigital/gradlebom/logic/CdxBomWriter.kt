package com.iodigital.gradlebom.logic

import com.google.gson.GsonBuilder
import com.iodigital.gradlebom.models.CdxBom
import com.iodigital.gradlebom.models.GradleDependency
import java.io.OutputStream

class CdxBomWriter {

    fun writeCdxBom(
        output: OutputStream,
        projectName: String,
        root: GradleDependency,
        dependencies: Map<String, GradleDependency>
    ) {
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
            .toByteArray()
            .let { output.write(it) }
    }
}