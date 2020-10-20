package pl.kamilszustak.read.ui.main.di

import dagger.Subcomponent
import pl.kamilszustak.read.ui.base.di.module.BaseUiModule
import pl.kamilszustak.read.ui.main.main.MainActivity
import pl.kamilszustak.read.ui.main.di.module.MainActivityModule
import pl.kamilszustak.read.ui.main.di.module.MainAssistedInjectModule
import pl.kamilszustak.read.ui.main.di.module.MainFragmentModule
import pl.kamilszustak.read.ui.main.di.module.MainViewModelModule
import pl.kamilszustak.read.ui.main.di.scope.MainScope

@Subcomponent(
    modules = [
        BaseUiModule::class,
        MainAssistedInjectModule::class,
        MainActivityModule::class,
        MainFragmentModule::class,
        MainViewModelModule::class,
    ]
)
@MainScope
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    interface ComponentProvider {
        fun provideMainComponent(): MainComponent
    }

    fun inject(activity: MainActivity)
}