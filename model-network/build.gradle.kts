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

    // Moshi
    implementation(Dependencies.Moshi.MOSHI)
    kapt(Dependencies.Moshi.MOSHI_KOTLIN_CODEGEN)
}