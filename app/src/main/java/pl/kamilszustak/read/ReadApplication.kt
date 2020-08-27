package pl.kamilszustak.read

import android.app.Application
import timber.log.Timber

class ReadApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}