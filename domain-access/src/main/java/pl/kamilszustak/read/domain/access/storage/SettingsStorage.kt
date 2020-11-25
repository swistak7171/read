package pl.kamilszustak.read.domain.access.storage

import pl.kamilszustak.read.domain.access.storage.base.DataStoreValue

interface SettingsStorage {
    val isDailyReadingGoalEnabled: DataStoreValue<Boolean>
}