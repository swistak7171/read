package pl.kamilszustak.read.ui.di.module.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.activity.authentication.mainmenu.MainMenuViewModel
import pl.kamilszustak.read.ui.di.factory.ViewModelFactory
import pl.kamilszustak.read.ui.di.key.ViewModelKey

@Module
interface AuthenticationViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    fun bindMainMenuViewModel(viewModel: MainMenuViewModel): ViewModel
}