plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-result-return-type",
            "-XXLanguage:+InlineClasses"
        )
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_ACCESS))
    implementation(project(Modules.DATA_ACCESS))
    implementation(project(Modules.MODEL_COMMON))
    implementation(project(Modules.MODEL_DOMAIN))
    implementation(project(Modules.MODEL_ENTITY))
    implementation(project(Modules.MODEL_NETWORK))
    implementation(project(Modules.MODEL_MAPPER))
    implementation(project(Modules.WORK))

    // WorkManager
    implementation(Dependencies.AndroidX.Work.WORK_RUNTIME_KTX)

    // DataStore
    implementation(Dependencies.AndroidX.DataStore.DATA_STORE_PREFERENCES)
    implementation(Dependencies.AndroidX.DataStore.DATA_STORE_PREFERENCES_CORE)

    // Dagger
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // CameraX
    implementation(Dependencies.AndroidX.CameraX.CAMERA_CORE)

    // ML Kit
    implementation(Dependencies.Google.MLKit.BARCODE_SCANNING)
    implementation(Dependencies.Google.MLKit.TEXT_RECOGNITION)

    // Firebase Authentication
    implementation(Dependencies.Google.Firebase.Authentication.AUTH_UI)

    // pastelPlaceholders
    implementation(Dependencies.PASTEL_PLACEHOLDERS)
}