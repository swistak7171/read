package pl.kamilszustak.ui.di

import dagger.Component
import pl.kamilszustak.ui.di.module.BaseUiModule

@Component(
    modules = [
        BaseUiModule::class,
    ]
)
interface BaseUiComponent