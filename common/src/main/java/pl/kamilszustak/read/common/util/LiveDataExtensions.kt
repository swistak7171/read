package pl.kamilszustak.read.common.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline fun <S, T> LiveData<S>.map(
    initialValue: T,
    crossinline transform: (S) -> T
): LiveData<T> {
    val result = MediatorLiveData<T>()

    return result.apply {
        value = initialValue
        addSource(this@map) {
            value = transform(it)
        }
    }
}

inline fun <T> MutableLiveData<T>.update(transform: (T?) -> T?) {
    value = transform(value)
}

inline fun <T> MutableLiveData<T>.updateNotNull(transform: (T) -> T?) {
    val value = this.value
    if (value != null) {
        val updated = transform(value)
        this.value = updated
    }
}