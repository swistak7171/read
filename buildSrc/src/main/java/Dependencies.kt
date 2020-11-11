object Dependencies {
    object Kotlin {
        const val KOTLIN_STANDARD_LIBRARY: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
        const val KOTLIN_GRADLE_PLUGIN: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

        object Coroutines {
            const val KOTLINX_COROUTINES_CORE: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
            const val KOTLINX_COROUTINES_CORE_JVM: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.KOTLINX_COROUTINES}"
            const val KOTLINX_COROUTINES_ANDROID: String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
            const val KOTLINX_COROUTINES_PLAY_SERVICES: String = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.KOTLINX_COROUTINES}"
        }
    }

    object Google {
        object Material {
            const val MATERIAL_COMPONENTS: String = "com.google.android.material:material:${Versions.MATERIAL_COMPONENTS}"
        }

        object Dagger {
            const val DAGGER_ANDROID: String = "com.google.dagger:dagger-android:${Versions.DAGGER}"
            const val DAGGER_ANDROID_PROCESSOR: String = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
            const val DAGGER_COMPILER: String = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
        }

        object Firebase {
            object Authentication {
                const val AUTH_KTX: String = "com.google.firebase:firebase-auth-ktx:${Versions.FIREBASE_AUTH}"
                const val AUTH_UI: String = "com.firebaseui:firebase-ui-auth:${Versions.FIREBASE_AUTH_UI}"
            }

            object Database {
                const val DATABASE_KTX: String = "com.google.firebase:firebase-database-ktx:${Versions.FIREBASE_DATABASE}"
            }
        }

        object Gms {
            const val GOOGLE_SERVICES: String = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
        }

        object MLKit {
            const val BARCODE_SCANNING: String = "com.google.mlkit:barcode-scanning:${Versions.ML_KIT_BARCODE_SCANNING}"
        }
    }

    object AndroidX {
        object AppCompat {
            const val APP_COMPAT: String = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
            const val APP_COMPAT_RESOURCES: String = "androidx.appcompat:appcompat-resources:${Versions.APP_COMPAT}"
        }

        object Lifecycle {
            const val LIFECYCLE_VIEWMODEL_KTX: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
            const val LIFECYCLE_LIVEDATA_KTX: String = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
            const val LIFECYCLE_RUNTIME_KTX: String = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
            const val LIFECYCLE_COMMON_JAVA_8: String = "androidx.lifecycle:lifecycle-common-java8:${Versions.LIFECYCLE}"
        }

        object Core {
            const val CORE: String = "androidx.core:core:${Versions.CORE}"
            const val CORE_KTX: String = "androidx.core:core-ktx:${Versions.CORE}"
        }

        object Activity {
            const val ACTIVITY: String = "androidx.activity:activity:${Versions.ACTIVITY}"
            const val ACTIVITY_KTX: String = "androidx.activity:activity-ktx:${Versions.ACTIVITY}"
        }

        object Fragment {
            const val FRAGMENT: String = "androidx.fragment:fragment:${Versions.FRAGMENT}"
            const val FRAGMENT_KTX: String = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT}"
        }

        object Preference {
            const val PREFERENCE: String = "androidx.preference:preference:${Versions.PREFERENCE}"
            const val PREFERENCE_KTX: String = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"
        }

        object Navigation {
            const val NAVIGATION_FRAGMENT_KTX: String = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
            const val NAVIGATION_UI_KTX: String = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
            const val NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN: String = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_SAFE_ARGS_GRADLE_PLUGIN}"
        }

        object Room {
            const val ROOM_KTX: String = "androidx.room:room-ktx:${Versions.ROOM}"
            const val ROOM_RUNTIME: String = "androidx.room:room-runtime:${Versions.ROOM}"
            const val ROOM_COMPILER: String = "androidx.room:room-compiler:${Versions.ROOM}"
        }

        object Work {
            const val WORK_RUNTIME_KTX: String = "androidx.work:work-runtime-ktx:${Versions.WORK}"
        }

        object ConstraintLayout {
            const val CONSTRAINT_LAYOUT: String = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        }

        object RecyclerView {
            const val RECYCLER_VIEW: String = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
        }

        object SwipeRefreshLayout {
            const val SWIPE_REFRESH_LAYOUT: String = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"
        }

        object CameraX {
            const val CAMERA_CORE: String = "androidx.camera:camera-core:${Versions.CAMERA_X}"
            const val CAMERA_LIFECYCLE: String = "androidx.camera:camera-lifecycle:${Versions.CAMERA_X}"
            const val CAMERA_2: String = "androidx.camera:camera-camera2:${Versions.CAMERA_X}"
            const val CAMERA_VIEW: String = "androidx.camera:camera-view:${Versions.CAMERA_X_VIEW}"
        }
    }

    object Retrofit {
        const val RETROFIT: String = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_MOSHI: String = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    }

    object OkHttp {
        const val OK_HTTP: String = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP}"
        const val OK_HTTP_LOGGING_INTERCEPTOR: String = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP}"
    }

    object Moshi {
        const val MOSHI: String = "com.squareup.moshi:moshi:${Versions.MOSHI}"
        const val MOSHI_ADAPTERS: String = "com.squareup.moshi:moshi-adapters:${Versions.MOSHI}"
        const val MOSHI_KOTLIN_CODEGEN: String = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    }

    object Anko {
        const val ANKO: String = "org.jetbrains.anko:anko:${Versions.ANKO}"
        const val ANKO_DESIGN: String = "org.jetbrains.anko:anko-design:${Versions.ANKO}"
        const val ANKO_COMMONS: String = "org.jetbrains.anko:anko-commons:${Versions.ANKO}"
    }

    object AssistedInject {
        const val ASSISTED_INJECT_ANNOTATIONS_DAGGER_2: String = "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.ASSISTED_INJECT}"
        const val ASSISTED_INJECT_PROCESSOR_DAGGER_2: String = "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.ASSISTED_INJECT}"
    }

    object MaterialDialogs {
        const val MATERIAL_DIALOGS_CORE: String = "com.afollestad.material-dialogs:core:${Versions.MATERIAL_DIALOGS}"
        const val MATERIAL_DIALOGS_INPUT: String = "com.afollestad.material-dialogs:input:${Versions.MATERIAL_DIALOGS}"
        const val MATERIAL_DIALOGS_DATETIME: String = "com.afollestad.material-dialogs:datetime:${Versions.MATERIAL_DIALOGS}"
        const val MATERIAL_DIALOGS_LIFECYCLE: String = "com.afollestad.material-dialogs:lifecycle:${Versions.MATERIAL_DIALOGS}"
    }

    object FastAdapter {
        const val FAST_ADAPTER: String = "com.mikepenz:fastadapter:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_BINDING: String = "com.mikepenz:fastadapter-extensions-binding:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_EXPANDABLE: String = "com.mikepenz:fastadapter-extensions-expandable:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_DIFF: String = "com.mikepenz:fastadapter-extensions-diff:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_DRAG: String = "com.mikepenz:fastadapter-extensions-drag:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_PAGED: String = "com.mikepenz:fastadapter-extensions-paged:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_SCROLL: String = "com.mikepenz:fastadapter-extensions-scroll:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_SWIPE: String = "com.mikepenz:fastadapter-extensions-swipe:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_UI: String = "com.mikepenz:fastadapter-extensions-ui:${Versions.FAST_ADAPTER}"
        const val FAST_ADAPTER_EXTENSIONS_UTILS: String = "com.mikepenz:fastadapter-extensions-utils:${Versions.FAST_ADAPTER}"
        const val MATERIALIZE: String = "com.mikepenz:materialize:${Versions.MATERIALIZE}"
    }

    object Assent {
        const val ASSENT_CORE: String = "com.afollestad.assent:core:${Versions.ASSENT}"
        const val ASSENT_COROUTINES: String = "com.afollestad.assent:coroutines:${Versions.ASSENT}"
    }

    object Glide {
        const val GLIDE: String = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val GLIDE_COMPILER: String = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    }

    object Roomigrant {
        const val ROOMIGRANT: String = "com.github.MatrixDev.Roomigrant:RoomigrantLib:${Versions.ROOMIGRANT}"
        const val COMPILER: String = "com.github.MatrixDev.Roomigrant:RoomigrantCompiler:${Versions.ROOMIGRANT}"
    }

    const val ANDROID_GRADLE_BUILD_TOOLS: String = "com.android.tools.build:gradle:${Versions.ANDROID_STUDIO}"
    const val KTLINT: String = "com.pinterest:ktlint:${Versions.KTLINT}"
    const val TIMBER: String = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val LEAK_CANARY_ANDROID: String = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    const val TOASTY: String = "com.github.GrenderG:Toasty:${Versions.TOASTY}"
    const val FACEBOOK_ANDROID_SDK: String = "com.facebook.android:facebook-android-sdk:${Versions.FACEBOOK_ANDROID_SDK}"
    const val TWITTER_ANDROID_SDK: String = "com.twitter.sdk.android:twitter-core:${Versions.TWITTER_ANDROID_SDK}"
    const val ANDROID_FLAGS: String = "com.github.Shusshu:android-flags:${Versions.ANDROID_FLAGS}"
    const val CIRCULAR_PROGRESS_BAR: String = "com.mikhaellopez:circularprogressbar:${Versions.CIRCULAR_PROGRESS_BAR}"
    const val DISCRETE_SCROLL_VIEW: String = "com.yarolegovich:discrete-scrollview:${Versions.DISCRETE_SCROLL_VIEW}"
    const val PASTEL_PLACEHOLDERS: String = "com.github.zedlabs:pastelPlaceholders:${Versions.PASTEL_PLACEHOLDERS}"
}