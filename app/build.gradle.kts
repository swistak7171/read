import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.DOKKA)
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

    buildTypes {
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
        jvmTarget = "1.8"
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
}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":common"))

    // LeakCanary
    debugImplementation(Dependencies.LEAK_CANARY_ANDROID)
}