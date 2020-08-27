package pl.kamilszustak.read.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.kamilszustak.read.ReadApplication
import pl.kamilszustak.read.di.module.ApplicationModule
import pl.kamilszustak.read.di.module.AssistedInjectModule
import pl.kamilszustak.read.ui.di.module.ActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        AssistedInjectModule::class,
        ActivityModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ReadApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}