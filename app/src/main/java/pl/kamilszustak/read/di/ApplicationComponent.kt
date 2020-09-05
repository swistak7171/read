package pl.kamilszustak.read.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import pl.kamilszustak.read.ReadApplication
import pl.kamilszustak.read.di.module.ApplicationModule
import pl.kamilszustak.read.di.module.AssistedInjectModule
import pl.kamilszustak.read.di.scope.ApplicationScope
import pl.kamilszustak.read.ui.di.AuthenticationComponent

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        AssistedInjectModule::class,
    ],
    dependencies = [
        AuthenticationComponent::class,
    ]
)
interface ApplicationComponent : AndroidInjector<ReadApplication> {
    val uiComponent: AuthenticationComponent

    @Component.Builder
    interface Builder {
        fun application(@BindsInstance application: Application): Builder
        fun authenticationComponent(component: AuthenticationComponent): Builder
        fun build(): ApplicationComponent
    }
}