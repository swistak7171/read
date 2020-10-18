package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.usecase.barcode.ReadBarcodeUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.AddCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetAllCollectionBooksUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.GetCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.UpdateCollectionBookUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.AddQuoteUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.GetAllQuotesUseCase
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.domain.usecase.barcode.ReadBarcodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.collection.AddCollectionBookUseCaseImpl
import pl.kamilszustak.read.domain.usecase.collection.GetAllCollectionBooksUseCaseImpl
import pl.kamilszustak.read.domain.usecase.collection.GetCollectionBookUseCaseImpl
import pl.kamilszustak.read.domain.usecase.collection.UpdateCollectionBookUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryCodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryUseCaseImpl
import pl.kamilszustak.read.domain.usecase.quote.AddQuoteUseCaseImpl
import pl.kamilszustak.read.domain.usecase.quote.GetAllQuotesUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.SignOutUseCaseImpl

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
    fun bindGetAllCollectionBooksUseCase(useCaseImpl: GetAllCollectionBooksUseCaseImpl): GetAllCollectionBooksUseCase

    @Binds
    fun bindGetCollectionBookUseCase(useCaseimpl: GetCollectionBookUseCaseImpl): GetCollectionBookUseCase

    @Binds
    fun bindUpdateCollectionBookUseCase(useCaseImpl: UpdateCollectionBookUseCaseImpl): UpdateCollectionBookUseCase

    @Binds
    fun bindAddQuoteUseCase(useCaseImpl: AddQuoteUseCaseImpl): AddQuoteUseCase

    @Binds
    fun bindGetAllQuotesUseCase(useCaseImpl: GetAllQuotesUseCaseImpl): GetAllQuotesUseCase

    @Binds
    fun bindReadBarcodeUseCase(useCaseImpl: ReadBarcodeUseCaseImpl): ReadBarcodeUseCase

    @Binds
    fun bindGetUserUseCase(useCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindSignOutUseCase(useCaseImpl: SignOutUseCaseImpl): SignOutUseCase
}