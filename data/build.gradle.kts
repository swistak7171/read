import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.DATA_ACCESS))
    implementation(project(Modules.MODEL_DOMAIN))

    // Dagger
    kapt(Dependencies.Google.Dagger.DAGGER_ANDROID_PROCESSOR)
    kapt(Dependencies.Google.Dagger.DAGGER_COMPILER)

    // Room
    implementation(Dependencies.AndroidX.Room.ROOM_KTX)
    implementation(Dependencies.AndroidX.Room.ROOM_RUNTIME)
    kapt(Dependencies.AndroidX.Room.ROOM_COMPILER)

    // Roomigrant
    implementation(Dependencies.Roomigrant.ROOMIGRANT)
    kapt(Dependencies.Roomigrant.COMPILER)

    // Firebase Database
    implementation(Dependencies.Google.Firebase.Database.DATABASE_KTX)
}