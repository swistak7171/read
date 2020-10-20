package pl.kamilszustak.read.di

import dagger.Module

@Module(
    includes = [
        MoshiModule::class,
        RetrofitModule::class
    ]
)
interface NetworkModule