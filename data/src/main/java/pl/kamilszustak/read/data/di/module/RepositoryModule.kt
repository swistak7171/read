package pl.kamilszustak.read.data.di.module

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.data.access.repository.BookRepository
import pl.kamilszustak.read.data.access.repository.CountryRepository
import pl.kamilszustak.read.data.access.repository.QuoteRepository
import pl.kamilszustak.read.data.access.repository.VolumeRepository
import pl.kamilszustak.read.data.repository.BookRepositoryImpl
import pl.kamilszustak.read.data.repository.CountryRepositoryImpl
import pl.kamilszustak.read.data.repository.QuoteRepositoryImpl
import pl.kamilszustak.read.data.repository.VolumeRepositoryImpl

@Module
interface RepositoryModule {
    @Binds
    fun bindCountryRepository(repositoryImpl: CountryRepositoryImpl): CountryRepository

    @Binds
    fun bindBookRepository(repositoryImpl: BookRepositoryImpl): BookRepository

    @Binds
    fun bindQuoteRepository(repositoryImpl: QuoteRepositoryImpl): QuoteRepository

    @Binds
    fun bindVolumeRepository(repositoryImpl: VolumeRepositoryImpl): VolumeRepository
}