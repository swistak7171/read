package pl.kamilszustak.read.data.di.module

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.data.access.repository.*
import pl.kamilszustak.read.data.repository.*

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

    @Binds
    fun bindLogEntryRepository(repositoryImpl: LogEntryRepositoryImpl): LogEntryRepository
}