package pl.kamilszustak.read.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pl.kamilszustak.read.ReadApplication
import pl.kamilszustak.read.data.di.module.DataModule
import pl.kamilszustak.read.di.module.ApplicationModule
import pl.kamilszustak.read.di.module.FirebaseAuthenticationModule
import pl.kamilszustak.read.di.module.SubcomponentsModule
import pl.kamilszustak.read.domain.di.DomainModule
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.main.di.MainComponent
import pl.kamilszustak.read.ui.splashscreen.di.SplashScreenComponent
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        FirebaseAuthenticationModule::class,
        NetworkModule::class,
        DataModule::class,
        DomainModule::class,
        SubcomponentsModule::class,
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<ReadApplication> {
    fun splashScreenComponent(): SplashScreenComponent.Factory
    fun authenticationComponent(): AuthenticationComponent.Factory
    fun mainComponent(): MainComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}