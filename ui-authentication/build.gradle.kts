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

    implementation(project(":ui-base"))
    implementation(project(":common"))
    implementation(project(":domain"))

    // Dagger
    api(Dependencies.Google.Dagger.DAGGER_ANDROID)
    api(Dependencies.Google.Dagger.DAGGER_ANDROID_SUPPORT)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // ConstraintLayout
    implementation(Dependencies.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    // Preference
    implementation(Dependencies.AndroidX.Preference.PREFERENCE)
    implementation(Dependencies.AndroidX.Preference.PREFERENCE_KTX)

    // SwipeRefreshLayout
    implementation(Dependencies.AndroidX.SwipeRefreshLayout.SWIPE_REFRESH_LAYOUT)

    // RecyclerView
    implementation(Dependencies.AndroidX.RecyclerView.RECYCLER_VIEW)

    // Glide
    implementation(Dependencies.Glide.GLIDE)
    kapt(Dependencies.Glide.GLIDE_COMPILER)

    // Firebase Authentication
    implementation(Dependencies.Google.Firebase.Authentication.AUTH_UI)

    // Facebok Android SDK
    implementation(Dependencies.FACEBOOK_ANDROID_SDK)

    // Twitter Android SDK
    implementation(Dependencies.TWITTER_ANDROID_SDK)
}
