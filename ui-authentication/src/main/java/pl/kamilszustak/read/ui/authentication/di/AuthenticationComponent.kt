package pl.kamilszustak.read.ui.authentication.di

import dagger.Subcomponent
import pl.kamilszustak.read.ui.authentication.AuthenticationActivity
import pl.kamilszustak.read.ui.authentication.di.module.AuthenticationActivityModule
import pl.kamilszustak.read.ui.authentication.di.module.AuthenticationFragmentModule
import pl.kamilszustak.read.ui.authentication.di.module.AuthenticationViewModelModule
import pl.kamilszustak.read.ui.authentication.di.scope.AuthenticationScope
import pl.kamilszustak.read.ui.base.di.module.BaseUiModule

@Subcomponent(
    modules = [
        BaseUiModule::class,
        AuthenticationActivityModule::class,
        AuthenticationFragmentModule::class,
        AuthenticationViewModelModule::class,
    ]
)
@AuthenticationScope
interface AuthenticationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthenticationComponent
    }

    interface ComponentProvider {
        fun provideAuthenticationComponent(): AuthenticationComponent
    }

    fun inject(activity: AuthenticationActivity)
}