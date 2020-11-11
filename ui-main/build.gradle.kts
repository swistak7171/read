plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.NAVIGATION_SAFE_ARGS_KOTLIN)
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

androidExtensions {
    features = setOf("parcelize")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.UI_BASE))
    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_ACCESS))
    implementation(project(Modules.MODEL_DOMAIN))

    // Dagger
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // AssistedInject
    kapt(Dependencies.AssistedInject.ASSISTED_INJECT_PROCESSOR_DAGGER_2)

    // Firebase Authentication
    implementation(Dependencies.Google.Firebase.Authentication.AUTH_KTX)

    // CameraX
    implementation(Dependencies.AndroidX.CameraX.CAMERA_CORE)
    implementation(Dependencies.AndroidX.CameraX.CAMERA_2)
    implementation(Dependencies.AndroidX.CameraX.CAMERA_LIFECYCLE)
    implementation(Dependencies.AndroidX.CameraX.CAMERA_VIEW)

    // DiscreteScrollView
    implementation(Dependencies.DISCRETE_SCROLL_VIEW)
}
