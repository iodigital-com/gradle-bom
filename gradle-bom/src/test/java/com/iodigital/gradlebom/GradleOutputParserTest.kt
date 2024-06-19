package com.iodigital.gradlebom

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import com.iodigital.gradlebom.logic.GradleOutputParser
import com.iodigital.gradlebom.models.GradleDependency.Component.Project
import org.junit.Before
import org.junit.Test

class GradleOutputParserTest {

    companion object {
        //region private const val tree = ...
        private const val tree = """
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

    private lateinit var target: GradleOutputParser

    @Before
    fun setUp() {
        target = GradleOutputParser()
    }

    @Test
    fun `WHEN tree is parsed THEN dependencies are correct`() {
        //region GIVEN
        val projectName = "test"
        //endregion
        //region WHEN
        val (root, dependencies) = target.parse(projectName, tree.reader().buffered())
        //endregion
        //region THEN
        assertThat(root.component is Project).isTrue()
        assertThat((root.component as Project).projectName).isEqualTo(":$projectName")
        assertThat(dependencies.size).isEqualTo(156)
        assertThat(dependencies.hashCode()).isEqualTo(-93916932)
        //endregion
    }
}