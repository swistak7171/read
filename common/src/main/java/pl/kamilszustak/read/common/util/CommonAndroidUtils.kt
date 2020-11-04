package pl.kamilszustak.read.common.util

import android.os.Build

inline fun belowSdkVersion(version: Int, action: () -> Unit) {
    if (Build.VERSION.SDK_INT < version) {
        action()
    }
}

inline fun overSdkVersion(version: Int, action: () -> Unit) {
    if (Build.VERSION.SDK_INT > version) {
        action()
    }
}