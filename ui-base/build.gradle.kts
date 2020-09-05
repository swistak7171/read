plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Configuration.MIN_SDK_VERSION)
    }

    kotlinOptions {
        jvmTarget = "1.8"
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

    // Dagger
    api(Dependencies.Google.Dagger.DAGGER_ANDROID)
    api(Dependencies.Google.Dagger.DAGGER_ANDROID_SUPPORT)

    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT)
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT_RESOURCES)

    // Activity
    implementation(Dependencies.AndroidX.Activity.ACTIVITY)
    implementation(Dependencies.AndroidX.Activity.ACTIVITY_KTX)

    // Fragment
    implementation(Dependencies.AndroidX.Fragment.FRAGMENT)
    implementation(Dependencies.AndroidX.Fragment.FRAGMENT_KTX)

    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT)
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT_RESOURCES)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_COMMON_JAVA_8)

    // Navigation
    implementation(Dependencies.AndroidX.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.Navigation.NAVIGATION_UI_KTX)

    // Material Components
    implementation(Dependencies.Google.Material.MATERIAL_COMPONENTS)

    // Material Dialogs
    implementation(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_CORE)
    implementation(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_INPUT)
    implementation(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_DATETIME)
    implementation(Dependencies.MaterialDialogs.MATERIAL_DIALOGS_LIFECYCLE)

    // FastAdapter & Materialize
    implementation(Dependencies.FastAdapter.FAST_ADAPTER)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_BINDING)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_EXPANDABLE)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_DIFF)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_DRAG)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_PAGED)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_SCROLL)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_SWIPE)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_UI)
    implementation(Dependencies.FastAdapter.FAST_ADAPTER_EXTENSIONS_UTILS)
    implementation(Dependencies.FastAdapter.MATERIALIZE)

    // Assent
    implementation(Dependencies.Assent.ASSENT_CORE)
    implementation(Dependencies.Assent.ASSENT_COROUTINES)

    // Toasty
    implementation(Dependencies.TOASTY)

    // Anko
    implementation(Dependencies.Anko.ANKO)
    implementation(Dependencies.Anko.ANKO_DESIGN)
    implementation(Dependencies.Anko.ANKO_COMMONS)
}
