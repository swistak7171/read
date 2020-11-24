package pl.kamilszustak.read.domain.di

import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.domain.access.usecase.book.*
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryCodeUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.domain.access.usecase.device.GetPhoneNumberUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.AddDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.CheckDailyReadingGoalCompletionUseCase
import pl.kamilszustak.read.domain.access.usecase.goal.GetLatestDailyReadingGoalUseCase
import pl.kamilszustak.read.domain.access.usecase.log.*
import pl.kamilszustak.read.domain.access.usecase.quote.*
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBarcodeUseCase
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadBitmapUseCase
import pl.kamilszustak.read.domain.access.usecase.scanner.ReadTextUseCase
import pl.kamilszustak.read.domain.access.usecase.user.EditUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.ObserveUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.GetVolumeUseCase
import pl.kamilszustak.read.domain.access.usecase.volume.ObserveVolumesUseCase
import pl.kamilszustak.read.domain.usecase.book.*
import pl.kamilszustak.read.domain.usecase.country.GetAllCountriesUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryCodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.country.GetDefaultCountryUseCaseImpl
import pl.kamilszustak.read.domain.usecase.device.GetPhoneNumberUseCaseImpl
import pl.kamilszustak.read.domain.usecase.goal.AddDailyReadingGoalUseCaseImpl
import pl.kamilszustak.read.domain.usecase.goal.CheckDailyReadingGoalCompletionUseCaseImpl
import pl.kamilszustak.read.domain.usecase.goal.GetLatestDailyReadingGoalUseCaseImpl
import pl.kamilszustak.read.domain.usecase.log.*
import pl.kamilszustak.read.domain.usecase.quote.*
import pl.kamilszustak.read.domain.usecase.scanner.ReadBarcodeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.scanner.ReadBitmapUseCaseImpl
import pl.kamilszustak.read.domain.usecase.scanner.ReadTextUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.EditUserUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.GetUserUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.ObserveUserUseCaseImpl
import pl.kamilszustak.read.domain.usecase.user.SignOutUseCaseImpl
import pl.kamilszustak.read.domain.usecase.volume.GetVolumeUseCaseImpl
import pl.kamilszustak.read.domain.usecase.volume.ObserveVolumesUseCaseImpl

@Module
interface UseCaseModule {
    @Binds
    fun bindGetPhoneNumberUseCase(useCaseImpl: GetPhoneNumberUseCaseImpl): GetPhoneNumberUseCase

    @Binds
    fun bindGetAllCountriesUseCase(useCaseImpl: GetAllCountriesUseCaseImpl): GetAllCountriesUseCase

    @Binds
    fun bindGetDefaultCountryUseCase(useCaseImpl: GetDefaultCountryUseCaseImpl): GetDefaultCountryUseCase

    @Binds
    fun bindGetDefaultCountryCodeUseCase(useCaseImpl: GetDefaultCountryCodeUseCaseImpl): GetDefaultCountryCodeUseCase

    @Binds
    fun bindAddBookUseCase(useCaseImpl: AddBookUseCaseImpl): AddBookUseCase

    @Binds
    fun bindObserveAllBooksUseCase(useCaseImpl: ObserveAllBooksUseCaseImpl): ObserveAllBooksUseCase

    @Binds
    fun bindObserveBookUseCase(useCaseimpl: ObserveBookUseCaseImpl): ObserveBookUseCase

    @Binds
    fun bindGetBookUseCase(useCaseImpl: GetBookUseCaseImpl): GetBookUseCase

    @Binds
    fun bindEditBookUseCase(useCaseImpl: EditBookUseCaseImpl): EditBookUseCase

    @Binds
    fun bindDeleteBookUseCase(useCaseImpl: DeleteBookUseCaseImpl): DeleteBookUseCase

    @Binds
    fun bindAddQuoteUseCase(useCaseImpl: AddQuoteUseCaseImpl): AddQuoteUseCase

