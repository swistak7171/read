package pl.kamilszustak.read.di.module

import dagger.Module
import pl.kamilszustak.read.ui.authentication.di.component.AuthenticationComponent

@Module(
    subcomponents = [
        AuthenticationComponent::class
    ]
)
interface SubcomponentsModule