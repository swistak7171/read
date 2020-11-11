package pl.kamilszustak.read.common.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.common.resource.ResourceProvider
import pl.kamilszustak.read.common.resource.ResourceProviderImpl

@Module
interface ResourceModule {
    @Binds
    fun bindResourceProvider(providerImpl: ResourceProviderImpl): ResourceProvider
}