package com.iodigital.gradlebom.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class CdxBom(
    val bomFormat: String = "CycloneDX",
    val components: List<Component>,
    val dependencies: List<Dependency>,
    val metadata: Metadata,
    val serialNumber: String = "urn:uuid:${UUID.randomUUID()}",
    val specVersion: String = "1.5",
    val version: Int = 1,
) {
    data class Component(
        val `bom-ref`: String,
        val group: String? = null,
        val name: String,
        val properties: List<Property>? = null,
        val purl: String? = null,
        val type: String,
        val version: String?,
        val description: String? = null,
    ) {
        data class Property(
            val name: String,
            val value: String
        )
    }

    data class Dependency(
        val dependsOn: List<String>,
        val ref: String
    )

    data class Metadata(
        val authors: List<Author> = listOf(
            Author(name = "iO Digial")
        ),
        val component: Component,
        val timestamp: String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).format(
            Date()
        ),
        val tools: List<Tool> = listOf(
            Tool(
                name = "CDXgradle",
                vendor = "iO Digital",
                version = "1.0"
            )
        )
    ) {
        data class Author(
            val name: String
        )

        data class Tool(
            val name: String,
            val vendor: String,
            val version: String
        )
    }
}