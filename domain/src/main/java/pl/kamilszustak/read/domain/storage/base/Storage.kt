package pl.kamilszustak.read.domain.storage.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
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
        val key = preferencesKey<T>(name)

        return DataStoreValueImpl(dataStore, key)
    }
}