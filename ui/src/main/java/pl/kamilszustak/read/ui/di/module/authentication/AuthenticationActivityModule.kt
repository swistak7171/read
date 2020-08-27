package pl.kamilszustak.read.ui.di.module.authentication

import dagger.Module

@Module(
    includes = [
        AuthenticationViewModelModule::class
    ]
)
interface AuthenticationActivityModule {
}