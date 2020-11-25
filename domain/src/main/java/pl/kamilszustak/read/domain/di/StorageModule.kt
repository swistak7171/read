package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.storage.SettingsStorage
import pl.kamilszustak.read.domain.storage.SettingsStorageImpl

@Module
interface StorageModule {
    @Binds
    fun bindSettingsStorage(storageImpl: SettingsStorageImpl): SettingsStorage
}