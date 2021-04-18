plugins {
    id(Plugins.DOKKA) version (Versions.DOKKA)
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(Repositories.KOTLIN_EAP)
        maven(Repositories.KOTLIN_DEV)
    }

    dependencies {
        classpath(Dependencies.ANDROID_GRADLE_BUILD_TOOLS)
        classpath(Dependencies.Kotlin.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.AndroidX.Navigation.NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN)
        classpath(Dependencies.KTLINT)
        classpath(Dependencies.Google.Gms.GOOGLE_SERVICES)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(Repositories.GRADLE_PLUGINS_M2)
        maven(Repositories.JITPACK)
        maven(Repositories.KOTLIN_EAP)
        maven(Repositories.KOTLIN_DEV)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}