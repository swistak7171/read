plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
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

    implementation(project(Modules.MODEL_DOMAIN))
    implementation(project(Modules.MODEL_DATA))
    implementation(project(Modules.DOMAIN_ACCESS))

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Firebase Authentication
    api(Dependencies.Google.Firebase.Authentication.AUTH_KTX)
}