plugins {
    id(Plugins.KTLINT) version (Versions.KTLINT)
    id(Plugins.DOKKA) version (Versions.DOKKA)
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.ANDROID_GRADLE_BUILD_TOOLS)
        classpath(Dependencies.Kotlin.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.AndroidX.Navigation.NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN)
        classpath(Dependencies.KTLINT_GRADLE)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(Repositories.GRADLE_PLUGINS_M2)
        maven(Repositories.JITPACK)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}