import Configuration.BUILD_TOOLS_VERSION
import Configuration.COMPILE_SDK_VERSION
import Configuration.MIN_SDK_VERSION
import org.gradle.api.artifacts.Configuration

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

val ktlint: Configuration by configurations.creating

android {
    compileSdkVersion(COMPILE_SDK_VERSION)
    buildToolsVersion(BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(MIN_SDK_VERSION)
    }

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    ktlint(Dependencies.KTLINT)

    // Kotlin Coroutines
    api(Dependencies.Kotlin.Coroutines.KOTLIN_COROUTINES_CORE)
    api(Dependencies.Kotlin.Coroutines.KOTLIN_COROUTINES_ANDROID)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_COMMON_JAVA_8)

    // Activity
    implementation(Dependencies.AndroidX.Activity.ACTIVITY)

    // Core
    api(Dependencies.AndroidX.Core.CORE)
    api(Dependencies.AndroidX.Core.CORE_KTX)

    // WorkManager
    api(Dependencies.AndroidX.Work.WORK_RUNTIME_KTX)

    // Dagger
    api(Dependencies.Google.Dagger.DAGGER_ANDROID)
    api(Dependencies.Google.Dagger.DAGGER_ANDROID_SUPPORT)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // AssistedInject
    compileOnly(Dependencies.AssistedInject.ASSISTED_INJECT_ANNOTATIONS_DAGGER_2)
    kapt(Dependencies.AssistedInject.ASSISTED_INJECT_PROCESSOR_DAGGER_2)

    // Timber
    api(Dependencies.TIMBER)
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}