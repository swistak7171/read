package pl.kamilszustak.read.di.module

import dagger.Module
import pl.kamilszustak.read.ui.di.component.AuthenticationComponent

@Module(
    subcomponents = [
        AuthenticationComponent::class
    ]
)
interface SubcomponentsModule