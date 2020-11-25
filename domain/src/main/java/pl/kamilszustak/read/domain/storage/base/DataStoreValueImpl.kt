package pl.kamilszustak.read.domain.storage.base

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.domain.access.storage.base.DataStoreValue

class DataStoreValueImpl<T>(
    private val dataStore: DataStore<Preferences>,
    private val key: Preferences.Key<T>,
) : DataStoreValue<T> {

    override fun get(): Flow<T?> =
        dataStore.data.map { it[key] }

    override suspend fun set(action: (T?) -> T) {
        dataStore.edit { preferences ->
            val value = preferences[key]
            preferences[key] = action(value)
        }
    }

    override suspend fun set(value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}