package pl.kamilszustak.read.common.util

inline fun <T> tryOrDefault(defaultValue: T, action: () -> T): T {
    return try {
        action()
    } catch (throwable: Throwable) {
        defaultValue
    }
}

inline fun <T> tryOrNull(action: () -> T): T? =
    tryOrDefault(null, action)

inline fun <T : Any, R> T?.useOrNull(action: (T) -> R): R? {
    return if (this != null) {
        action(this)
    } else {
        null
    }
}