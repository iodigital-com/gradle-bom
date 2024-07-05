package com.iodigital.gradlebom

import org.gradle.testkit.runner.GradleRunner
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files

class GradleBomPluginFunctionalTest {

    companion object {
        //region private const val output = ...
        private const val output = """
Reusing configuration cache.

> Task :app-test:dependencies

------------------------------------------------------------
Project ':app-test'
------------------------------------------------------------

releaseRuntimeClasspath - Runtime classpath of /release.
+--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3
|    +--- org.jetbrains:annotations:13.0 -> 23.0.0
|    +--- org.jetbrains.kotlin:kotlin-stdlib-common:2.0.0-RC3 (c)
|    +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0 -> 1.9.10 (c)
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0 -> 1.9.10 (c)
\--- project :base-android
     +--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     +--- project :base-shared
     |    +--- androidx.appcompat:appcompat:1.6.1
     |    |    +--- androidx.activity:activity:1.6.0 -> 1.9.0
     |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01
     |    |    |    |    \--- androidx.annotation:annotation-jvm:1.8.0-rc01
     |    |    |    |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.7.10 -> 2.0.0-RC3 (*)
     |    |    |    +--- androidx.collection:collection:1.0.0 -> 1.4.0
     |    |    |    |    \--- androidx.collection:collection-jvm:1.4.0
     |    |    |    |         +--- androidx.annotation:annotation:1.7.0 -> 1.8.0-rc01 (*)
     |    |    |    |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |         +--- androidx.collection:collection-ktx:1.4.0 (c)
     |    |    |    |         \--- androidx.collection:collection-ktx:1.3.0 -> 1.4.0 (c)
     |    |    |    +--- androidx.core:core:1.13.0
     |    |    |    |    +--- androidx.annotation:annotation:1.6.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.annotation:annotation-experimental:1.4.0
     |    |    |    |    |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.7.10 -> 2.0.0-RC3 (*)
     |    |    |    |    +--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |    |    |    +--- androidx.concurrent:concurrent-futures:1.0.0 -> 1.1.0
     |    |    |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |    \--- com.google.guava:listenablefuture:1.0
     |    |    |    |    +--- androidx.interpolator:interpolator:1.0.0
     |    |    |    |    |    \--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.6.2 -> 2.8.0-rc01
     |    |    |    |    |    \--- androidx.lifecycle:lifecycle-runtime-android:2.8.0-rc01
     |    |    |    |    |         +--- androidx.annotation:annotation:1.8.0-rc01 (*)
     |    |    |    |    |         +--- androidx.arch.core:core-common:2.2.0
     |    |    |    |    |         |    \--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |         +--- androidx.arch.core:core-runtime:2.2.0
     |    |    |    |    |         |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |         |    \--- androidx.arch.core:core-common:2.2.0 (*)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01
     |    |    |    |    |         |    \--- androidx.lifecycle:lifecycle-common-jvm:2.8.0-rc01
     |    |    |    |    |         |         +--- androidx.annotation:annotation:1.8.0-rc01 (*)
     |    |    |    |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3 -> 1.8.0
     |    |    |    |    |         |         |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0
     |    |    |    |    |         |         |         +--- org.jetbrains:annotations:23.0.0
     |    |    |    |    |         |         |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.0
     |    |    |    |    |         |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0 (c)
     |    |    |    |    |         |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0 (c)
     |    |    |    |    |         |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (c)
     |    |    |    |    |         |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.8.0 (c)
     |    |    |    |    |         |         |              \--- org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.8.0 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    |         |         +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         |         \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.profileinstaller:profileinstaller:1.3.1
     |    |    |    |    |         |    +--- androidx.annotation:annotation:1.2.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |         |    +--- androidx.concurrent:concurrent-futures:1.1.0 (*)
     |    |    |    |    |         |    +--- androidx.startup:startup-runtime:1.1.1
     |    |    |    |    |         |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |         |    |    \--- androidx.tracing:tracing:1.0.0
     |    |    |    |    |         |    |         \--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |         |    \--- com.google.guava:listenablefuture:1.0
     |    |    |    |    |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3 -> 1.8.0
     |    |    |    |    |         |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (*)
     |    |    |    |    |         |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.0 (*)
     |    |    |    |    |         |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.21 -> 2.0.0-RC3 (*)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    |         +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    |         \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.versionedparcelable:versionedparcelable:1.1.1
     |    |    |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |    \--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    \--- androidx.core:core-ktx:1.13.0 (c)
     |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.6.1 -> 2.8.0-rc01 (*)
     |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.6.1 -> 2.8.0-rc01
     |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel-android:2.8.0-rc01
     |    |    |    |         +--- androidx.annotation:annotation:1.8.0-rc01 (*)
     |    |    |    |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3 -> 1.8.0 (*)
     |    |    |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3 -> 1.8.0 (*)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |         +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |         \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1 -> 2.8.0-rc01
     |    |    |    |    +--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.core:core-ktx:1.2.0 -> 1.13.0
     |    |    |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |    +--- androidx.core:core:1.13.0 (*)
     |    |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |    \--- androidx.core:core:1.13.0 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01
     |    |    |    |    |    +--- androidx.arch.core:core-common:2.2.0 (*)
     |    |    |    |    |    +--- androidx.arch.core:core-runtime:2.2.0 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (*)
     |    |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (*)
     |    |    |    |    +--- androidx.savedstate:savedstate:1.2.1
     |    |    |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    |    +--- androidx.arch.core:core-common:2.1.0 -> 2.2.0 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.6.1 -> 2.8.0-rc01 (*)
     |    |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.10 -> 2.0.0-RC3 (*)
     |    |    |    |    |    \--- androidx.savedstate:savedstate-ktx:1.2.1 (c)
     |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3 -> 1.8.0 (*)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    \--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    +--- androidx.profileinstaller:profileinstaller:1.3.1 (*)
     |    |    |    +--- androidx.savedstate:savedstate:1.2.1 (*)
     |    |    |    +--- androidx.tracing:tracing:1.0.0 (*)
     |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    +--- androidx.activity:activity-compose:1.9.0 (c)
     |    |    |    \--- androidx.activity:activity-ktx:1.9.0 (c)
     |    |    +--- androidx.annotation:annotation:1.3.0 -> 1.8.0-rc01 (*)
     |    |    +--- androidx.appcompat:appcompat-resources:1.6.1
     |    |    |    +--- androidx.annotation:annotation:1.2.0 -> 1.8.0-rc01 (*)
     |    |    |    +--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |    |    +--- androidx.core:core:1.6.0 -> 1.13.0 (*)
     |    |    |    +--- androidx.vectordrawable:vectordrawable:1.1.0
     |    |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.core:core:1.1.0 -> 1.13.0 (*)
     |    |    |    |    \--- androidx.collection:collection:1.1.0 -> 1.4.0 (*)
     |    |    |    +--- androidx.vectordrawable:vectordrawable-animated:1.1.0
     |    |    |    |    +--- androidx.vectordrawable:vectordrawable:1.1.0 (*)
     |    |    |    |    +--- androidx.interpolator:interpolator:1.0.0 (*)
     |    |    |    |    \--- androidx.collection:collection:1.1.0 -> 1.4.0 (*)
     |    |    |    \--- androidx.appcompat:appcompat:1.6.1 (c)
     |    |    +--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |    +--- androidx.core:core:1.9.0 -> 1.13.0 (*)
     |    |    +--- androidx.core:core-ktx:1.8.0 -> 1.13.0 (*)
     |    |    +--- androidx.cursoradapter:cursoradapter:1.0.0
     |    |    |    \--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    +--- androidx.drawerlayout:drawerlayout:1.0.0
     |    |    |    +--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |    +--- androidx.core:core:1.0.0 -> 1.13.0 (*)
     |    |    |    \--- androidx.customview:customview:1.0.0
     |    |    |         +--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |         \--- androidx.core:core:1.0.0 -> 1.13.0 (*)
     |    |    +--- androidx.emoji2:emoji2:1.2.0 -> 1.3.0
     |    |    |    +--- androidx.annotation:annotation:1.2.0 -> 1.8.0-rc01 (*)
     |    |    |    +--- androidx.collection:collection:1.1.0 -> 1.4.0 (*)
     |    |    |    +--- androidx.core:core:1.3.0 -> 1.13.0 (*)
     |    |    |    +--- androidx.lifecycle:lifecycle-process:2.4.1 -> 2.8.0-rc01
     |    |    |    |    +--- androidx.annotation:annotation:1.2.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (*)
     |    |    |    |    +--- androidx.startup:startup-runtime:1.1.1 (*)
     |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    +--- androidx.startup:startup-runtime:1.0.0 -> 1.1.1 (*)
     |    |    |    \--- androidx.emoji2:emoji2-views-helper:1.3.0 (c)
     |    |    +--- androidx.emoji2:emoji2-views-helper:1.2.0 -> 1.3.0
     |    |    |    +--- androidx.collection:collection:1.1.0 -> 1.4.0 (*)
     |    |    |    +--- androidx.core:core:1.3.0 -> 1.13.0 (*)
     |    |    |    +--- androidx.emoji2:emoji2:1.3.0 (*)
     |    |    |    \--- androidx.emoji2:emoji2:1.3.0 (c)
     |    |    +--- androidx.fragment:fragment:1.3.6
     |    |    |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    |    +--- androidx.core:core:1.2.0 -> 1.13.0 (*)
     |    |    |    +--- androidx.collection:collection:1.1.0 -> 1.4.0 (*)
     |    |    |    +--- androidx.viewpager:viewpager:1.0.0
     |    |    |    |    +--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.core:core:1.0.0 -> 1.13.0 (*)
     |    |    |    |    \--- androidx.customview:customview:1.0.0 (*)
     |    |    |    +--- androidx.loader:loader:1.0.0
     |    |    |    |    +--- androidx.annotation:annotation:1.0.0 -> 1.8.0-rc01 (*)
     |    |    |    |    +--- androidx.core:core:1.0.0 -> 1.13.0 (*)
     |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata:2.0.0 -> 2.8.0-rc01
     |    |    |    |    |    +--- androidx.arch.core:core-common:2.2.0 (*)
     |    |    |    |    |    +--- androidx.arch.core:core-runtime:2.2.0 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (*)
     |    |    |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |    |    |    |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3 -> 1.8.0 (*)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |    |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |    |    |    \--- androidx.lifecycle:lifecycle-viewmodel:2.0.0 -> 2.8.0-rc01 (*)
     |    |    |    +--- androidx.activity:activity:1.2.4 -> 1.9.0 (*)
     |    |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.3.1 -> 2.8.0-rc01 (*)
     |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.3.1 -> 2.8.0-rc01 (*)
     |    |    |    +--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1 -> 2.8.0-rc01 (*)
     |    |    |    +--- androidx.savedstate:savedstate:1.1.0 -> 1.2.1 (*)
     |    |    |    \--- androidx.annotation:annotation-experimental:1.0.0 -> 1.4.0 (*)
     |    |    +--- androidx.lifecycle:lifecycle-runtime:2.5.1 -> 2.8.0-rc01 (*)
     |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.5.1 -> 2.8.0-rc01 (*)
     |    |    +--- androidx.resourceinspection:resourceinspection-annotation:1.0.1
     |    |    |    \--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |    +--- androidx.savedstate:savedstate:1.2.0 -> 1.2.1 (*)
     |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.7.10 -> 2.0.0-RC3 (*)
     |    |    \--- androidx.appcompat:appcompat-resources:1.6.1 (c)
     |    +--- io.ktor:ktor-client-okhttp:2.3.11
     |    |    \--- io.ktor:ktor-client-okhttp-jvm:2.3.11
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10
     |    |         |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.10 -> 2.0.0-RC3 (*)
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10
     |    |         |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.9.10 -> 2.0.0-RC3 (*)
     |    |         |    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.10 (*)
     |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0
     |    |         |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (*)
     |    |         |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.0 (*)
     |    |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         +--- io.ktor:ktor-client-core:2.3.11
     |    |         |    \--- io.ktor:ktor-client-core-jvm:2.3.11
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         +--- io.ktor:ktor-http:2.3.11
     |    |         |         |    \--- io.ktor:ktor-http-jvm:2.3.11
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |         \--- io.ktor:ktor-utils:2.3.11
     |    |         |         |              \--- io.ktor:ktor-utils-jvm:2.3.11
     |    |         |         |                   +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |                   +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |                   +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |                   +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |                   +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |                   \--- io.ktor:ktor-io:2.3.11
     |    |         |         |                        \--- io.ktor:ktor-io-jvm:2.3.11
     |    |         |         |                             +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |                             +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |                             +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |                             +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |                             \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         +--- io.ktor:ktor-events:2.3.11
     |    |         |         |    \--- io.ktor:ktor-events-jvm:2.3.11
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |         +--- io.ktor:ktor-http:2.3.11 (*)
     |    |         |         |         \--- io.ktor:ktor-utils:2.3.11 (*)
     |    |         |         +--- io.ktor:ktor-websocket-serialization:2.3.11
     |    |         |         |    \--- io.ktor:ktor-websocket-serialization-jvm:2.3.11
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |         +--- io.ktor:ktor-http:2.3.11 (*)
     |    |         |         |         \--- io.ktor:ktor-serialization:2.3.11
     |    |         |         |              \--- io.ktor:ktor-serialization-jvm:2.3.11
     |    |         |         |                   +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |                   +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |                   +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |                   +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |                   +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |                   +--- io.ktor:ktor-http:2.3.11 (*)
     |    |         |         |                   \--- io.ktor:ktor-websockets:2.3.11
     |    |         |         |                        \--- io.ktor:ktor-websockets-jvm:2.3.11
     |    |         |         |                             +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         |                             +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         |                             +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         |                             +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         |                             +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         |                             \--- io.ktor:ktor-http:2.3.11 (*)
     |    |         |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.7.1 -> 1.8.0
     |    |         |              +--- org.slf4j:slf4j-api:1.7.32 -> 1.7.36
     |    |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (*)
     |    |         |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.8.0 (*)
     |    |         |              \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.21 -> 2.0.0-RC3 (*)
     |    |         +--- com.squareup.okhttp3:okhttp:4.12.0
     |    |         |    +--- com.squareup.okio:okio:3.6.0 -> 3.8.0
     |    |         |    |    \--- com.squareup.okio:okio-jvm:3.8.0
     |    |         |    |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.21 -> 2.0.0-RC3 (*)
     |    |         |    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21 -> 1.9.10 (*)
     |    |         +--- com.squareup.okio:okio:3.7.0 -> 3.8.0 (*)
     |    |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (*)
     |    +--- io.github.aakira:napier:2.7.1
     |    |    \--- io.github.aakira:napier-android:2.7.1
     |    |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.21 -> 2.0.0-RC3 (*)
     |    +--- io.insert-koin:koin-core:3.5.6
     |    |    \--- io.insert-koin:koin-core-jvm:3.5.6
     |    |         +--- co.touchlab:stately-concurrency:2.0.6
     |    |         |    \--- co.touchlab:stately-concurrency-jvm:2.0.6
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10 (*)
     |    |         |         \--- co.touchlab:stately-strict:2.0.6
     |    |         |              \--- co.touchlab:stately-strict-jvm:2.0.6
     |    |         |                   \--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10 (*)
     |    |         +--- co.touchlab:stately-concurrent-collections:2.0.6
     |    |         |    \--- co.touchlab:stately-concurrent-collections-jvm:2.0.6
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10 (*)
     |    |         |         \--- co.touchlab:stately-concurrency:2.0.6 (*)
     |    |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.22 -> 2.0.0-RC3 (*)
     |    +--- org.jetbrains.kotlinx:kotlinx-datetime:0.6.0
     |    |    \--- org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.6.0
     |    |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.9.21 -> 2.0.0-RC3 (*)
     |    +--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     |    +--- io.ktor:ktor-client-content-negotiation:2.3.11
     |    |    \--- io.ktor:ktor-client-content-negotiation-jvm:2.3.11
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         +--- io.ktor:ktor-client-core:2.3.11 (*)
     |    |         \--- io.ktor:ktor-serialization:2.3.11 (*)
     |    +--- io.ktor:ktor-serialization-kotlinx-json:2.3.11
     |    |    \--- io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.11
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         +--- io.ktor:ktor-http:2.3.11 (*)
     |    |         +--- io.ktor:ktor-serialization-kotlinx:2.3.11
     |    |         |    \--- io.ktor:ktor-serialization-kotlinx-jvm:2.3.11
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22 -> 1.9.10 (*)
     |    |         |         +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22 -> 1.9.10 (*)
     |    |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.7.1 -> 1.8.0 (*)
     |    |         |         +--- org.slf4j:slf4j-api:1.7.36
     |    |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |         |         +--- io.ktor:ktor-http:2.3.11 (*)
     |    |         |         +--- io.ktor:ktor-serialization:2.3.11 (*)
     |    |         |         \--- org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1 -> 1.6.3
     |    |         |              \--- org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.3
     |    |         |                   +--- org.jetbrains.kotlin:kotlin-stdlib:1.9.22 -> 2.0.0-RC3 (*)
     |    |         |                   \--- org.jetbrains.kotlinx:kotlinx-serialization-bom:1.6.3
     |    |         |                        +--- org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3 (c)
     |    |         |                        +--- org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.6.3 (c)
     |    |         |                        +--- org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3 (c)
     |    |         |                        \--- org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.6.3 (c)
     |    |         \--- org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1 -> 1.6.3
     |    |              \--- org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.6.3
     |    |                   +--- org.jetbrains.kotlin:kotlin-stdlib:1.9.22 -> 2.0.0-RC3 (*)
     |    |                   +--- org.jetbrains.kotlinx:kotlinx-serialization-bom:1.6.3 (*)
     |    |                   \--- org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3 (*)
     |    +--- com.squareup.okio:okio:3.8.0 (*)
     |    +--- org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3 (*)
     |    \--- org.jetbrains.kotlin:kotlin-parcelize-runtime:2.0.0-RC3
     |         +--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     |         \--- org.jetbrains.kotlin:kotlin-android-extensions-runtime:2.0.0-RC3
     |              \--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     +--- project :ui-shared
     |    +--- org.jetbrains.compose.ui:ui-tooling-preview:1.6.10-rc02
     |    |    \--- androidx.compose.ui:ui-tooling-preview:1.6.7
     |    |         \--- androidx.compose.ui:ui-tooling-preview-android:1.6.7
     |    |              +--- androidx.annotation:annotation:1.2.0 -> 1.8.0-rc01 (*)
     |    |              +--- androidx.compose.runtime:runtime:1.6.7
     |    |              |    \--- androidx.compose.runtime:runtime-android:1.6.7
     |    |              |         +--- androidx.collection:collection:1.4.0 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.22 -> 2.0.0-RC3
     |    |              |         |    \--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     |    |              |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1 -> 1.8.0 (*)
     |    |              |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |              |         \--- androidx.compose.runtime:runtime-saveable:1.6.7 (c)
     |    |              +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.22 -> 2.0.0-RC3 (*)
     |    |              +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              \--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    +--- org.jetbrains.kotlin:kotlin-stdlib:2.0.0-RC3 (*)
     |    +--- project :base-shared (*)
     |    +--- org.jetbrains.compose.ui:ui:1.6.10-rc02
     |    |    \--- androidx.compose.ui:ui:1.6.7
     |    |         \--- androidx.compose.ui:ui-android:1.6.7
     |    |              +--- androidx.activity:activity-ktx:1.7.0 -> 1.9.0
     |    |              |    +--- androidx.activity:activity:1.9.0 (*)
     |    |              |    +--- androidx.core:core-ktx:1.13.0 (*)
     |    |              |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.6.1 -> 2.8.0-rc01
     |    |              |    |    \--- androidx.lifecycle:lifecycle-runtime-ktx-android:2.8.0-rc01
     |    |              |    |         +--- androidx.annotation:annotation:1.8.0-rc01 (*)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (*)
     |    |              |    |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |    |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3 -> 1.8.0 (*)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |              |    |         +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |              |    |         \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1 -> 2.8.0-rc01
     |    |              |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (*)
     |    |              |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |    |    +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3 -> 1.8.0 (*)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |              |    |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |              |    |    \--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |              |    +--- androidx.savedstate:savedstate-ktx:1.2.1
     |    |              |    |    +--- androidx.savedstate:savedstate:1.2.1 (*)
     |    |              |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.10 -> 2.0.0-RC3 (*)
     |    |              |    |    \--- androidx.savedstate:savedstate:1.2.1 (c)
     |    |              |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |    +--- androidx.activity:activity:1.9.0 (c)
     |    |              |    \--- androidx.activity:activity-compose:1.9.0 (c)
     |    |              +--- androidx.annotation:annotation:1.6.0 -> 1.8.0-rc01 (*)
     |    |              +--- androidx.autofill:autofill:1.0.0
     |    |              |    \--- androidx.core:core:1.1.0 -> 1.13.0 (*)
     |    |              +--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |              +--- androidx.collection:collection:1.4.0 (*)
     |    |              +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              +--- androidx.compose.runtime:runtime-saveable:1.6.7
     |    |              |    \--- androidx.compose.runtime:runtime-saveable-android:1.6.7
     |    |              |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         \--- androidx.compose.runtime:runtime:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-geometry:1.6.7
     |    |              |    \--- androidx.compose.ui:ui-geometry-android:1.6.7
     |    |              |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         +--- androidx.compose.runtime:runtime:1.2.1 -> 1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-util:1.6.7
     |    |              |         |    \--- androidx.compose.ui:ui-util-android:1.6.7
     |    |              |         |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         |         +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              |         |         \--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              |         \--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-graphics:1.6.7
     |    |              |    \--- androidx.compose.ui:ui-graphics-android:1.6.7
     |    |              |         +--- androidx.annotation:annotation:1.7.0 -> 1.8.0-rc01 (*)
     |    |              |         +--- androidx.collection:collection:1.4.0 (*)
     |    |              |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-unit:1.6.7
     |    |              |         |    \--- androidx.compose.ui:ui-unit-android:1.6.7
     |    |              |         |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         |         +--- androidx.collection:collection-ktx:1.2.0 -> 1.4.0
     |    |              |         |         |    +--- androidx.collection:collection:1.4.0 (*)
     |    |              |         |         |    \--- androidx.collection:collection:1.4.0 (c)
     |    |              |         |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui-geometry:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         |         +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              |         |         +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              |         |         \--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              |         \--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-text:1.6.7
     |    |              |    \--- androidx.compose.ui:ui-text-android:1.6.7
     |    |              |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         +--- androidx.collection:collection:1.0.0 -> 1.4.0 (*)
     |    |              |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         +--- androidx.compose.runtime:runtime-saveable:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-graphics:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-unit:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         +--- androidx.core:core:1.7.0 -> 1.13.0 (*)
     |    |              |         +--- androidx.emoji2:emoji2:1.2.0 -> 1.3.0 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |              |         +--- androidx.compose.ui:ui:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              |         +--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              |         \--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-unit:1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              +--- androidx.core:core:1.12.0 -> 1.13.0 (*)
     |    |              +--- androidx.customview:customview-poolingcontainer:1.0.0
     |    |              |    +--- androidx.core:core-ktx:1.5.0 -> 1.13.0 (*)
     |    |              |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.6.21 -> 2.0.0-RC3 (*)
     |    |              +--- androidx.emoji2:emoji2:1.2.0 -> 1.3.0 (*)
     |    |              +--- androidx.lifecycle:lifecycle-runtime:2.6.1 -> 2.8.0-rc01 (*)
     |    |              +--- androidx.lifecycle:lifecycle-viewmodel:2.6.1 -> 2.8.0-rc01 (*)
     |    |              +--- androidx.profileinstaller:profileinstaller:1.3.0 -> 1.3.1 (*)
     |    |              +--- androidx.savedstate:savedstate-ktx:1.2.1 (*)
     |    |              +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              +--- org.jetbrains.kotlin:kotlin-stdlib-common:1.8.22 -> 2.0.0-RC3 (*)
     |    |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1 -> 1.8.0 (*)
     |    |              +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |              +--- androidx.compose.ui:ui-geometry:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-graphics:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-text:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-tooling-preview:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-unit:1.6.7 (c)
     |    |              +--- androidx.compose.ui:ui-util:1.6.7 (c)
     |    |              \--- androidx.compose.foundation:foundation:1.4.0 -> 1.6.7 (c)
     |    +--- org.jetbrains.compose.runtime:runtime:1.6.10-rc02
     |    |    \--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    +--- org.jetbrains.compose.foundation:foundation:1.6.10-rc02
     |    |    \--- androidx.compose.foundation:foundation:1.6.7
     |    |         \--- androidx.compose.foundation:foundation-android:1.6.7
     |    |              +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              +--- androidx.collection:collection:1.4.0 (*)
     |    |              +--- androidx.compose.animation:animation:1.6.7
     |    |              |    \--- androidx.compose.animation:animation-android:1.6.7
     |    |              |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         +--- androidx.compose.animation:animation-core:1.6.7
     |    |              |         |    \--- androidx.compose.animation:animation-core-android:1.6.7
     |    |              |         |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         |         +--- androidx.collection:collection:1.4.0 (*)
     |    |              |         |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui-unit:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         |         +--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1 -> 1.8.0 (*)
     |    |              |         |         \--- androidx.compose.animation:animation:1.6.7 (c)
     |    |              |         +--- androidx.compose.foundation:foundation-layout:1.6.7
     |    |              |         |    \--- androidx.compose.foundation:foundation-layout-android:1.6.7
     |    |              |         |         +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |         |         +--- androidx.compose.animation:animation-core:1.2.1 -> 1.6.7 (*)
     |    |              |         |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui:1.6.7 (*)
     |    |              |         |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         |         +--- androidx.core:core:1.7.0 -> 1.13.0 (*)
     |    |              |         |         \--- androidx.compose.foundation:foundation:1.6.7 (c)
     |    |              |         +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-geometry:1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              |         \--- androidx.compose.animation:animation-core:1.6.7 (c)
     |    |              +--- androidx.compose.foundation:foundation-layout:1.6.7 (*)
     |    |              +--- androidx.compose.runtime:runtime:1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui:1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-text:1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-util:1.6.7 (*)
     |    |              +--- androidx.core:core:1.12.0 -> 1.13.0 (*)
     |    |              +--- androidx.emoji2:emoji2:1.3.0 (*)
     |    |              \--- androidx.compose.foundation:foundation-layout:1.6.7 (c)
     |    +--- org.jetbrains.compose.components:components-resources:1.6.10-rc02
     |    |    \--- org.jetbrains.compose.components:components-resources-android:1.6.10-rc02
     |    |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.9.23 -> 2.0.0-RC3 (*)
     |    |         +--- org.jetbrains.compose.runtime:runtime:1.6.10-rc02 (*)
     |    |         +--- org.jetbrains.compose.foundation:foundation:1.6.10-rc02 (*)
     |    |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0 (*)
     |    +--- org.jetbrains.compose.material3:material3:1.6.10-rc02
     |    |    \--- androidx.compose.material3:material3:1.2.1
     |    |         \--- androidx.compose.material3:material3-android:1.2.1
     |    |              +--- androidx.activity:activity-compose:1.5.0 -> 1.9.0
     |    |              |    +--- androidx.activity:activity-ktx:1.9.0 (*)
     |    |              |    +--- androidx.compose.runtime:runtime:1.0.1 -> 1.6.7 (*)
     |    |              |    +--- androidx.compose.runtime:runtime-saveable:1.0.1 -> 1.6.7 (*)
     |    |              |    +--- androidx.compose.ui:ui:1.0.1 -> 1.6.7 (*)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel:2.6.1 -> 2.8.0-rc01 (*)
     |    |              |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |    +--- androidx.activity:activity-ktx:1.9.0 (c)
     |    |              |    \--- androidx.activity:activity:1.9.0 (c)
     |    |              +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              +--- androidx.annotation:annotation-experimental:1.4.0 (*)
     |    |              +--- androidx.collection:collection:1.4.0 (*)
     |    |              +--- androidx.compose.animation:animation-core:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.foundation:foundation:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.foundation:foundation-layout:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.material:material-icons-core:1.6.0
     |    |              |    \--- androidx.compose.material:material-icons-core-android:1.6.0
     |    |              |         +--- androidx.compose.ui:ui:1.6.0 -> 1.6.7 (*)
     |    |              |         +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |    |              |         \--- androidx.compose.material:material-ripple:1.6.0 (c)
     |    |              +--- androidx.compose.material:material-ripple:1.6.0
     |    |              |    \--- androidx.compose.material:material-ripple-android:1.6.0
     |    |              |         +--- androidx.compose.animation:animation:1.6.0 -> 1.6.7 (*)
     |    |              |         +--- androidx.compose.foundation:foundation:1.6.0 -> 1.6.7 (*)
     |    |              |         +--- androidx.compose.runtime:runtime:1.6.0 -> 1.6.7 (*)
     |    |              |         +--- androidx.compose.ui:ui-util:1.6.0 -> 1.6.7 (*)
     |    |              |         \--- androidx.compose.material:material-icons-core:1.6.0 (c)
     |    |              +--- androidx.compose.runtime:runtime:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-graphics:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-text:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.compose.ui:ui-util:1.6.0 -> 1.6.7 (*)
     |    |              +--- androidx.lifecycle:lifecycle-common-java8:2.6.1 -> 2.8.0-rc01
     |    |              |    +--- androidx.annotation:annotation:1.1.0 -> 1.8.0-rc01 (*)
     |    |              |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (*)
     |    |              |    +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |    |              |    +--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |    |              |    \--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     |    |              +--- androidx.lifecycle:lifecycle-runtime:2.6.1 -> 2.8.0-rc01 (*)
     |    |              +--- androidx.lifecycle:lifecycle-viewmodel:2.6.1 -> 2.8.0-rc01 (*)
     |    |              \--- androidx.savedstate:savedstate-ktx:1.2.1 (*)
     |    \--- org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01
     |         \--- androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc01
     |              \--- androidx.lifecycle:lifecycle-viewmodel-compose-android:2.8.0-rc01
     |                   +--- androidx.annotation:annotation:1.8.0-rc01 (*)
     |                   +--- androidx.compose.runtime:runtime:1.6.0 -> 1.6.7 (*)
     |                   +--- androidx.compose.ui:ui:1.6.0 -> 1.6.7 (*)
     |                   +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (*)
     |                   +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (*)
     |                   +--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (*)
     |                   +--- org.jetbrains.kotlin:kotlin-stdlib:1.8.22 -> 2.0.0-RC3 (*)
     |                   +--- androidx.lifecycle:lifecycle-common:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-common-java8:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-livedata:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-livedata-core:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-runtime:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-viewmodel:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-rc01 (c)
     |                   +--- androidx.lifecycle:lifecycle-process:2.8.0-rc01 (c)
     |                   \--- androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.0-rc01 (c)
     +--- androidx.activity:activity-compose:1.9.0 (*)
     \--- androidx.compose.ui:ui-tooling-preview-android:1.6.7 (*)

(c) - A dependency constraint, not a dependency. The dependency affected by the constraint occurs elsewhere in the tree.
(*) - Indicates repeated occurrences of a transitive dependency subtree. Gradle expands transitive dependency subtrees only once per project; repeat occurrences only display the root of the subtree, followed by this annotation.

A web-based, searchable dependency report is available by adding the --scan option.

BUILD SUCCESSFUL in 533ms
1 actionable task: 1 executed
Configuration cache entry reused.
        """
        //endregion
    }

