package pl.kamilszustak.read.ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.AuthenticationActivity

@Module
interface AuthenticationActivityModule {
    @ContributesAndroidInjector
    fun contributeAuthenticationActivityInjector(): AuthenticationActivity
}