    @Binds
    fun bindEditQuoteUseCase(useCaseImpl: EditQuoteUseCaseImpl): EditQuoteUseCase

    @Binds
    fun bindDeleteQuoteUseCase(useCaseImpl: DeleteQuoteUseCaseImpl): DeleteQuoteUseCase

    @Binds
    fun bindObserveAllQuotesUseCase(useCaseImpl: ObserveAllQuotesUseCaseImpl): ObserveAllQuotesUseCase

    @Binds
    fun bindGetQuoteUseCase(useCaseImpl: GetQuoteUseCaseImpl): GetQuoteUseCase

    @Binds
    fun bindGetQuoteColorsUseCase(useCaseImpl: GetQuoteColorsUseCaseImpl): GetQuoteColorsUseCase

    @Binds
    fun bindReadBarcodeUseCase(useCaseImpl: ReadBarcodeUseCaseImpl): ReadBarcodeUseCase

    @Binds
    fun bindReadTextUseCase(useCaseImpl: ReadTextUseCaseImpl): ReadTextUseCase

    @Binds
    fun bindReadBitmapUseCase(useCaseImpl: ReadBitmapUseCaseImpl): ReadBitmapUseCase

    @Binds
    fun bindGetUserUseCase(useCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindObserveUserUseCase(useCaseImpl: ObserveUserUseCaseImpl): ObserveUserUseCase

    @Binds
    fun bindEditUserUseCase(useCaseImpl: EditUserUseCaseImpl): EditUserUseCase

    @Binds
    fun bindSignOutUseCase(useCaseImpl: SignOutUseCaseImpl): SignOutUseCase

    @Binds
    fun bindObserveVolumesUseCase(useCaseImpl: ObserveVolumesUseCaseImpl): ObserveVolumesUseCase

    @Binds
    fun bindGetVolumeUseCase(useCaseImpl: GetVolumeUseCaseImpl): GetVolumeUseCase

    @Binds
    fun bindAddLogEntryUseCase(useCaseImpl: AddLogEntryUseCaseImpl): AddLogEntryUseCase

    @Binds
    fun bindEditLogEntryUseCase(useCaseImpl: EditLogEntryUseCaseImpl): EditLogEntryUseCase

    @Binds
    fun bindDeleteLogEntryUseCase(useCaseImpl: DeleteLogEntryUseCaseImpl): DeleteLogEntryUseCase

    @Binds
    fun bindGetLogEntryUseCase(useCaseImpl: GetLogEntryUseCaseImpl): GetLogEntryUseCase

    @Binds
    fun bindGetReadingLogUseCase(useCaseImpl: GetReadingLogUseCaseImpl): GetReadingLogUseCase

    @Binds
    fun bindGetReadingLogByDateUseCase(useCaseImpl: GetReadingLogByDateUseCaseImpl): GetReadingLogByDateUseCase

    @Binds
    fun bindObserveLogEntryUseCase(useCaseImpl: ObserveLogEntryUseCaseImpl): ObserveLogEntryUseCase

    @Binds
    fun bindObserveReadingLogUseCase(useCaseImpl: ObserveReadingLogUseCaseImpl): ObserveReadingLogUseCase

    @Binds
    fun bindDeleteBookReadingLogUseCase(useCaseImpl: DeleteBookReadingLogUseCaseImpl): DeleteBookReadingLogUseCase

    @Binds
    fun bindGetDailyReadingGoalUseCase(useCaseImpl: GetLatestDailyReadingGoalUseCaseImpl): GetLatestDailyReadingGoalUseCase

    @Binds
    fun bindSetDailyReadingGoalUseCase(useCaseImpl: AddDailyReadingGoalUseCaseImpl): AddDailyReadingGoalUseCase

    @Binds
    fun bindCheckDailyReadingGoalCompletionUseCase(useCaseImpl: CheckDailyReadingGoalCompletionUseCaseImpl): CheckDailyReadingGoalCompletionUseCase
}