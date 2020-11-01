package pl.kamilszustak.read.ui.splashscreen.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.base.di.key.ViewModelKey
import pl.kamilszustak.read.ui.splashscreen.activity.SplashScreenViewModel

@Module
interface SplashScreenViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel
}