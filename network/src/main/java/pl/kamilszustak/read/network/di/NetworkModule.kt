package pl.kamilszustak.read.network.di

import dagger.Module

@Module(
    includes = [
        MoshiModule::class,
        RetrofitModule::class
    ]
)
interface NetworkModule