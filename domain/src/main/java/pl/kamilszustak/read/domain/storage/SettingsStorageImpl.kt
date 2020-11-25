package pl.kamilszustak.read.domain.storage

import android.app.Application
import pl.kamilszustak.read.domain.R
import pl.kamilszustak.read.domain.access.storage.base.DataStoreValue
import pl.kamilszustak.read.domain.storage.base.Storage
import javax.inject.Inject

class SettingsStorageImpl @Inject constructor(application: Application) : Storage(application, R.string.settings_data_store_name) {
    val isDailyReadingGoalEnabled: DataStoreValue<Boolean> = getValue(R.string.daily_reading_goal_data_store_value_name)
}