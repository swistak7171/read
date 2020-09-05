package pl.kamilszustak.ui.di

import dagger.Component
import pl.kamilszustak.ui.di.module.UiModule

@Component(
    modules = [
        UiModule::class,
    ]
)
interface UiComponent