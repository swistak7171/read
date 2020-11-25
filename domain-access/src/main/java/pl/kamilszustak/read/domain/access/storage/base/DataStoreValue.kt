package pl.kamilszustak.read.domain.access.storage.base

import kotlinx.coroutines.flow.Flow

interface DataStoreValue<T> {
    fun asFlow(): Flow<T?>
    fun asNotNullableFlow(): Flow<T>
    suspend fun get(): T?
    suspend fun edit(action: (T?) -> T)
    suspend fun edit(value: T)
}