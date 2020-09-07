package pl.kamilszustak.read.ui.di

import dagger.Component
import pl.kamilszustak.read.di.ApplicationComponent
import pl.kamilszustak.read.ui.AuthenticationActivity
import pl.kamilszustak.read.ui.di.module.AuthenticationActivityModule
import pl.kamilszustak.ui.di.BaseUiComponent
import pl.kamilszustak.ui.di.scope.ModuleScope

@ModuleScope
@Component(
    modules = [
        AuthenticationActivityModule::class,
    ],
    dependencies = [
        BaseUiComponent::class,
        ApplicationComponent::class,
    ]
)
interface AuthenticationComponent {
    fun inject(activity: AuthenticationActivity)
}