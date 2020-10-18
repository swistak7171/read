package pl.kamilszustak.read.ui.authentication.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.authentication.mainmenu.MainMenuViewModel
import pl.kamilszustak.read.ui.authentication.signin.email.EmailSignInViewModel
import pl.kamilszustak.read.ui.authentication.signin.phone.PhoneSignInViewModel
import pl.kamilszustak.read.ui.base.di.key.ViewModelKey

@Module
interface AuthenticationViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    fun bindMainMenuViewModel(viewModel: MainMenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmailSignInViewModel::class)
    fun bindEmailSignInViewModel(viewModel: EmailSignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhoneSignInViewModel::class)
    fun bindPhoneSignInViewModel(viewModel: PhoneSignInViewModel): ViewModel
}