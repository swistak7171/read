plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))

    // Dagger
    api(Dependencies.Google.Dagger.DAGGER_ANDROID)
    api(Dependencies.Google.Dagger.DAGGER_ANDROID_SUPPORT)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Retrofit
    api(Dependencies.Retrofit.RETROFIT)
    api(Dependencies.Retrofit.RETROFIT_CONVERTER_MOSHI)

    // OkHttp
    api(Dependencies.OkHttp.OK_HTTP)
    api(Dependencies.OkHttp.OK_HTTP_LOGGING_INTERCEPTOR)

    // Moshi
    api(Dependencies.Moshi.MOSHI)
    api(Dependencies.Moshi.MOSHI_ADAPTERS)
    kapt(Dependencies.Moshi.MOSHI_KOTLIN_CODEGEN)
}