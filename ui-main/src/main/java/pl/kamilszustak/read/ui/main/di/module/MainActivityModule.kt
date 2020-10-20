package pl.kamilszustak.read.ui.main.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.main.activity.MainActivity

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivityInjector(): MainActivity
}