package pl.kamilszustak.read.domain.di

import dagger.Module

@Module(
    includes = [
        UseCaseModule::class,
        StorageModule::class,
    ]
)
interface DomainModule