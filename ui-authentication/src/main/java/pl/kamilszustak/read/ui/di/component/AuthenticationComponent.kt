package pl.kamilszustak.read.ui.di.component

import dagger.Subcomponent
import pl.kamilszustak.read.ui.AuthenticationActivity
import pl.kamilszustak.read.ui.di.module.AuthenticationActivityModule
import pl.kamilszustak.read.ui.di.module.authentication.AuthenticationFragmentModule
import pl.kamilszustak.read.ui.di.module.authentication.AuthenticationViewModelModule
import pl.kamilszustak.read.ui.di.scope.AuthenticationScope
import pl.kamilszustak.ui.di.module.BaseUiModule

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