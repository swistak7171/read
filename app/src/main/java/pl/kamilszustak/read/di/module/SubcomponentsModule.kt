package pl.kamilszustak.read.di.module

import dagger.Module
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.main.di.MainComponent

@Module(
    subcomponents = [
        AuthenticationComponent::class,
        MainComponent::class
    ]
)
interface SubcomponentsModule