package pl.kamilszustak.read.data.di

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        DatabaseModule::class,
    ]
)
interface DataModule