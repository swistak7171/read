package pl.kamilszustak.read.ui.main.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(
    includes = [AssistedInject_MainAssistedInjectModule::class]
)
@AssistedModule
interface MainAssistedInjectModule