package pl.kamilszustak.read.ui.di.module

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.ui.di.factory.InjectingFragmentFactory
import pl.kamilszustak.read.ui.di.factory.ViewModelFactory

@Module
interface BaseUiModule {
    @Binds
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}