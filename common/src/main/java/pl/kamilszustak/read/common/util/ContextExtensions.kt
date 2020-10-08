package pl.kamilszustak.read.common.util

import android.content.Context

inline fun <reified T> Context.getSystemService(): T? {
    return getSystemService(T::class.java)
}