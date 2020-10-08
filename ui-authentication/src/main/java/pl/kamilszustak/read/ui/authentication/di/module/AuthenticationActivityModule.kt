package pl.kamilszustak.read.ui.authentication.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.authentication.AuthenticationActivity

@Module
interface AuthenticationActivityModule {
    @ContributesAndroidInjector
    fun contributeAuthenticationActivityInjector(): AuthenticationActivity
}