    val expectedBom = """
        {
          "bomFormat": "CycloneDX",
          "components": [
            {
              "bom-ref": "ca080353-40f8-4b4e-a1c4-43d919218ac7",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-stdlib",
              "type": "library",
              "version": "2.0.0-RC3"
            },
            {
              "bom-ref": "eee64069-284b-415a-98a0-4d9673e421d2",
              "group": "org.jetbrains",
              "name": "annotations",
              "type": "library",
              "version": "23.0.0"
            },
            {
              "bom-ref": "d8f98eb6-d21a-4615-a492-fb3173ad3918",
              "name": ":base-android",
              "type": "library",
              "version": "SNAPSHOT"
            },
            {
              "bom-ref": "916e9c2c-fe07-4a10-bf18-1de7a5d3ee26",
              "name": ":base-shared",
              "type": "library",
              "version": "SNAPSHOT"
            },
            {
              "bom-ref": "897cfc73-97ce-4995-9da7-4626ced1321d",
              "group": "androidx.appcompat",
              "name": "appcompat",
              "type": "library",
              "version": "1.6.1"
            },
            {
              "bom-ref": "be7811b2-dd4c-4779-b112-d08b2e4fb5c7",
              "group": "androidx.activity",
              "name": "activity",
              "type": "library",
              "version": "1.9.0"
            },
            {
              "bom-ref": "6189ccaa-387b-4bff-9263-35d1994ba13d",
              "group": "androidx.annotation",
              "name": "annotation",
              "type": "library",
              "version": "1.8.0-rc01"
            },
            {
              "bom-ref": "6d3c1ee3-64f4-483f-8fd8-462320893dfe",
              "group": "androidx.annotation",
              "name": "annotation-jvm",
              "type": "library",
              "version": "1.8.0-rc01"
            },
            {
              "bom-ref": "317688ca-8005-49cc-ae31-e8713f34c8c4",
              "group": "androidx.collection",
              "name": "collection",
              "type": "library",
              "version": "1.4.0"
            },
            {
              "bom-ref": "c8ac4cb9-37c8-4ea3-9e36-a335f2e778e9",
              "group": "androidx.collection",
              "name": "collection-jvm",
              "type": "library",
              "version": "1.4.0"
            },
            {
              "bom-ref": "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
              "group": "androidx.core",
              "name": "core",
              "type": "library",
              "version": "1.13.0"
            },
            {
              "bom-ref": "2ecd384d-270e-4ec1-b4fe-38174354457d",
              "group": "androidx.annotation",
              "name": "annotation-experimental",
              "type": "library",
              "version": "1.4.0"
            },
            {
              "bom-ref": "f40da505-d440-497f-af51-4383bc2c7cf4",
              "group": "androidx.concurrent",
              "name": "concurrent-futures",
              "type": "library",
              "version": "1.1.0"
            },
            {
              "bom-ref": "e360cb97-0946-4e32-975c-e64b8bcd1e62",
              "group": "com.google.guava",
              "name": "listenablefuture",
              "type": "library",
              "version": "1.0"
            },
            {
              "bom-ref": "d721a0fd-343a-44ec-8447-9a64276399c5",
              "group": "androidx.interpolator",
              "name": "interpolator",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "9bef8a59-6db0-42b1-a1d4-262730996b4b",
              "group": "androidx.lifecycle",
              "name": "lifecycle-runtime",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "2cdd8df5-2e9e-4484-ab43-3a09422146bc",
              "group": "androidx.lifecycle",
              "name": "lifecycle-runtime-android",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "373744cb-67c9-40bf-86e4-266d8634b508",
              "group": "androidx.arch.core",
              "name": "core-common",
              "type": "library",
              "version": "2.2.0"
            },
            {
              "bom-ref": "64c67398-0021-4461-a356-707915b69c21",
              "group": "androidx.arch.core",
              "name": "core-runtime",
              "type": "library",
              "version": "2.2.0"
            },
            {
              "bom-ref": "32ef4df7-62f4-47b1-b9bb-cf415e6d4465",
              "group": "androidx.lifecycle",
              "name": "lifecycle-common",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "7369f357-7de8-490f-ae75-acf74a33c7ab",
              "group": "androidx.lifecycle",
              "name": "lifecycle-common-jvm",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "e33f3a13-9b0b-448a-9827-e0d9c9607358",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-core",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "da78e10b-fe75-47b9-9a81-f51e5af816e7",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-core-jvm",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-bom",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "ac435bab-906f-46f8-9503-12011e5033c9",
              "group": "androidx.profileinstaller",
              "name": "profileinstaller",
              "type": "library",
              "version": "1.3.1"
            },
            {
              "bom-ref": "810aa4be-b383-4ccc-a89b-3f684f398d81",
              "group": "androidx.startup",
              "name": "startup-runtime",
              "type": "library",
              "version": "1.1.1"
            },
            {
              "bom-ref": "b6f1bdce-e71f-4b49-87a9-445b70eff1f6",
              "group": "androidx.tracing",
              "name": "tracing",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "8eb10f01-1144-47ac-a306-0ea59361fcb3",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-android",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "a049b76d-b855-4073-a252-33bf29411c94",
              "group": "androidx.versionedparcelable",
              "name": "versionedparcelable",
              "type": "library",
              "version": "1.1.1"
            },
            {
              "bom-ref": "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "fba9cf2c-dd8f-4e30-a893-f3cdbe7c4be1",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel-android",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "b6e53090-105e-43d4-af32-f1c17f8f8a17",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel-savedstate",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570",
              "group": "androidx.core",
              "name": "core-ktx",
              "type": "library",
              "version": "1.13.0"
            },
            {
              "bom-ref": "6a73dcdc-f5ec-472d-bb01-039afe771d01",
              "group": "androidx.lifecycle",
              "name": "lifecycle-livedata-core",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "e54adaf7-332e-4d36-8602-4131f98f3673",
              "group": "androidx.savedstate",
              "name": "savedstate",
              "type": "library",
              "version": "1.2.1"
            },
            {
              "bom-ref": "756ea591-bc2d-4384-9386-d1e19e3fc0d2",
              "group": "androidx.appcompat",
              "name": "appcompat-resources",
              "type": "library",
              "version": "1.6.1"
            },
            {
              "bom-ref": "6841efb0-e52a-4acb-b9f8-8c0019bb4f09",
              "group": "androidx.vectordrawable",
              "name": "vectordrawable",
              "type": "library",
              "version": "1.1.0"
            },
            {
              "bom-ref": "a68a0175-9c62-4736-9333-6edadaa6f544",
              "group": "androidx.vectordrawable",
              "name": "vectordrawable-animated",
              "type": "library",
              "version": "1.1.0"
            },
            {
              "bom-ref": "8ddbf1a3-4696-4377-9712-a17bf18f876e",
              "group": "androidx.cursoradapter",
              "name": "cursoradapter",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "e7190c43-f02b-480a-b957-c3a02052aac0",
              "group": "androidx.drawerlayout",
              "name": "drawerlayout",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "6bd040db-f72f-472c-ae5f-5f577b1c1618",
              "group": "androidx.customview",
              "name": "customview",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "6b1bb849-e457-46dd-bf64-98277bbabfaa",
              "group": "androidx.emoji2",
              "name": "emoji2",
              "type": "library",
              "version": "1.3.0"
            },
            {
              "bom-ref": "aa266273-9dd8-4d92-a530-2b2fe3cf7283",
              "group": "androidx.lifecycle",
              "name": "lifecycle-process",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "7486a41d-cf04-4b10-a0fd-176a1d156a74",
              "group": "androidx.emoji2",
              "name": "emoji2-views-helper",
              "type": "library",
              "version": "1.3.0"
            },
            {
              "bom-ref": "9a55d81f-8ea0-435c-933b-bcb429b189b3",
              "group": "androidx.fragment",
              "name": "fragment",
              "type": "library",
              "version": "1.3.6"
            },
            {
              "bom-ref": "f1cf458e-558a-415b-9f36-447c7678d215",
              "group": "androidx.viewpager",
              "name": "viewpager",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "fe4a3713-a973-4147-8105-3b5cccb914cf",
              "group": "androidx.loader",
              "name": "loader",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "78c5297a-3147-4b15-8b70-320f83653a46",
              "group": "androidx.lifecycle",
              "name": "lifecycle-livedata",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "72f8a418-7d2a-4df0-bda2-3ae49846b1cc",
              "group": "androidx.lifecycle",
              "name": "lifecycle-livedata-core-ktx",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "24965d8a-b591-420c-84e9-35535e62826c",
              "group": "androidx.resourceinspection",
              "name": "resourceinspection-annotation",
              "type": "library",
              "version": "1.0.1"
            },
            {
              "bom-ref": "1475ae1e-2eb8-482c-97ed-3a13ee217be9",
              "group": "io.ktor",
              "name": "ktor-client-okhttp",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "1637529b-cc06-49a3-bc13-44f0ca463607",
              "group": "io.ktor",
              "name": "ktor-client-okhttp-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "41e0b732-ad7b-4e06-8434-5d629ad42138",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-stdlib-jdk7",
              "type": "library",
              "version": "1.9.10"
            },
            {
              "bom-ref": "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-stdlib-jdk8",
              "type": "library",
              "version": "1.9.10"
            },
            {
              "bom-ref": "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-jdk8",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
              "group": "org.slf4j",
              "name": "slf4j-api",
              "type": "library",
              "version": "1.7.36"
            },
            {
              "bom-ref": "203d6912-ddea-42ca-9f86-3d79ba77b165",
              "group": "io.ktor",
              "name": "ktor-client-core",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "47defa27-eb63-4a54-933d-ba31a36b948b",
              "group": "io.ktor",
              "name": "ktor-client-core-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "97ad98d7-a97d-428c-bab8-39faa6c9778e",
              "group": "io.ktor",
              "name": "ktor-http",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "69040c5f-47ff-47c1-b9f0-564ed14a456e",
              "group": "io.ktor",
              "name": "ktor-http-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "771aa058-9ae4-4f46-a51d-bbc71be9eff8",
              "group": "io.ktor",
              "name": "ktor-utils",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "35a1b560-c887-41ef-bd82-e6e699eeb573",
              "group": "io.ktor",
              "name": "ktor-utils-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "8a7973fd-2cf3-4f15-89fa-e90b81d438cd",
              "group": "io.ktor",
              "name": "ktor-io",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "32794ad1-12c8-4b7a-8e4e-a50f3ed4fbea",
              "group": "io.ktor",
              "name": "ktor-io-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "8f7dde0c-ab68-4cb8-8762-6e792aa2ebd8",
              "group": "io.ktor",
              "name": "ktor-events",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "e55429ad-d1da-4a6d-b3e5-d946fc049aa4",
              "group": "io.ktor",
              "name": "ktor-events-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "b2fff050-5b43-4127-8fb8-b6f33b565cda",
              "group": "io.ktor",
              "name": "ktor-websocket-serialization",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "209b2a84-a173-4371-8d99-68e891b8e39f",
              "group": "io.ktor",
              "name": "ktor-websocket-serialization-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "8e8f827f-d501-48d1-a909-a2cc31b6118a",
              "group": "io.ktor",
              "name": "ktor-serialization",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "c02ec658-ef49-429f-ab35-4ad02c55cb4f",
              "group": "io.ktor",
              "name": "ktor-serialization-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "5ec3accc-1a2c-4f72-860b-12470b323a50",
              "group": "io.ktor",
              "name": "ktor-websockets",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "cb069e3b-d4b2-4c7f-b240-8f32179bbef2",
              "group": "io.ktor",
              "name": "ktor-websockets-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "1d5bb002-b42f-4f76-8f77-96fb1a41d84b",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-coroutines-slf4j",
              "type": "library",
              "version": "1.8.0"
            },
            {
              "bom-ref": "a590c31d-893c-47d9-bd36-cb76ca6ab09b",
              "group": "com.squareup.okhttp3",
              "name": "okhttp",
              "type": "library",
              "version": "4.12.0"
            },
            {
              "bom-ref": "fb821c0c-2139-4da1-9984-7a671f5b5848",
              "group": "com.squareup.okio",
              "name": "okio",
              "type": "library",
              "version": "3.8.0"
            },
            {
              "bom-ref": "4c74a302-81c6-4b71-ab5f-e4b4b4eb9212",
              "group": "com.squareup.okio",
              "name": "okio-jvm",
              "type": "library",
              "version": "3.8.0"
            },
            {
              "bom-ref": "39ebe53e-9701-48f8-8683-d2b76690485e",
              "group": "io.github.aakira",
              "name": "napier",
              "type": "library",
              "version": "2.7.1"
            },
            {
              "bom-ref": "66ca52c6-459d-48b0-8ad4-635877931b5a",
              "group": "io.github.aakira",
              "name": "napier-android",
              "type": "library",
              "version": "2.7.1"
            },
            {
              "bom-ref": "b488231b-ca44-41ca-af5e-bf2fb455d098",
              "group": "io.insert-koin",
              "name": "koin-core",
              "type": "library",
              "version": "3.5.6"
            },
            {
              "bom-ref": "89f2754f-4f00-4da5-99aa-e811b839f1c8",
              "group": "io.insert-koin",
              "name": "koin-core-jvm",
              "type": "library",
              "version": "3.5.6"
            },
            {
              "bom-ref": "bf7d1c49-6124-43a0-9875-f4898fd6bc45",
              "group": "co.touchlab",
              "name": "stately-concurrency",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "e282c814-0b4f-4243-ab5e-c3d2daf017ea",
              "group": "co.touchlab",
              "name": "stately-concurrency-jvm",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "55af0cfe-ad28-49fc-a361-3d592892e69f",
              "group": "co.touchlab",
              "name": "stately-strict",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "4e7dae0a-333e-4943-9e82-267670e9b4c3",
              "group": "co.touchlab",
              "name": "stately-strict-jvm",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "9da62063-f4e3-4567-b047-fc8c51de4ce0",
              "group": "co.touchlab",
              "name": "stately-concurrent-collections",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "f118f347-868b-4a76-a3e4-678d44b75b16",
              "group": "co.touchlab",
              "name": "stately-concurrent-collections-jvm",
              "type": "library",
              "version": "2.0.6"
            },
            {
              "bom-ref": "f230d072-b497-46d4-b3c2-3c069c00cf90",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-datetime",
              "type": "library",
              "version": "0.6.0"
            },
            {
              "bom-ref": "cd0b9890-c436-4a69-8794-d077d63dec0b",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-datetime-jvm",
              "type": "library",
              "version": "0.6.0"
            },
            {
              "bom-ref": "aa5d8951-ce78-4750-be1c-17ba96218e0c",
              "group": "io.ktor",
              "name": "ktor-client-content-negotiation",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "9cd5d51c-213b-4aa8-af2f-35ae009b2a5d",
              "group": "io.ktor",
              "name": "ktor-client-content-negotiation-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "76d1bd7b-7e9c-44d4-aede-82a3d96dd375",
              "group": "io.ktor",
              "name": "ktor-serialization-kotlinx-json",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "db740bb2-b1cf-472e-a175-afafcecddb0a",
              "group": "io.ktor",
              "name": "ktor-serialization-kotlinx-json-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "18c76d79-739e-498c-8ace-63bd9f5f1d50",
              "group": "io.ktor",
              "name": "ktor-serialization-kotlinx",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "4733f6c8-a1b0-470b-aed0-d6064bb4b982",
              "group": "io.ktor",
              "name": "ktor-serialization-kotlinx-jvm",
              "type": "library",
              "version": "2.3.11"
            },
            {
              "bom-ref": "d5ef3f0f-7464-4f69-a8e5-3d62b64765a6",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-serialization-core",
              "type": "library",
              "version": "1.6.3"
            },
            {
              "bom-ref": "cfad292d-66db-415a-9a5e-98565f6c8db5",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-serialization-core-jvm",
              "type": "library",
              "version": "1.6.3"
            },
            {
              "bom-ref": "19bd7968-3f6d-4839-91c7-ab7c0927e162",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-serialization-bom",
              "type": "library",
              "version": "1.6.3"
            },
            {
              "bom-ref": "98957b26-9c59-4177-9e46-da0bfbafcc86",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-serialization-json",
              "type": "library",
              "version": "1.6.3"
            },
            {
              "bom-ref": "b956ae69-dadb-4bf4-abb6-cda237f7fc17",
              "group": "org.jetbrains.kotlinx",
              "name": "kotlinx-serialization-json-jvm",
              "type": "library",
              "version": "1.6.3"
            },
            {
              "bom-ref": "2348b56a-fdcd-4f69-8dd1-ff82bfaf2dca",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-parcelize-runtime",
              "type": "library",
              "version": "2.0.0-RC3"
            },
            {
              "bom-ref": "d28beb07-a31d-47dd-9ec0-94681025dd4a",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-android-extensions-runtime",
              "type": "library",
              "version": "2.0.0-RC3"
            },
            {
              "bom-ref": "ece9f494-434d-49c1-ba40-b9c535d35ef7",
              "name": ":ui-shared",
              "type": "library",
              "version": "SNAPSHOT"
            },
            {
              "bom-ref": "651428e7-6f21-4db1-a124-9c4c4a53409e",
              "group": "org.jetbrains.compose.ui",
              "name": "ui-tooling-preview",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "972127a0-19ad-4a57-b8dc-beefc40e70b9",
              "group": "androidx.compose.ui",
              "name": "ui-tooling-preview",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "afcc7b44-b0a9-4b0d-9b35-85e3ba45b496",
              "group": "androidx.compose.ui",
              "name": "ui-tooling-preview-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "c01bd576-a8d7-4273-bdcf-28ae49d72186",
              "group": "androidx.compose.runtime",
              "name": "runtime",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "63caaafe-5063-49f9-8f48-50a7c5a7fa38",
              "group": "androidx.compose.runtime",
              "name": "runtime-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "6da0a2a8-be55-4294-8e91-0466f52e461c",
              "group": "org.jetbrains.kotlin",
              "name": "kotlin-stdlib-common",
              "type": "library",
              "version": "2.0.0-RC3"
            },
            {
              "bom-ref": "3dac96be-33b7-473f-99a9-b6f773c47093",
              "group": "org.jetbrains.compose.ui",
              "name": "ui",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
              "group": "androidx.compose.ui",
              "name": "ui",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "565d1c6b-3cea-4250-8b18-edafd83e7791",
              "group": "androidx.compose.ui",
              "name": "ui-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "5f192495-3e66-47db-9c75-2c2ab029c934",
              "group": "androidx.activity",
              "name": "activity-ktx",
              "type": "library",
              "version": "1.9.0"
            },
            {
              "bom-ref": "ae3b5eb4-18eb-413e-9a28-eae6118a0062",
              "group": "androidx.lifecycle",
              "name": "lifecycle-runtime-ktx",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "9a3e14cc-3ed5-4c65-90a7-ba68f9bdc814",
              "group": "androidx.lifecycle",
              "name": "lifecycle-runtime-ktx-android",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "fd1d6dc3-9ad0-496e-b625-68bba82e386e",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel-ktx",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "1bbc6251-35a2-4e9d-9a8c-12d446fd59e7",
              "group": "androidx.savedstate",
              "name": "savedstate-ktx",
              "type": "library",
              "version": "1.2.1"
            },
            {
              "bom-ref": "7fe69f5b-fdeb-4de8-9d48-f09741e357e2",
              "group": "androidx.autofill",
              "name": "autofill",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "6d98cb55-b2e1-42d8-b18a-0c8e8fabfbab",
              "group": "androidx.compose.runtime",
              "name": "runtime-saveable",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "adebe7fb-b0a2-49c3-8bf0-f1f59e013784",
              "group": "androidx.compose.runtime",
              "name": "runtime-saveable-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "91450b75-05b0-4e32-bb6c-36eb2103d305",
              "group": "androidx.compose.ui",
              "name": "ui-geometry",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "ef329d79-0d73-4f65-9ecd-c13fd8252372",
              "group": "androidx.compose.ui",
              "name": "ui-geometry-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "6a220678-e513-492d-b0a3-f4291e67b0ac",
              "group": "androidx.compose.ui",
              "name": "ui-util",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "d9416bbd-17e5-4fcd-a8f0-13bad793849b",
              "group": "androidx.compose.ui",
              "name": "ui-util-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "666c0f3c-8df1-43f9-8ed4-badd81e5593f",
              "group": "androidx.compose.ui",
              "name": "ui-graphics",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "13c24104-f06e-4caa-9769-05f3d0a9e353",
              "group": "androidx.compose.ui",
              "name": "ui-graphics-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "136a31c0-636f-4117-b116-c11fad8e50b7",
              "group": "androidx.compose.ui",
              "name": "ui-unit",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "c5062078-aba0-43d9-b415-e94aeaf264d8",
              "group": "androidx.compose.ui",
              "name": "ui-unit-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "4dd094bb-826a-4fcd-a18a-1f7b606155a9",
              "group": "androidx.collection",
              "name": "collection-ktx",
              "type": "library",
              "version": "1.4.0"
            },
            {
              "bom-ref": "10070bc0-ab7d-4373-a926-a1733e40de2a",
              "group": "androidx.compose.ui",
              "name": "ui-text",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "4158fabe-b8ef-4006-ad6b-cf66b4dc1cda",
              "group": "androidx.compose.ui",
              "name": "ui-text-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "5bf91f73-e9a5-4e33-95ca-19bbdef8c7fb",
              "group": "androidx.customview",
              "name": "customview-poolingcontainer",
              "type": "library",
              "version": "1.0.0"
            },
            {
              "bom-ref": "09a764e5-d49d-412c-b1b8-49f5f1840511",
              "group": "org.jetbrains.compose.runtime",
              "name": "runtime",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "2e9a3e90-efa9-43bf-950d-fbad303f1622",
              "group": "org.jetbrains.compose.foundation",
              "name": "foundation",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "6b6d9950-b284-43d9-8db1-01cd61dae339",
              "group": "androidx.compose.foundation",
              "name": "foundation",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "13fa09df-d8e4-4654-8ed6-eb4b19a8610d",
              "group": "androidx.compose.foundation",
              "name": "foundation-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "63c531e3-f7df-4ecd-820f-ab5c5e5c18d7",
              "group": "androidx.compose.animation",
              "name": "animation",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "a91e33b6-95e5-43d1-bce3-93f3d8dc6035",
              "group": "androidx.compose.animation",
              "name": "animation-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "1b6176e6-6706-4ae6-9a55-a317e4a3e8d4",
              "group": "androidx.compose.animation",
              "name": "animation-core",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "3f7f6ffa-0187-4560-a4ee-da48caee64ce",
              "group": "androidx.compose.animation",
              "name": "animation-core-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "0b15e13a-c0a5-4920-893d-4dd323e99d68",
              "group": "androidx.compose.foundation",
              "name": "foundation-layout",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "badc98f6-da20-4285-bfe3-8a43947b5c99",
              "group": "androidx.compose.foundation",
              "name": "foundation-layout-android",
              "type": "library",
              "version": "1.6.7"
            },
            {
              "bom-ref": "66781686-4303-4eec-9c17-fad95219ff85",
              "group": "org.jetbrains.compose.components",
              "name": "components-resources",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "b12698ee-fedd-4e21-81ec-6582aa913ae2",
              "group": "org.jetbrains.compose.components",
              "name": "components-resources-android",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "338c88ae-806c-4b8c-bb6f-3a891e2f9ad4",
              "group": "org.jetbrains.compose.material3",
              "name": "material3",
              "type": "library",
              "version": "1.6.10-rc02"
            },
            {
              "bom-ref": "d046da35-6c9e-43d2-ba43-6b22f96a7552",
              "group": "androidx.compose.material3",
              "name": "material3",
              "type": "library",
              "version": "1.2.1"
            },
            {
              "bom-ref": "df523397-d2f5-4680-8622-8b1a3bdaba04",
              "group": "androidx.compose.material3",
              "name": "material3-android",
              "type": "library",
              "version": "1.2.1"
            },
            {
              "bom-ref": "9646e672-d159-4021-b7c2-69d2a886018d",
              "group": "androidx.activity",
              "name": "activity-compose",
              "type": "library",
              "version": "1.9.0"
            },
            {
              "bom-ref": "6942f9b1-3bac-4e15-b6f8-d9461e12886a",
              "group": "androidx.compose.material",
              "name": "material-icons-core",
              "type": "library",
              "version": "1.6.0"
            },
            {
              "bom-ref": "a77775e1-dd7d-4e5c-bd5b-dcda06a2dcca",
              "group": "androidx.compose.material",
              "name": "material-icons-core-android",
              "type": "library",
              "version": "1.6.0"
            },
            {
              "bom-ref": "4933ece0-9b12-4dfb-8373-526e24b53db4",
              "group": "androidx.compose.material",
              "name": "material-ripple",
              "type": "library",
              "version": "1.6.0"
            },
            {
              "bom-ref": "54cb5135-df9c-4ee3-a0a0-0a1c713c3e2f",
              "group": "androidx.compose.material",
              "name": "material-ripple-android",
              "type": "library",
              "version": "1.6.0"
            },
            {
              "bom-ref": "e3954d11-5739-4159-84c1-becd506c827f",
              "group": "androidx.lifecycle",
              "name": "lifecycle-common-java8",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "1a997744-b9f1-4a25-8eac-acd3d2525507",
              "group": "org.jetbrains.androidx.lifecycle",
              "name": "lifecycle-viewmodel-compose",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "a541475f-f7d6-4736-b435-cb96936b81b8",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel-compose",
              "type": "library",
              "version": "2.8.0-rc01"
            },
            {
              "bom-ref": "bb5739d1-8e13-49d7-b833-e7e7f581d7b4",
              "group": "androidx.lifecycle",
              "name": "lifecycle-viewmodel-compose-android",
              "type": "library",
              "version": "2.8.0-rc01"
            }
          ],
          "dependencies": [
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "d8f98eb6-d21a-4615-a492-fb3173ad3918"
              ],
              "ref": "20df021b-da0f-4c7d-b4ae-54d61fc8c1dc"
            },
            {
              "dependsOn": [
                "eee64069-284b-415a-98a0-4d9673e421d2"
              ],
              "ref": "ca080353-40f8-4b4e-a1c4-43d919218ac7"
            },
            {
              "dependsOn": [],
              "ref": "eee64069-284b-415a-98a0-4d9673e421d2"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "916e9c2c-fe07-4a10-bf18-1de7a5d3ee26",
                "ece9f494-434d-49c1-ba40-b9c535d35ef7",
                "9646e672-d159-4021-b7c2-69d2a886018d",
                "afcc7b44-b0a9-4b0d-9b35-85e3ba45b496"
              ],
              "ref": "d8f98eb6-d21a-4615-a492-fb3173ad3918"
            },
            {
              "dependsOn": [
                "897cfc73-97ce-4995-9da7-4626ced1321d",
                "1475ae1e-2eb8-482c-97ed-3a13ee217be9",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "39ebe53e-9701-48f8-8683-d2b76690485e",
                "b488231b-ca44-41ca-af5e-bf2fb455d098",
                "f230d072-b497-46d4-b3c2-3c069c00cf90",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "aa5d8951-ce78-4750-be1c-17ba96218e0c",
                "76d1bd7b-7e9c-44d4-aede-82a3d96dd375",
                "fb821c0c-2139-4da1-9984-7a671f5b5848",
                "98957b26-9c59-4177-9e46-da0bfbafcc86",
                "2348b56a-fdcd-4f69-8dd1-ff82bfaf2dca"
              ],
              "ref": "916e9c2c-fe07-4a10-bf18-1de7a5d3ee26"
            },
            {
              "dependsOn": [
                "be7811b2-dd4c-4779-b112-d08b2e4fb5c7",
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "756ea591-bc2d-4384-9386-d1e19e3fc0d2",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570",
                "8ddbf1a3-4696-4377-9712-a17bf18f876e",
                "e7190c43-f02b-480a-b957-c3a02052aac0",
                "6b1bb849-e457-46dd-bf64-98277bbabfaa",
                "7486a41d-cf04-4b10-a0fd-176a1d156a74",
                "9a55d81f-8ea0-435c-933b-bcb429b189b3",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "24965d8a-b591-420c-84e9-35535e62826c",
                "e54adaf7-332e-4d36-8602-4131f98f3673",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "897cfc73-97ce-4995-9da7-4626ced1321d"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "b6e53090-105e-43d4-af32-f1c17f8f8a17",
                "ac435bab-906f-46f8-9503-12011e5033c9",
                "e54adaf7-332e-4d36-8602-4131f98f3673",
                "b6f1bdce-e71f-4b49-87a9-445b70eff1f6",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "be7811b2-dd4c-4779-b112-d08b2e4fb5c7"
            },
            {
              "dependsOn": [
                "6d3c1ee3-64f4-483f-8fd8-462320893dfe"
              ],
              "ref": "6189ccaa-387b-4bff-9263-35d1994ba13d"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "6d3c1ee3-64f4-483f-8fd8-462320893dfe"
            },
            {
              "dependsOn": [
                "c8ac4cb9-37c8-4ea3-9e36-a335f2e778e9"
              ],
              "ref": "317688ca-8005-49cc-ae31-e8713f34c8c4"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "c8ac4cb9-37c8-4ea3-9e36-a335f2e778e9"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "2ecd384d-270e-4ec1-b4fe-38174354457d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "f40da505-d440-497f-af51-4383bc2c7cf4",
                "d721a0fd-343a-44ec-8447-9a64276399c5",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "a049b76d-b855-4073-a252-33bf29411c94",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "2ecd384d-270e-4ec1-b4fe-38174354457d"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "e360cb97-0946-4e32-975c-e64b8bcd1e62"
              ],
              "ref": "f40da505-d440-497f-af51-4383bc2c7cf4"
            },
            {
              "dependsOn": [],
              "ref": "e360cb97-0946-4e32-975c-e64b8bcd1e62"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d"
              ],
              "ref": "d721a0fd-343a-44ec-8447-9a64276399c5"
            },
            {
              "dependsOn": [
                "2cdd8df5-2e9e-4484-ab43-3a09422146bc"
              ],
              "ref": "9bef8a59-6db0-42b1-a1d4-262730996b4b"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "373744cb-67c9-40bf-86e4-266d8634b508",
                "64c67398-0021-4461-a356-707915b69c21",
                "32ef4df7-62f4-47b1-b9bb-cf415e6d4465",
                "ac435bab-906f-46f8-9503-12011e5033c9",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3"
              ],
              "ref": "2cdd8df5-2e9e-4484-ab43-3a09422146bc"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d"
              ],
              "ref": "373744cb-67c9-40bf-86e4-266d8634b508"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "373744cb-67c9-40bf-86e4-266d8634b508"
              ],
              "ref": "64c67398-0021-4461-a356-707915b69c21"
            },
            {
              "dependsOn": [
                "7369f357-7de8-490f-ae75-acf74a33c7ab"
              ],
              "ref": "32ef4df7-62f4-47b1-b9bb-cf415e6d4465"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "7369f357-7de8-490f-ae75-acf74a33c7ab"
            },
            {
              "dependsOn": [
                "da78e10b-fe75-47b9-9a81-f51e5af816e7"
              ],
              "ref": "e33f3a13-9b0b-448a-9827-e0d9c9607358"
            },
            {
              "dependsOn": [
                "eee64069-284b-415a-98a0-4d9673e421d2",
                "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6"
              ],
              "ref": "da78e10b-fe75-47b9-9a81-f51e5af816e7"
            },
            {
              "dependsOn": [],
              "ref": "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "f40da505-d440-497f-af51-4383bc2c7cf4",
                "810aa4be-b383-4ccc-a89b-3f684f398d81",
                "e360cb97-0946-4e32-975c-e64b8bcd1e62"
              ],
              "ref": "ac435bab-906f-46f8-9503-12011e5033c9"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "b6f1bdce-e71f-4b49-87a9-445b70eff1f6"
              ],
              "ref": "810aa4be-b383-4ccc-a89b-3f684f398d81"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d"
              ],
              "ref": "b6f1bdce-e71f-4b49-87a9-445b70eff1f6"
            },
            {
              "dependsOn": [
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "8eb10f01-1144-47ac-a306-0ea59361fcb3"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4"
              ],
              "ref": "a049b76d-b855-4073-a252-33bf29411c94"
            },
            {
              "dependsOn": [
                "fba9cf2c-dd8f-4e30-a893-f3cdbe7c4be1"
              ],
              "ref": "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "fba9cf2c-dd8f-4e30-a893-f3cdbe7c4be1"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570",
                "6a73dcdc-f5ec-472d-bb01-039afe771d01",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "e54adaf7-332e-4d36-8602-4131f98f3673",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3"
              ],
              "ref": "b6e53090-105e-43d4-af32-f1c17f8f8a17"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570"
            },
            {
              "dependsOn": [
                "373744cb-67c9-40bf-86e4-266d8634b508",
                "64c67398-0021-4461-a356-707915b69c21",
                "32ef4df7-62f4-47b1-b9bb-cf415e6d4465",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "6a73dcdc-f5ec-472d-bb01-039afe771d01"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "373744cb-67c9-40bf-86e4-266d8634b508",
                "32ef4df7-62f4-47b1-b9bb-cf415e6d4465",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "e54adaf7-332e-4d36-8602-4131f98f3673"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6841efb0-e52a-4acb-b9f8-8c0019bb4f09",
                "a68a0175-9c62-4736-9333-6edadaa6f544"
              ],
              "ref": "756ea591-bc2d-4384-9386-d1e19e3fc0d2"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "317688ca-8005-49cc-ae31-e8713f34c8c4"
              ],
              "ref": "6841efb0-e52a-4acb-b9f8-8c0019bb4f09"
            },
            {
              "dependsOn": [
                "6841efb0-e52a-4acb-b9f8-8c0019bb4f09",
                "d721a0fd-343a-44ec-8447-9a64276399c5",
                "317688ca-8005-49cc-ae31-e8713f34c8c4"
              ],
              "ref": "a68a0175-9c62-4736-9333-6edadaa6f544"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d"
              ],
              "ref": "8ddbf1a3-4696-4377-9712-a17bf18f876e"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6bd040db-f72f-472c-ae5f-5f577b1c1618"
              ],
              "ref": "e7190c43-f02b-480a-b957-c3a02052aac0"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd"
              ],
              "ref": "6bd040db-f72f-472c-ae5f-5f577b1c1618"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "aa266273-9dd8-4d92-a530-2b2fe3cf7283",
                "810aa4be-b383-4ccc-a89b-3f684f398d81"
              ],
              "ref": "6b1bb849-e457-46dd-bf64-98277bbabfaa"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "810aa4be-b383-4ccc-a89b-3f684f398d81",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "aa266273-9dd8-4d92-a530-2b2fe3cf7283"
            },
            {
              "dependsOn": [
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6b1bb849-e457-46dd-bf64-98277bbabfaa"
              ],
              "ref": "7486a41d-cf04-4b10-a0fd-176a1d156a74"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "f1cf458e-558a-415b-9f36-447c7678d215",
                "fe4a3713-a973-4147-8105-3b5cccb914cf",
                "be7811b2-dd4c-4779-b112-d08b2e4fb5c7",
                "6a73dcdc-f5ec-472d-bb01-039afe771d01",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "b6e53090-105e-43d4-af32-f1c17f8f8a17",
                "e54adaf7-332e-4d36-8602-4131f98f3673",
                "2ecd384d-270e-4ec1-b4fe-38174354457d"
              ],
              "ref": "9a55d81f-8ea0-435c-933b-bcb429b189b3"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6bd040db-f72f-472c-ae5f-5f577b1c1618"
              ],
              "ref": "f1cf458e-558a-415b-9f36-447c7678d215"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "78c5297a-3147-4b15-8b70-320f83653a46",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6"
              ],
              "ref": "fe4a3713-a973-4147-8105-3b5cccb914cf"
            },
            {
              "dependsOn": [
                "373744cb-67c9-40bf-86e4-266d8634b508",
                "64c67398-0021-4461-a356-707915b69c21",
                "6a73dcdc-f5ec-472d-bb01-039afe771d01",
                "72f8a418-7d2a-4df0-bda2-3ae49846b1cc",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "78c5297a-3147-4b15-8b70-320f83653a46"
            },
            {
              "dependsOn": [
                "6a73dcdc-f5ec-472d-bb01-039afe771d01",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "72f8a418-7d2a-4df0-bda2-3ae49846b1cc"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d"
              ],
              "ref": "24965d8a-b591-420c-84e9-35535e62826c"
            },
            {
              "dependsOn": [
                "1637529b-cc06-49a3-bc13-44f0ca463607"
              ],
              "ref": "1475ae1e-2eb8-482c-97ed-3a13ee217be9"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "203d6912-ddea-42ca-9f86-3d79ba77b165",
                "a590c31d-893c-47d9-bd36-cb76ca6ab09b",
                "fb821c0c-2139-4da1-9984-7a671f5b5848",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "1637529b-cc06-49a3-bc13-44f0ca463607"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "41e0b732-ad7b-4e06-8434-5d629ad42138"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "41e0b732-ad7b-4e06-8434-5d629ad42138"
              ],
              "ref": "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7"
            },
            {
              "dependsOn": [
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6"
              ],
              "ref": "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f"
            },
            {
              "dependsOn": [],
              "ref": "21b82cc8-a032-48d9-9bcb-2666d5d9290c"
            },
            {
              "dependsOn": [
                "47defa27-eb63-4a54-933d-ba31a36b948b"
              ],
              "ref": "203d6912-ddea-42ca-9f86-3d79ba77b165"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "8f7dde0c-ab68-4cb8-8762-6e792aa2ebd8",
                "b2fff050-5b43-4127-8fb8-b6f33b565cda",
                "1d5bb002-b42f-4f76-8f77-96fb1a41d84b"
              ],
              "ref": "47defa27-eb63-4a54-933d-ba31a36b948b"
            },
            {
              "dependsOn": [
                "69040c5f-47ff-47c1-b9f0-564ed14a456e"
              ],
              "ref": "97ad98d7-a97d-428c-bab8-39faa6c9778e"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "771aa058-9ae4-4f46-a51d-bbc71be9eff8"
              ],
              "ref": "69040c5f-47ff-47c1-b9f0-564ed14a456e"
            },
            {
              "dependsOn": [
                "35a1b560-c887-41ef-bd82-e6e699eeb573"
              ],
              "ref": "771aa058-9ae4-4f46-a51d-bbc71be9eff8"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "8a7973fd-2cf3-4f15-89fa-e90b81d438cd"
              ],
              "ref": "35a1b560-c887-41ef-bd82-e6e699eeb573"
            },
            {
              "dependsOn": [
                "32794ad1-12c8-4b7a-8e4e-a50f3ed4fbea"
              ],
              "ref": "8a7973fd-2cf3-4f15-89fa-e90b81d438cd"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "32794ad1-12c8-4b7a-8e4e-a50f3ed4fbea"
            },
            {
              "dependsOn": [
                "e55429ad-d1da-4a6d-b3e5-d946fc049aa4"
              ],
              "ref": "8f7dde0c-ab68-4cb8-8762-6e792aa2ebd8"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "771aa058-9ae4-4f46-a51d-bbc71be9eff8"
              ],
              "ref": "e55429ad-d1da-4a6d-b3e5-d946fc049aa4"
            },
            {
              "dependsOn": [
                "209b2a84-a173-4371-8d99-68e891b8e39f"
              ],
              "ref": "b2fff050-5b43-4127-8fb8-b6f33b565cda"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "8e8f827f-d501-48d1-a909-a2cc31b6118a"
              ],
              "ref": "209b2a84-a173-4371-8d99-68e891b8e39f"
            },
            {
              "dependsOn": [
                "c02ec658-ef49-429f-ab35-4ad02c55cb4f"
              ],
              "ref": "8e8f827f-d501-48d1-a909-a2cc31b6118a"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "5ec3accc-1a2c-4f72-860b-12470b323a50"
              ],
              "ref": "c02ec658-ef49-429f-ab35-4ad02c55cb4f"
            },
            {
              "dependsOn": [
                "cb069e3b-d4b2-4c7f-b240-8f32179bbef2"
              ],
              "ref": "5ec3accc-1a2c-4f72-860b-12470b323a50"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e"
              ],
              "ref": "cb069e3b-d4b2-4c7f-b240-8f32179bbef2"
            },
            {
              "dependsOn": [
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "f347be38-44b9-4fe3-ae1b-a8de9c49a3e6",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "1d5bb002-b42f-4f76-8f77-96fb1a41d84b"
            },
            {
              "dependsOn": [
                "fb821c0c-2139-4da1-9984-7a671f5b5848",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7"
              ],
              "ref": "a590c31d-893c-47d9-bd36-cb76ca6ab09b"
            },
            {
              "dependsOn": [
                "4c74a302-81c6-4b71-ab5f-e4b4b4eb9212"
              ],
              "ref": "fb821c0c-2139-4da1-9984-7a671f5b5848"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "4c74a302-81c6-4b71-ab5f-e4b4b4eb9212"
            },
            {
              "dependsOn": [
                "66ca52c6-459d-48b0-8ad4-635877931b5a"
              ],
              "ref": "39ebe53e-9701-48f8-8683-d2b76690485e"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "66ca52c6-459d-48b0-8ad4-635877931b5a"
            },
            {
              "dependsOn": [
                "89f2754f-4f00-4da5-99aa-e811b839f1c8"
              ],
              "ref": "b488231b-ca44-41ca-af5e-bf2fb455d098"
            },
            {
              "dependsOn": [
                "bf7d1c49-6124-43a0-9875-f4898fd6bc45",
                "9da62063-f4e3-4567-b047-fc8c51de4ce0",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "89f2754f-4f00-4da5-99aa-e811b839f1c8"
            },
            {
              "dependsOn": [
                "e282c814-0b4f-4243-ab5e-c3d2daf017ea"
              ],
              "ref": "bf7d1c49-6124-43a0-9875-f4898fd6bc45"
            },
            {
              "dependsOn": [
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "55af0cfe-ad28-49fc-a361-3d592892e69f"
              ],
              "ref": "e282c814-0b4f-4243-ab5e-c3d2daf017ea"
            },
            {
              "dependsOn": [
                "4e7dae0a-333e-4943-9e82-267670e9b4c3"
              ],
              "ref": "55af0cfe-ad28-49fc-a361-3d592892e69f"
            },
            {
              "dependsOn": [
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7"
              ],
              "ref": "4e7dae0a-333e-4943-9e82-267670e9b4c3"
            },
            {
              "dependsOn": [
                "f118f347-868b-4a76-a3e4-678d44b75b16"
              ],
              "ref": "9da62063-f4e3-4567-b047-fc8c51de4ce0"
            },
            {
              "dependsOn": [
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "bf7d1c49-6124-43a0-9875-f4898fd6bc45"
              ],
              "ref": "f118f347-868b-4a76-a3e4-678d44b75b16"
            },
            {
              "dependsOn": [
                "cd0b9890-c436-4a69-8794-d077d63dec0b"
              ],
              "ref": "f230d072-b497-46d4-b3c2-3c069c00cf90"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "cd0b9890-c436-4a69-8794-d077d63dec0b"
            },
            {
              "dependsOn": [
                "9cd5d51c-213b-4aa8-af2f-35ae009b2a5d"
              ],
              "ref": "aa5d8951-ce78-4750-be1c-17ba96218e0c"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "203d6912-ddea-42ca-9f86-3d79ba77b165",
                "8e8f827f-d501-48d1-a909-a2cc31b6118a"
              ],
              "ref": "9cd5d51c-213b-4aa8-af2f-35ae009b2a5d"
            },
            {
              "dependsOn": [
                "db740bb2-b1cf-472e-a175-afafcecddb0a"
              ],
              "ref": "76d1bd7b-7e9c-44d4-aede-82a3d96dd375"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "18c76d79-739e-498c-8ace-63bd9f5f1d50",
                "98957b26-9c59-4177-9e46-da0bfbafcc86"
              ],
              "ref": "db740bb2-b1cf-472e-a175-afafcecddb0a"
            },
            {
              "dependsOn": [
                "4733f6c8-a1b0-470b-aed0-d6064bb4b982"
              ],
              "ref": "18c76d79-739e-498c-8ace-63bd9f5f1d50"
            },
            {
              "dependsOn": [
                "41e0b732-ad7b-4e06-8434-5d629ad42138",
                "5850e7fb-9e72-4ad3-b3f9-2300205cd2a7",
                "a02b8926-13c0-4d4a-a9ce-e60fe3d7d53f",
                "21b82cc8-a032-48d9-9bcb-2666d5d9290c",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358",
                "97ad98d7-a97d-428c-bab8-39faa6c9778e",
                "8e8f827f-d501-48d1-a909-a2cc31b6118a",
                "d5ef3f0f-7464-4f69-a8e5-3d62b64765a6"
              ],
              "ref": "4733f6c8-a1b0-470b-aed0-d6064bb4b982"
            },
            {
              "dependsOn": [
                "cfad292d-66db-415a-9a5e-98565f6c8db5"
              ],
              "ref": "d5ef3f0f-7464-4f69-a8e5-3d62b64765a6"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "19bd7968-3f6d-4839-91c7-ab7c0927e162"
              ],
              "ref": "cfad292d-66db-415a-9a5e-98565f6c8db5"
            },
            {
              "dependsOn": [],
              "ref": "19bd7968-3f6d-4839-91c7-ab7c0927e162"
            },
            {
              "dependsOn": [
                "b956ae69-dadb-4bf4-abb6-cda237f7fc17"
              ],
              "ref": "98957b26-9c59-4177-9e46-da0bfbafcc86"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "19bd7968-3f6d-4839-91c7-ab7c0927e162",
                "d5ef3f0f-7464-4f69-a8e5-3d62b64765a6"
              ],
              "ref": "b956ae69-dadb-4bf4-abb6-cda237f7fc17"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "d28beb07-a31d-47dd-9ec0-94681025dd4a"
              ],
              "ref": "2348b56a-fdcd-4f69-8dd1-ff82bfaf2dca"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "d28beb07-a31d-47dd-9ec0-94681025dd4a"
            },
            {
              "dependsOn": [
                "651428e7-6f21-4db1-a124-9c4c4a53409e",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "916e9c2c-fe07-4a10-bf18-1de7a5d3ee26",
                "3dac96be-33b7-473f-99a9-b6f773c47093",
                "09a764e5-d49d-412c-b1b8-49f5f1840511",
                "2e9a3e90-efa9-43bf-950d-fbad303f1622",
                "66781686-4303-4eec-9c17-fad95219ff85",
                "338c88ae-806c-4b8c-bb6f-3a891e2f9ad4",
                "1a997744-b9f1-4a25-8eac-acd3d2525507"
              ],
              "ref": "ece9f494-434d-49c1-ba40-b9c535d35ef7"
            },
            {
              "dependsOn": [
                "972127a0-19ad-4a57-b8dc-beefc40e70b9"
              ],
              "ref": "651428e7-6f21-4db1-a124-9c4c4a53409e"
            },
            {
              "dependsOn": [
                "afcc7b44-b0a9-4b0d-9b35-85e3ba45b496"
              ],
              "ref": "972127a0-19ad-4a57-b8dc-beefc40e70b9"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6da0a2a8-be55-4294-8e91-0466f52e461c"
              ],
              "ref": "afcc7b44-b0a9-4b0d-9b35-85e3ba45b496"
            },
            {
              "dependsOn": [
                "63caaafe-5063-49f9-8f48-50a7c5a7fa38"
              ],
              "ref": "c01bd576-a8d7-4273-bdcf-28ae49d72186"
            },
            {
              "dependsOn": [
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "6da0a2a8-be55-4294-8e91-0466f52e461c",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "63caaafe-5063-49f9-8f48-50a7c5a7fa38"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "6da0a2a8-be55-4294-8e91-0466f52e461c"
            },
            {
              "dependsOn": [
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649"
              ],
              "ref": "3dac96be-33b7-473f-99a9-b6f773c47093"
            },
            {
              "dependsOn": [
                "565d1c6b-3cea-4250-8b18-edafd83e7791"
              ],
              "ref": "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649"
            },
            {
              "dependsOn": [
                "5f192495-3e66-47db-9c75-2c2ab029c934",
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "7fe69f5b-fdeb-4de8-9d48-f09741e357e2",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6d98cb55-b2e1-42d8-b18a-0c8e8fabfbab",
                "91450b75-05b0-4e32-bb6c-36eb2103d305",
                "666c0f3c-8df1-43f9-8ed4-badd81e5593f",
                "10070bc0-ab7d-4373-a926-a1733e40de2a",
                "136a31c0-636f-4117-b116-c11fad8e50b7",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "5bf91f73-e9a5-4e33-95ca-19bbdef8c7fb",
                "6b1bb849-e457-46dd-bf64-98277bbabfaa",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "ac435bab-906f-46f8-9503-12011e5033c9",
                "1bbc6251-35a2-4e9d-9a8c-12d446fd59e7",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "6da0a2a8-be55-4294-8e91-0466f52e461c",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "565d1c6b-3cea-4250-8b18-edafd83e7791"
            },
            {
              "dependsOn": [
                "be7811b2-dd4c-4779-b112-d08b2e4fb5c7",
                "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570",
                "ae3b5eb4-18eb-413e-9a28-eae6118a0062",
                "fd1d6dc3-9ad0-496e-b625-68bba82e386e",
                "1bbc6251-35a2-4e9d-9a8c-12d446fd59e7",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "5f192495-3e66-47db-9c75-2c2ab029c934"
            },
            {
              "dependsOn": [
                "9a3e14cc-3ed5-4c65-90a7-ba68f9bdc814"
              ],
              "ref": "ae3b5eb4-18eb-413e-9a28-eae6118a0062"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3"
              ],
              "ref": "9a3e14cc-3ed5-4c65-90a7-ba68f9bdc814"
            },
            {
              "dependsOn": [
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "8eb10f01-1144-47ac-a306-0ea59361fcb3"
              ],
              "ref": "fd1d6dc3-9ad0-496e-b625-68bba82e386e"
            },
            {
              "dependsOn": [
                "e54adaf7-332e-4d36-8602-4131f98f3673",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "1bbc6251-35a2-4e9d-9a8c-12d446fd59e7"
            },
            {
              "dependsOn": [
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd"
              ],
              "ref": "7fe69f5b-fdeb-4de8-9d48-f09741e357e2"
            },
            {
              "dependsOn": [
                "adebe7fb-b0a2-49c3-8bf0-f1f59e013784"
              ],
              "ref": "6d98cb55-b2e1-42d8-b18a-0c8e8fabfbab"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "6da0a2a8-be55-4294-8e91-0466f52e461c"
              ],
              "ref": "adebe7fb-b0a2-49c3-8bf0-f1f59e013784"
            },
            {
              "dependsOn": [
                "ef329d79-0d73-4f65-9ecd-c13fd8252372"
              ],
              "ref": "91450b75-05b0-4e32-bb6c-36eb2103d305"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "ef329d79-0d73-4f65-9ecd-c13fd8252372"
            },
            {
              "dependsOn": [
                "d9416bbd-17e5-4fcd-a8f0-13bad793849b"
              ],
              "ref": "6a220678-e513-492d-b0a3-f4291e67b0ac"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "d9416bbd-17e5-4fcd-a8f0-13bad793849b"
            },
            {
              "dependsOn": [
                "13c24104-f06e-4caa-9769-05f3d0a9e353"
              ],
              "ref": "666c0f3c-8df1-43f9-8ed4-badd81e5593f"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "136a31c0-636f-4117-b116-c11fad8e50b7",
                "6a220678-e513-492d-b0a3-f4291e67b0ac"
              ],
              "ref": "13c24104-f06e-4caa-9769-05f3d0a9e353"
            },
            {
              "dependsOn": [
                "c5062078-aba0-43d9-b415-e94aeaf264d8"
              ],
              "ref": "136a31c0-636f-4117-b116-c11fad8e50b7"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "4dd094bb-826a-4fcd-a18a-1f7b606155a9",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "91450b75-05b0-4e32-bb6c-36eb2103d305",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "c5062078-aba0-43d9-b415-e94aeaf264d8"
            },
            {
              "dependsOn": [
                "317688ca-8005-49cc-ae31-e8713f34c8c4"
              ],
              "ref": "4dd094bb-826a-4fcd-a18a-1f7b606155a9"
            },
            {
              "dependsOn": [
                "4158fabe-b8ef-4006-ad6b-cf66b4dc1cda"
              ],
              "ref": "10070bc0-ab7d-4373-a926-a1733e40de2a"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6d98cb55-b2e1-42d8-b18a-0c8e8fabfbab",
                "666c0f3c-8df1-43f9-8ed4-badd81e5593f",
                "136a31c0-636f-4117-b116-c11fad8e50b7",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6b1bb849-e457-46dd-bf64-98277bbabfaa",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "4158fabe-b8ef-4006-ad6b-cf66b4dc1cda"
            },
            {
              "dependsOn": [
                "b5b2fe2d-b4cd-459a-ace4-89eef0dc6570",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "5bf91f73-e9a5-4e33-95ca-19bbdef8c7fb"
            },
            {
              "dependsOn": [
                "c01bd576-a8d7-4273-bdcf-28ae49d72186"
              ],
              "ref": "09a764e5-d49d-412c-b1b8-49f5f1840511"
            },
            {
              "dependsOn": [
                "6b6d9950-b284-43d9-8db1-01cd61dae339"
              ],
              "ref": "2e9a3e90-efa9-43bf-950d-fbad303f1622"
            },
            {
              "dependsOn": [
                "13fa09df-d8e4-4654-8ed6-eb4b19a8610d"
              ],
              "ref": "6b6d9950-b284-43d9-8db1-01cd61dae339"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "63c531e3-f7df-4ecd-820f-ab5c5e5c18d7",
                "0b15e13a-c0a5-4920-893d-4dd323e99d68",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "10070bc0-ab7d-4373-a926-a1733e40de2a",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd",
                "6b1bb849-e457-46dd-bf64-98277bbabfaa"
              ],
              "ref": "13fa09df-d8e4-4654-8ed6-eb4b19a8610d"
            },
            {
              "dependsOn": [
                "a91e33b6-95e5-43d1-bce3-93f3d8dc6035"
              ],
              "ref": "63c531e3-f7df-4ecd-820f-ab5c5e5c18d7"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "1b6176e6-6706-4ae6-9a55-a317e4a3e8d4",
                "0b15e13a-c0a5-4920-893d-4dd323e99d68",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "91450b75-05b0-4e32-bb6c-36eb2103d305",
                "6a220678-e513-492d-b0a3-f4291e67b0ac"
              ],
              "ref": "a91e33b6-95e5-43d1-bce3-93f3d8dc6035"
            },
            {
              "dependsOn": [
                "3f7f6ffa-0187-4560-a4ee-da48caee64ce"
              ],
              "ref": "1b6176e6-6706-4ae6-9a55-a317e4a3e8d4"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "136a31c0-636f-4117-b116-c11fad8e50b7",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "3f7f6ffa-0187-4560-a4ee-da48caee64ce"
            },
            {
              "dependsOn": [
                "badc98f6-da20-4285-bfe3-8a43947b5c99"
              ],
              "ref": "0b15e13a-c0a5-4920-893d-4dd323e99d68"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "1b6176e6-6706-4ae6-9a55-a317e4a3e8d4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "ac4e5657-8282-4bc3-824e-1bb33ad9c1cd"
              ],
              "ref": "badc98f6-da20-4285-bfe3-8a43947b5c99"
            },
            {
              "dependsOn": [
                "b12698ee-fedd-4e21-81ec-6582aa913ae2"
              ],
              "ref": "66781686-4303-4eec-9c17-fad95219ff85"
            },
            {
              "dependsOn": [
                "ca080353-40f8-4b4e-a1c4-43d919218ac7",
                "09a764e5-d49d-412c-b1b8-49f5f1840511",
                "2e9a3e90-efa9-43bf-950d-fbad303f1622",
                "e33f3a13-9b0b-448a-9827-e0d9c9607358"
              ],
              "ref": "b12698ee-fedd-4e21-81ec-6582aa913ae2"
            },
            {
              "dependsOn": [
                "d046da35-6c9e-43d2-ba43-6b22f96a7552"
              ],
              "ref": "338c88ae-806c-4b8c-bb6f-3a891e2f9ad4"
            },
            {
              "dependsOn": [
                "df523397-d2f5-4680-8622-8b1a3bdaba04"
              ],
              "ref": "d046da35-6c9e-43d2-ba43-6b22f96a7552"
            },
            {
              "dependsOn": [
                "9646e672-d159-4021-b7c2-69d2a886018d",
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "2ecd384d-270e-4ec1-b4fe-38174354457d",
                "317688ca-8005-49cc-ae31-e8713f34c8c4",
                "1b6176e6-6706-4ae6-9a55-a317e4a3e8d4",
                "6b6d9950-b284-43d9-8db1-01cd61dae339",
                "0b15e13a-c0a5-4920-893d-4dd323e99d68",
                "6942f9b1-3bac-4e15-b6f8-d9461e12886a",
                "4933ece0-9b12-4dfb-8373-526e24b53db4",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "666c0f3c-8df1-43f9-8ed4-badd81e5593f",
                "10070bc0-ab7d-4373-a926-a1733e40de2a",
                "6a220678-e513-492d-b0a3-f4291e67b0ac",
                "e3954d11-5739-4159-84c1-becd506c827f",
                "9bef8a59-6db0-42b1-a1d4-262730996b4b",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "1bbc6251-35a2-4e9d-9a8c-12d446fd59e7"
              ],
              "ref": "df523397-d2f5-4680-8622-8b1a3bdaba04"
            },
            {
              "dependsOn": [
                "5f192495-3e66-47db-9c75-2c2ab029c934",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6d98cb55-b2e1-42d8-b18a-0c8e8fabfbab",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "9646e672-d159-4021-b7c2-69d2a886018d"
            },
            {
              "dependsOn": [
                "a77775e1-dd7d-4e5c-bd5b-dcda06a2dcca"
              ],
              "ref": "6942f9b1-3bac-4e15-b6f8-d9461e12886a"
            },
            {
              "dependsOn": [
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "a77775e1-dd7d-4e5c-bd5b-dcda06a2dcca"
            },
            {
              "dependsOn": [
                "54cb5135-df9c-4ee3-a0a0-0a1c713c3e2f"
              ],
              "ref": "4933ece0-9b12-4dfb-8373-526e24b53db4"
            },
            {
              "dependsOn": [
                "63c531e3-f7df-4ecd-820f-ab5c5e5c18d7",
                "6b6d9950-b284-43d9-8db1-01cd61dae339",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "6a220678-e513-492d-b0a3-f4291e67b0ac"
              ],
              "ref": "54cb5135-df9c-4ee3-a0a0-0a1c713c3e2f"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "32ef4df7-62f4-47b1-b9bb-cf415e6d4465"
              ],
              "ref": "e3954d11-5739-4159-84c1-becd506c827f"
            },
            {
              "dependsOn": [
                "a541475f-f7d6-4736-b435-cb96936b81b8"
              ],
              "ref": "1a997744-b9f1-4a25-8eac-acd3d2525507"
            },
            {
              "dependsOn": [
                "bb5739d1-8e13-49d7-b833-e7e7f581d7b4"
              ],
              "ref": "a541475f-f7d6-4736-b435-cb96936b81b8"
            },
            {
              "dependsOn": [
                "6189ccaa-387b-4bff-9263-35d1994ba13d",
                "c01bd576-a8d7-4273-bdcf-28ae49d72186",
                "eaf12c00-3f77-42a1-8cd9-b6d6dcd41649",
                "32ef4df7-62f4-47b1-b9bb-cf415e6d4465",
                "d1656e17-8bf0-4ca7-9e39-9b3699d19dd6",
                "b6e53090-105e-43d4-af32-f1c17f8f8a17",
                "ca080353-40f8-4b4e-a1c4-43d919218ac7"
              ],
              "ref": "bb5739d1-8e13-49d7-b833-e7e7f581d7b4"
            }
          ],
          "metadata": {
            "authors": [
              {
                "name": "iO Digial"
              }
            ],
            "component": {
              "bom-ref": "20df021b-da0f-4c7d-b4ae-54d61fc8c1dc",
              "name": ":functionalTest",
              "type": "application",
              "version": "SNAPSHOT",
              "description": "functionalTest"
            },
            "timestamp": "2024-07-05T12:57:52Z",
            "tools": [
              {
                "name": "CDXgradle",
                "vendor": "iO Digital",
                "version": "1.0"
              }
            ]
          },
          "serialNumber": "urn:uuid:ef01ea5f-05a9-47da-acf8-61c762842c01",
          "specVersion": "1.5",
          "version": 1
        }
    """.trimIndent()

    @Test
    @Throws(IOException::class)
    fun canRunTask() {
        // Setup the test build
        val projectDir = File("build/functionalTest")
        val mockFile = File(projectDir, "mock.txt")
        Files.createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(mockFile, output)
        writeString(
            File(projectDir, "build.gradle"),
            """
                plugins {
                    id('com.iodigital.gradlebom')
                }
            """.trimIndent()
        )

        // Run the build
        val result = GradleRunner.create()
            .forwardOutput()
            .withPluginClasspath()
            .withArguments("generateBom", "--configuration=test")
            .withProjectDir(projectDir)
            .withEnvironment(mapOf("mockDependencyTreeFile" to mockFile.absolutePath))
            .build()


        // Verify the result
        val bomFile = File("${projectDir.absolutePath}/build/outputs/bom.json")
        val bom = bomFile.reader().readText().stabilizeUuid()
        val expected = "Wrote BOM file to: $bomFile"
        Assert.assertTrue(
            "Didn't find in output: \"$expected\"",
            result.output.contains(expected),
        )
        Assert.assertEquals(
            "Expected BOM to be correct, but got: $bom",
            expectedBom.stabilizeUuid(),
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
        FileWriter(file).use { writer ->
            writer.write(string)
        }
    }
}
