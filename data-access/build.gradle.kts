plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-result-return-type",
            "-XXLanguage:+InlineClasses"
        )
    }

    defaultConfig {
        minSdkVersion(Configuration.MIN_SDK_VERSION)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.MODEL_DOMAIN))
    implementation(project(Modules.MODEL_DATA))
}