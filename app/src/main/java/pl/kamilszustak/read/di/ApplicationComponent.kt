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
import pl.kamilszustak.read.ui.di.UiComponent

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        AssistedInjectModule::class,
    ],
    dependencies = [
        UiComponent::class,
    ]
)
interface ApplicationComponent : AndroidInjector<ReadApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun uiComponent(component: UiComponent): Builder
        fun build(): ApplicationComponent
    }
}