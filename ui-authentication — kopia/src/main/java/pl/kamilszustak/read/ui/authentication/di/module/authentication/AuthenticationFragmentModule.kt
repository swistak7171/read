package pl.kamilszustak.read.ui.authentication.di.module.authentication

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.authentication.mainmenu.MainMenuFragment
import pl.kamilszustak.read.ui.authentication.signin.email.EmailSignInFragment
import pl.kamilszustak.read.ui.di.key.FragmentKey

@Module
interface AuthenticationFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(MainMenuFragment::class)
    fun bindMainMenuFragment(fragment: MainMenuFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(EmailSignInFragment::class)
    fun bindEmailSignInFragment(fragment: EmailSignInFragment): Fragment
}