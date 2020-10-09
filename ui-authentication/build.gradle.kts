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
    implementation(project(Modules.UI_MAIN))
    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN_ACCESS))
    implementation(project(Modules.MODEL_DOMAIN))

    // Dagger
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Firebase Authentication
    implementation(Dependencies.Google.Firebase.Authentication.AUTH_UI)

    // Facebok Android SDK
    implementation(Dependencies.FACEBOOK_ANDROID_SDK)

    // Twitter Android SDK
    implementation(Dependencies.TWITTER_ANDROID_SDK)
}
