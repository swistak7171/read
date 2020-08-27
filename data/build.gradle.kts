plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
}

android {
    compileSdkVersion(Configuration.COMPILE_SDK_VERSION)
    buildToolsVersion(Configuration.BUILD_TOOLS_VERSION)

    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation(project(":common"))
    implementation(project(":network"))

    // Room
    implementation(Dependencies.AndroidX.Room.ROOM_KTX)
    implementation(Dependencies.AndroidX.Room.ROOM_RUNTIME)
    kapt(Dependencies.AndroidX.Room.ROOM_COMPILER)

    // Roomigrant
    implementation(Dependencies.Roomigrant.ROOMIGRANT)
    kapt(Dependencies.Roomigrant.COMPILER)
}