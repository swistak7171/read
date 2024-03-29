plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.COMMON))
    implementation(project(Modules.NOTIFICATION))

    // Dagger
    implementation(Dependencies.Google.Dagger.DAGGER_ANDROID)
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // AppCompat
    api(Dependencies.AndroidX.AppCompat.APP_COMPAT)
    api(Dependencies.AndroidX.AppCompat.APP_COMPAT_RESOURCES)

    // Activity
    api(Dependencies.AndroidX.Activity.ACTIVITY)
    api(Dependencies.AndroidX.Activity.ACTIVITY_KTX)

    // Fragment
    api(Dependencies.AndroidX.Fragment.FRAGMENT)
    api(Dependencies.AndroidX.Fragment.FRAGMENT_KTX)

    // AppCompat
    api(Dependencies.AndroidX.AppCompat.APP_COMPAT)
    api(Dependencies.AndroidX.AppCompat.APP_COMPAT_RESOURCES)

    // Lifecycle
    api(Dependencies.AndroidX.Lifecycle.LIFECYCLE_VIEWMODEL_KTX)
    api(Dependencies.AndroidX.Lifecycle.LIFECYCLE_LIVEDATA_KTX)
    api(Dependencies.AndroidX.Lifecycle.LIFECYCLE_RUNTIME_KTX)
    api(Dependencies.AndroidX.Lifecycle.LIFECYCLE_COMMON_JAVA_8)

    // Navigation
    api(Dependencies.AndroidX.Navigation.NAVIGATION_FRAGMENT_KTX)
    api(Dependencies.AndroidX.Navigation.NAVIGATION_UI_KTX)

    // Material Components
    api(Dependencies.Google.Material.MATERIAL_COMPONENTS)

    // ConstraintLayout
    api(Dependencies.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    // Preference
    api(Dependencies.AndroidX.Preference.PREFERENCE)
    api(Dependencies.AndroidX.Preference.PREFERENCE_KTX)

    // SwipeRefreshLayout
    api(Dependencies.AndroidX.SwipeRefreshLayout.SWIPE_REFRESH_LAYOUT)

    // RecyclerView
    api(Dependencies.AndroidX.RecyclerView.RECYCLER_VIEW)

    // Firebase Messaging
    implementation(Dependencies.Google.Firebase.Messaging.MESSAGING_KTX)

    // Material Dialogs
    api(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_CORE)
    api(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_INPUT)
    api(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_DATETIME)
    api(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_LIFECYCLE)

    // FastAdapter & Materialize
    api(Dependencies.FastAdapter.FAST_ADAPTER)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_BINDING)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_EXPANDABLE)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_DIFF)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_DRAG)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_PAGED)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_SCROLL)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_SWIPE)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_UI)
    api(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_UTILS)
    api(Dependencies.FastAdapter.MATERIALIZE)

    // Assent
    api(Dependencies.Assent.ASSENT_CORE)
    api(Dependencies.Assent.ASSENT_COROUTINES)

    // Toasty
    api(Dependencies.TOASTY)

    // Anko
    api(Dependencies.Anko.ANKO)
    api(Dependencies.Anko.ANKO_DESIGN)
    api(Dependencies.Anko.ANKO_COMMONS)

    // Timber
    api(Dependencies.TIMBER)

    // Glide
    api(Dependencies.Glide.GLIDE)
    kapt(Dependencies.Glide.GLIDE_COMPILER)

    // CircularProgressBar
    api(Dependencies.CIRCULAR_PROGRESS_BAR)
}
