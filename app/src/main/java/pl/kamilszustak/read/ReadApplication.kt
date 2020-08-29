package pl.kamilszustak.read

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kamilszustak.read.di.component.ApplicationComponent
import pl.kamilszustak.read.di.component.DaggerApplicationComponent
import timber.log.Timber

class ReadApplication : DaggerApplication() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationComponent

    override fun onCreate() {
        super.onCreate()

        initializeDagger()
        initializeTimber()
    }

    private fun initializeDagger() {
        applicationComponent.inject(this)
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}