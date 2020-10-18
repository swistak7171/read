package pl.kamilszustak.read

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kamilszustak.read.di.ApplicationComponent
import pl.kamilszustak.read.di.DaggerApplicationComponent
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.main.di.MainComponent
import timber.log.Timber

class ReadApplication : DaggerApplication(),
    AuthenticationComponent.ComponentProvider,
    MainComponent.ComponentProvider
{
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationComponent

    override fun provideAuthenticationComponent(): AuthenticationComponent =
        applicationComponent.authenticationComponent().create()

    override fun provideMainComponent(): MainComponent =
        applicationComponent.mainComponent().create()

    override fun onCreate() {
        super.onCreate()

        initializeDagger()
        initializeTimber()
    }

    private fun initializeDagger() {
        applicationComponent.inject(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}