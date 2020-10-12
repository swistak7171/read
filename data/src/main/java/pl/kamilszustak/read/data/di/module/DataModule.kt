package pl.kamilszustak.read.data.di.module

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        DatabaseModule::class,
    ]
)
interface DataModule