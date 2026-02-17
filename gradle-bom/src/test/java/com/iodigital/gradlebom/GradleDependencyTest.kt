package com.iodigital.gradlebom

import com.google.common.truth.Truth.assertThat
import com.iodigital.gradlebom.models.GradleDependency.Component
import org.junit.Test

class GradleDependencyTest {

    @Test
    fun `WHEN simple library is parsed THEN purl is correct`() {
        val component = Component.parse("com.example:my-library:1.2.3")

        assertThat(component).isNotNull()
        val cdx = component!!.toCdx()
        assertThat(cdx.purl).isEqualTo("pkg:maven/com.example/my-library@1.2.3")
    }

    @Test
    fun `WHEN version change library is parsed THEN purl uses resolved version`() {
        val component = Component.parse("com.example:my-library:1.0.0 -> 2.0.0")

        assertThat(component).isNotNull()
        val cdx = component!!.toCdx()
        assertThat(cdx.purl).isEqualTo("pkg:maven/com.example/my-library@2.0.0")
    }

    @Test
    fun `WHEN library change is parsed THEN purl uses resolved group name and version`() {
        val component = Component.parse("com.old:old-lib:1.0.0 -> com.new:new-lib:3.0.0")

        assertThat(component).isNotNull()
        val cdx = component!!.toCdx()
        assertThat(cdx.purl).isEqualTo("pkg:maven/com.new/new-lib@3.0.0")
    }

    @Test
    fun `WHEN project is parsed THEN purl is null`() {
        val component = Component.parse("project :app")

        assertThat(component).isNotNull()
        val cdx = component!!.toCdx()
        assertThat(cdx.purl).isNull()
    }

    @Test
    fun `WHEN constraint dependency is parsed THEN result is null`() {
        val component = Component.parse("com.example:my-library:1.0.0 (c)")

        assertThat(component).isNull()
    }

    @Test
    fun `WHEN version change with annotation is parsed THEN purl uses resolved version`() {
        val component = Component.parse("org.jetbrains.kotlin:kotlin-stdlib:1.7.10 -> 2.0.0-RC3 (*)")

        assertThat(component).isNotNull()
        val cdx = component!!.toCdx()
        assertThat(cdx.purl).isEqualTo("pkg:maven/org.jetbrains.kotlin/kotlin-stdlib@2.0.0-RC3")
    }
}
