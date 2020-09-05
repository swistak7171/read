package pl.kamilszustak.read.ui.di

import dagger.Component
import pl.kamilszustak.read.ui.di.module.AuthenticationActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthenticationActivityModule::class,
    ]
)
interface AuthenticationComponent