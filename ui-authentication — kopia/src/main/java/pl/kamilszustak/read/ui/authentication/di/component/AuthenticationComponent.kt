package pl.kamilszustak.read.ui.authentication.di.component

import dagger.Subcomponent
import pl.kamilszustak.read.ui.authentication.AuthenticationActivity
import pl.kamilszustak.read.ui.authentication.di.module.AuthenticationActivityModule
import pl.kamilszustak.read.ui.authentication.di.module.authentication.AuthenticationFragmentModule
import pl.kamilszustak.read.ui.authentication.di.module.authentication.AuthenticationViewModelModule
import pl.kamilszustak.read.ui.authentication.di.scope.AuthenticationScope
import pl.kamilszustak.read.ui.di.module.BaseUiModule

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

    fun inject(activity: AuthenticationActivity)
}