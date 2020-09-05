package pl.kamilszustak.read.ui.di

import dagger.Component
import pl.kamilszustak.read.ui.di.module.ActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
    ]
)
interface UiComponent