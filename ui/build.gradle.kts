plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.GOOGLE_SERVICES)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Configuration.MIN_SDK_VERSION)
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main") {
            res.srcDirs(
                    "src/main/res/layouts/authentication",
                    "src/main/res/layouts",
                    "src/main/res"
            )
        }
    }
}

androidExtensions {
    features = setOf("parcelize")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))
    implementation(project(":domain"))

    // Material Components
    implementation(Dependencies.Google.Material.MATERIAL_COMPONENTS)

    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT)
    implementation(Dependencies.AndroidX.AppCompat.APP_COMPAT_RESOURCES)

    // ConstraintLayout
    implementation(Dependencies.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    // Activity
    implementation(Dependencies.AndroidX.Activity.ACTIVITY)
    implementation(Dependencies.AndroidX.Activity.ACTIVITY_KTX)

    // Fragment
    implementation(Dependencies.AndroidX.Fragment.FRAGMENT)
    implementation(Dependencies.AndroidX.Fragment.FRAGMENT_KTX)

    // Preference
    implementation(Dependencies.AndroidX.Preference.PREFERENCE)
    implementation(Dependencies.AndroidX.Preference.PREFERENCE_KTX)

    // Navigation
    implementation(Dependencies.AndroidX.Navigation.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.Navigation.NAVIGATION_UI_KTX)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.Lifecycle.LIFECYCLE_COMMON_JAVA_8)

    // SwipeRefreshLayout
    implementation(Dependencies.AndroidX.SwipeRefreshLayout.SWIPE_REFRESH_LAYOUT)

    // RecyclerView
    implementation(Dependencies.AndroidX.RecyclerView.RECYCLER_VIEW)

    // Anko
    implementation(Dependencies.Anko.ANKO)
    implementation(Dependencies.Anko.ANKO_DESIGN)
    implementation(Dependencies.Anko.ANKO_COMMONS)

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

    // Glide
    implementation(Dependencies.Glide.GLIDE)
    kapt(Dependencies.Glide.GLIDE_COMPILER)
}
