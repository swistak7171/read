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
    implementation(project(Modules.MODEL_COMMON))
    implementation(project(Modules.MODEL_NETWORK))

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Retrofit
    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.RETROFIT_CONVERTER_MOSHI)

    // OkHttp
    implementation(Dependencies.OkHttp.OK_HTTP)
    implementation(Dependencies.OkHttp.OK_HTTP_LOGGING_INTERCEPTOR)

    // Moshi
    implementation(Dependencies.Moshi.MOSHI)
    implementation(Dependencies.Moshi.MOSHI_ADAPTERS)
    kapt(Dependencies.Moshi.MOSHI_KOTLIN_CODEGEN)
}