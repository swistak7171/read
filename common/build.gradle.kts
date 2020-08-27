plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Configuration.MIN_SDK_VERSION)
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