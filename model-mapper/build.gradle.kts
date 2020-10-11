plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
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

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
}