plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))

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