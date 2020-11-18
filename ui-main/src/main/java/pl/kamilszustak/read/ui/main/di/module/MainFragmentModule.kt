package pl.kamilszustak.read.ui.main.di.module

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.base.di.key.FragmentKey
import pl.kamilszustak.read.ui.main.book.details.BookDetailsFragment
import pl.kamilszustak.read.ui.main.book.edit.BookEditFragment
import pl.kamilszustak.read.ui.main.book.progress.ReadingProgressDialogFragment
import pl.kamilszustak.read.ui.main.collection.CollectionFragment
import pl.kamilszustak.read.ui.main.collection.log.ReadingLogFragment
import pl.kamilszustak.read.ui.main.collection.log.details.LogEntryDetailsFragment
import pl.kamilszustak.read.ui.main.profile.ProfileFragment
import pl.kamilszustak.read.ui.main.profile.edit.ProfileEditFragment
import pl.kamilszustak.read.ui.main.quotes.QuotesFragment
import pl.kamilszustak.read.ui.main.quotes.edit.QuoteEditFragment
import pl.kamilszustak.read.ui.main.scanner.ScannerFragment
import pl.kamilszustak.read.ui.main.scanner.selection.TextSelectionFragment
import pl.kamilszustak.read.ui.main.search.SearchFragment

@Module
interface MainFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(CollectionFragment::class)
    fun bindCollectionFragment(fragment: CollectionFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    fun bindSearchFragment(fragment: SearchFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ScannerFragment::class)
    fun bindScannerFragment(fragment: ScannerFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(QuotesFragment::class)
    fun bindQuotesFragment(fragment: QuotesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ProfileFragment::class)
    fun bindProfileFragment(fragment: ProfileFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BookEditFragment::class)
    fun bindBookEditFragment(fragment: BookEditFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ReadingProgressDialogFragment::class)
    fun bindReadingProgressDialogFragment(fragment: ReadingProgressDialogFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(QuoteEditFragment::class)
    fun bindQuoteEditFragment(fragment: QuoteEditFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ReadingLogFragment::class)
    fun bindReadingLogFragment(fragment: ReadingLogFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(LogEntryDetailsFragment::class)
    fun bindLogEntryDetailsFragment(fragment: LogEntryDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BookDetailsFragment::class)
    fun bindBookDetailsFragment(fragment: BookDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TextSelectionFragment::class)
    fun bindTextSelectionFragment(fragment: TextSelectionFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ProfileEditFragment::class)
    fun bindProfileEditFragment(fragment: ProfileEditFragment): Fragment
}