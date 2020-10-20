package pl.kamilszustak.read.ui.main.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.main.main.MainActivity

@Module
interface MainActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivityInjector(): MainActivity
}