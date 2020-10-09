plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DOKKA)
    id(Plugins.GOOGLE_SERVICES)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId = Configuration.APPLICATION_ID
        minSdkVersion(Configuration.MIN_SDK_VERSION)
        targetSdkVersion(Configuration.TARGET_SDK_VERSION)
        versionCode = Configuration.VERSION_CODE
        versionName = Configuration.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-result-return-type",
            "-XXLanguage:+InlineClasses"
        )
    }

    testOptions {
        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.COMMON))
    implementation(project(Modules.DATA))
    implementation(project(Modules.DATA_ACCESS))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DOMAIN_ACCESS))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.UI_BASE))
    implementation(project(Modules.UI_AUTHENTICATION))
    implementation(project(Modules.UI_MAIN))

    // Dagger
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // LeakCanary
    debugImplementation(Dependencies.LEAK_CANARY_ANDROID)

    // Firebase Database
    implementation(Dependencies.Google.Firebase.Database.DATABASE_KTX)
}
