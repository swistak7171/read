package pl.kamilszustak.read.ui.splashscreen.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.splashscreen.activity.SplashScreenActivity

@Module
interface SplashScreenActivityModule {
    @ContributesAndroidInjector
    fun contributeSplashScreenActivityInjector(): SplashScreenActivity
}