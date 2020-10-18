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

inline fun <T : Any, R> T?.useOrNull(action: (T) -> R): R? =
    if (this != null) action(this) else null

inline fun <T, R> runNotNull(value: T?, action: (T) -> R): Result<R> {
    return if (value != null) {
        val result = action(value)
        Result.success(result)
    } else {
        val exception = Exception("Value is null")
        Result.failure(exception)
    }
}