package pl.kamilszustak.read.ui.splashscreen.di

import dagger.Subcomponent
import pl.kamilszustak.read.ui.base.di.module.BaseUiModule
import pl.kamilszustak.read.ui.splashscreen.activity.SplashScreenActivity
import pl.kamilszustak.read.ui.splashscreen.di.module.SplashScreenActivityModule
import pl.kamilszustak.read.ui.splashscreen.di.module.SplashScreenViewModelModule
import pl.kamilszustak.read.ui.splashscreen.di.scope.SplashScreenScope

@Subcomponent(
    modules = [
        BaseUiModule::class,
        SplashScreenActivityModule::class,
        SplashScreenViewModelModule::class,
    ]
)
@SplashScreenScope
interface SplashScreenComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashScreenComponent
    }

    interface ComponentProvider {
        fun provideSplashScreenComponent(): SplashScreenComponent
    }

    fun inject(activity: SplashScreenActivity)
}