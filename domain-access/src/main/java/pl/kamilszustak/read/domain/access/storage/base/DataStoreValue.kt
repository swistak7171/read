package pl.kamilszustak.read.domain.access.storage.base

import kotlinx.coroutines.flow.Flow

interface DataStoreValue<T> {
    fun get(): Flow<T?>
    suspend fun set(action: (T?) -> T)
    suspend fun set(value: T)
}