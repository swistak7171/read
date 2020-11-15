package pl.kamilszustak.read.ui.main.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.base.di.key.ViewModelKey
import pl.kamilszustak.read.ui.main.activity.MainViewModel
import pl.kamilszustak.read.ui.main.collection.CollectionViewModel
import pl.kamilszustak.read.ui.main.collection.log.ReadingLogViewModel
import pl.kamilszustak.read.ui.main.profile.ProfileViewModel
import pl.kamilszustak.read.ui.main.profile.edit.ProfileEditViewModel
import pl.kamilszustak.read.ui.main.quotes.QuotesViewModel
import pl.kamilszustak.read.ui.main.scanner.ScannerViewModel
import pl.kamilszustak.read.ui.main.search.SearchViewModel

@Module
interface MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollectionViewModel::class)
    fun bindCollectionViewModel(viewModel: CollectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScannerViewModel::class)
    fun bindScannerViewModel(viewModel: ScannerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel::class)
    fun bindQuotesViewModel(viewModel: QuotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReadingLogViewModel::class)
    fun bindReadingLogViewModel(viewModel: ReadingLogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileEditViewModel::class)
    fun bindProfileEditViewModel(viewModel: ProfileEditViewModel): ViewModel
}