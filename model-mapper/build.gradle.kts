plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
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
    implementation(project(Modules.MODEL_COMMON))
    implementation(project(Modules.MODEL_DOMAIN))
    implementation(project(Modules.MODEL_ENTITY))
    implementation(project(Modules.MODEL_NETWORK))
    implementation(project(Modules.DOMAIN_ACCESS))

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Firebase Authentication
    implementation(Dependencies.Google.Firebase.Authentication.AUTH_KTX)

    // ML Kit
    implementation(Dependencies.Google.MLKit.TEXT_RECOGNITION)
}