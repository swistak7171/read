package pl.kamilszustak.read

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kamilszustak.read.di.ApplicationComponent
import pl.kamilszustak.read.di.DaggerApplicationComponent
import pl.kamilszustak.read.ui.di.AuthenticationComponentProvider
import pl.kamilszustak.ui.di.DaggerBaseUiComponent
import timber.log.Timber

class ReadApplication : DaggerApplication(), AuthenticationComponentProvider {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .authenticationComponent(provideAuthenticationComponent())
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationComponent

    override fun provideAuthenticationComponent(): AuthenticationComponent {
        val baseUiComponent = DaggerBaseUiComponent.create()
        val authenticationComponent = DaggerAuthenticationComponent.builder()
            .baseUiComponent(baseUiComponent)
            .build()
    }

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