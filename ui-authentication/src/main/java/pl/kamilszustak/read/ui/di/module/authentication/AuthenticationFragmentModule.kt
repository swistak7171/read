package pl.kamilszustak.read.ui.di.module.authentication

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.di.key.FragmentKey
import pl.kamilszustak.read.ui.mainmenu.MainMenuFragment
import pl.kamilszustak.read.ui.signin.email.EmailSignInFragment

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