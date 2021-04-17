import Configuration.BUILD_TOOLS_VERSION
import Configuration.COMPILE_SDK_VERSION
import Configuration.MIN_SDK_VERSION

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdkVersion(COMPILE_SDK_VERSION)
    buildToolsVersion(BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(MIN_SDK_VERSION)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-result-return-type",
            "-XXLanguage:+InlineClasses"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(Modules.MODEL_COMMON))

    // Kotlin Standard Library
    api(Dependencies.Kotlin.KOTLIN_STANDARD_LIBRARY)

    // Kotlin Coroutines
    api(Dependencies.Kotlin.Coroutines.KOTLINX_COROUTINES_CORE)
    api(Dependencies.Kotlin.Coroutines.KOTLINX_COROUTINES_CORE_JVM)
    api(Dependencies.Kotlin.Coroutines.KOTLINX_COROUTINES_ANDROID)
    api(Dependencies.Kotlin.Coroutines.KOTLINX_COROUTINES_PLAY_SERVICES)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_COMMON_JAVA_8)

    // Activity
    implementation(Dependencies.AndroidX.Activity.ACTIVITY)

    // Core
    api(Dependencies.AndroidX.Core.CORE)
    api(Dependencies.AndroidX.Core.CORE_KTX)

    // Firebase Authentication
    api(Dependencies.Google.Firebase.Authentication.AUTH_KTX)

    // Dagger
    api(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // AssistedInject
    api(Dependencies.AssistedInject.ASSISTED_INJECT_ANNOTATIONS_DAGGER_2)
    kapt(Dependencies.AssistedInject.ASSISTED_INJECT_PROCESSOR_DAGGER_2)

    // Timber
    api(Dependencies.TIMBER)

    // android-flags
    api(Dependencies.ANDROID_FLAGS)

    // pastelPlaceholders
    api(Dependencies.PASTEL_PLACEHOLDERS)
}