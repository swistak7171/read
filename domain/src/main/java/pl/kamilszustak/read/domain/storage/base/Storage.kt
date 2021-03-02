package pl.kamilszustak.read.domain.storage.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import pl.kamilszustak.read.domain.access.storage.base.DataStoreValue

abstract class Storage(
    protected val application: Application,
    @StringRes nameResourceId: Int
) {
    protected val dataStore: DataStore<Preferences>

    init {
        val name = application.getString(nameResourceId)
        dataStore = application.createDataStore(name)
    }

    protected inline fun <reified T : Any> getValue(@StringRes nameResourceId: Int): DataStoreValue<T> {
        val name = application.getString(nameResourceId)
        val key = when (T::class) {
            Int::class -> intPreferencesKey(name)
            Double::class -> doublePreferencesKey(name)
            String::class -> stringPreferencesKey(name)
            Boolean::class -> booleanPreferencesKey(name)
            Float::class -> floatPreferencesKey(name)
            Long::class -> longPreferencesKey(name)
            Set::class -> stringSetPreferencesKey(name)
            else -> throw IllegalArgumentException("Invalid Preferences.Key type: ${T::class.qualifiedName}")
        } as Preferences.Key<T>

        return DataStoreValueImpl(dataStore, key)
    }
}