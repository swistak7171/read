import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id(Plugins.DOKKA) version (Versions.DOKKA)
}

buildscript {
    extra["kotlin_version"] = Versions.KOTLIN

    repositories {
        google()
        jcenter()
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
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val dokka: DokkaTask by tasks.getting(DokkaTask::class) {
    outputDirectory = "$buildDir/dokka"
    outputFormat = "html"
}