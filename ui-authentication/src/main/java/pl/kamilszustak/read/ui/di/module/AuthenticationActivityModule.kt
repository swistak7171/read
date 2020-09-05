package pl.kamilszustak.read.ui.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.AuthenticationActivity
import pl.kamilszustak.read.ui.di.module.authentication.AuthenticationFragmentModule
import pl.kamilszustak.read.ui.di.module.authentication.AuthenticationViewModelModule
import pl.kamilszustak.ui.di.scope.ActivityScope

@Module
interface AuthenticationActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            AuthenticationFragmentModule::class,
            AuthenticationViewModelModule::class,
        ]
    )
    fun contributeAuthenticationActivityInjector(): AuthenticationActivity
}