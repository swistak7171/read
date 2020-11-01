package pl.kamilszustak.read.di.module

import dagger.Module
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.main.di.MainComponent
import pl.kamilszustak.read.ui.splashscreen.di.SplashScreenComponent

@Module(
    subcomponents = [
        SplashScreenComponent::class,
        AuthenticationComponent::class,
        MainComponent::class
    ]
)
interface SubcomponentsModule