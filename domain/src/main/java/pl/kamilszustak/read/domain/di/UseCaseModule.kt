package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.domain.usecase.collection.AddCollectionBookUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryCodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryUseCaseImpl
import pl.kamilszustak.read.domain.usecase.quote.AddQuoteUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindGetAllCountriesUseCase(useCaseImpl: GetAllCountriesUseCaseImpl): GetAllCountriesUseCase

    @Binds
    fun bindGetDefaultCountryUseCase(useCaseImpl: GetDefaultCountryUseCaseImpl): GetDefaultCountryUseCase

    @Binds
    fun bindGetDefaultCountryCodeUseCase(useCaseImpl: GetDefaultCountryCodeUseCaseImpl): GetDefaultCountryCodeUseCase

    @Binds
    fun bindAddCollectionBookUseCase(useCaseImpl: AddCollectionBookUseCaseImpl): AddCollectionBookUseCase

    @Binds
    fun bindAddQuoteUseCase(useCaseImpl: AddQuoteUseCaseImpl): AddQuoteUseCase
}