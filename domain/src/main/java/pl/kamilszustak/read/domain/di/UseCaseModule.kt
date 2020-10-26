package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.usecase.barcode.ReadBarcodeUseCase
import pl.kamilszustak.read.domain.access.usecase.collection.*
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.domain.access.usecase.quote.*
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.GetVolumeUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.ObserveVolumesUseCase
import pl.kamilszustak.read.domain.usecase.barcode.ReadBarcodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.collection.*
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryCodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryUseCaseImpl
import pl.kamilszustak.read.domain.usecase.quote.*
import pl.kamilszustak.read.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.SignOutUseCaseImpl
import pl.kamilszustak.read.domain.usecase.volume.GetVolumeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.volume.ObserveVolumesUseCaseImpl

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
    fun bindEditCollectionBookUseCase(useCaseImpl: EditCollectionBookUseCaseImpl): EditCollectionBookUseCase

    @Binds
    fun bindDeleteCollectionBookUseCase(useCaseImpl: DeleteCollectionBookUseCaseImpl): DeleteCollectionBookUseCase

    @Binds
    fun bindAddQuoteUseCase(useCaseImpl: AddQuoteUseCaseImpl): AddQuoteUseCase

    @Binds
    fun bindEditQuoteUseCase(useCaseImpl: EditQuoteUseCaseImpl): EditQuoteUseCase

    @Binds
    fun bindDeleteQuoteUseCase(useCaseImpl: DeleteQuoteUseCaseImpl): DeleteQuoteUseCase

    @Binds
    fun bindGetAllQuotesUseCase(useCaseImpl: GetAllQuotesUseCaseImpl): GetAllQuotesUseCase

    @Binds
    fun bindGetQuoteUseCase(useCaseImpl: GetQuoteUseCaseImpl): GetQuoteUseCase

    @Binds
    fun bindReadBarcodeUseCase(useCaseImpl: ReadBarcodeUseCaseImpl): ReadBarcodeUseCase

    @Binds
    fun bindGetUserUseCase(useCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindSignOutUseCase(useCaseImpl: SignOutUseCaseImpl): SignOutUseCase

    @Binds
    fun bindObserveVolumesUseCase(useCaseImpl: ObserveVolumesUseCaseImpl): ObserveVolumesUseCase

    @Binds
    fun bindGetVolumeUseCase(useCaseImpl: GetVolumeUseCaseImpl): GetVolumeUseCase
}