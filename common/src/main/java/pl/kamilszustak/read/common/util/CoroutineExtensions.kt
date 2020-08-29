package pl.kamilszustak.read.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> withMainContext(crossinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Main) {
        block()
    }

suspend inline fun <T> withDefaultContext(crossinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Default) {
        block()
    }

suspend inline fun <T> withIOContext(crossinline block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.IO) {
        block()
    }