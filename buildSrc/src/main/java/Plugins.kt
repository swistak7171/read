object Plugins {
    private const val JETBRAINS_KOTLIN: String = "org.jetbrains.kotlin"
    private const val ANDROID: String = "com.android"

    const val ANDROID_APPLICATION: String = "$ANDROID.application"
    const val ANDROID_LIBRARY: String = "$ANDROID.library"
    const val KOTLIN_ANDROID: String = "$JETBRAINS_KOTLIN.android"
    const val KOTLIN_PARCELIZE: String = "kotlin-parcelize"
    const val KOTLIN_KAPT: String = "$JETBRAINS_KOTLIN.kapt"
    const val NAVIGATION_SAFE_ARGS_KOTLIN: String = "androidx.navigation.safeargs.kotlin"
    const val KTLINT: String = "org.jlleitschuh.gradle.ktlint"
    const val DOKKA: String = "org.jetbrains.dokka"
    const val GOOGLE_SERVICES: String = "com.google.gms.google-services"
}