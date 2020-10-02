package pl.kamilszustak.read.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pl.kamilszustak.read.ReadApplication
import pl.kamilszustak.read.di.module.ApplicationModule
import pl.kamilszustak.read.di.module.AssistedInjectModule
import pl.kamilszustak.read.di.module.FirebaseAuthenticationModule
import pl.kamilszustak.read.di.module.SubcomponentsModule
import pl.kamilszustak.read.domain.di.UseCaseModule
import pl.kamilszustak.read.ui.authentication.di.component.AuthenticationComponent
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        SubcomponentsModule::class,
        AssistedInjectModule::class,
        FirebaseAuthenticationModule::class,
        UseCaseModule::class,
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<ReadApplication> {
    fun authenticationComponent(): AuthenticationComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}