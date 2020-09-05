package pl.kamilszustak.read

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kamilszustak.read.di.ApplicationComponent
import pl.kamilszustak.read.di.DaggerApplicationComponent
import pl.kamilszustak.read.ui.di.DaggerUiComponent
import timber.log.Timber

class ReadApplication : DaggerApplication() {
    val applicationComponent: ApplicationComponent by lazy {
        val uiComponent = DaggerUiComponent.create()

        DaggerApplicationComponent.builder()
            .application(this)
            .uiComponent(uiComponent)
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