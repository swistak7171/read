package pl.kamilszustak.read

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kamilszustak.read.di.ApplicationComponent
import pl.kamilszustak.read.di.DaggerApplicationComponent
import pl.kamilszustak.read.ui.di.DaggerAuthenticationComponent
import pl.kamilszustak.ui.di.DaggerBaseUiComponent
import timber.log.Timber

class ReadApplication : DaggerApplication() {
    val applicationComponent: ApplicationComponent by lazy {
        val baseUiComponent = DaggerBaseUiComponent.create()
        val authenticationComponent = DaggerAuthenticationComponent.builder()
            .baseUiComponent(baseUiComponent)
            .build()

        DaggerApplicationComponent.builder()
            .application(this)
            .authenticationComponent(authenticationComponent)
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