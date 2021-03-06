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
                argument("room.schemaLocation", "$projectDir/schemas")
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
    implementation(project(Modules.MODEL_ENTITY))
    implementation(project(Modules.MODEL_NETWORK))
    implementation(project(Modules.DOMAIN_ACCESS))

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

    // Retrofit
    implementation(Dependencies.Retrofit.RETROFIT)
}