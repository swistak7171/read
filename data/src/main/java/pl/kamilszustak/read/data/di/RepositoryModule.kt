package pl.kamilszustak.read.data.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.data.access.repository.CollectionBookRepository
import pl.kamilszustak.read.data.access.repository.CountryRepository
import pl.kamilszustak.read.data.repository.CollectionBookRepositoryImpl
import pl.kamilszustak.read.data.repository.CountryRepositoryImpl

@Module
interface RepositoryModule {
    @Binds
    fun bindCountryRepository(repositoryImpl: CountryRepositoryImpl): CountryRepository

    @Binds
    fun bindCollectionBookRepository(repositoryImpl: CollectionBookRepositoryImpl): CollectionBookRepository
}