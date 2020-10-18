package pl.kamilszustak.read.common.util

import java.lang.ref.Reference
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

fun <T : Any?> T.asWeakReference(): WeakReference<T> =
    WeakReference(this)

fun <T : Any?> T.asSoftReference(): SoftReference<T> =
    SoftReference(this)

fun <T> Reference<T>.getOnce(): T? =
    this.get().also { this.clear() }