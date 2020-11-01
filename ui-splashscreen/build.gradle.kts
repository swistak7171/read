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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-result-return-type",
            "-XXLanguage:+InlineClasses"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

androidExtensions {
    features = setOf("parcelize")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.UI_BASE))

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)
}
