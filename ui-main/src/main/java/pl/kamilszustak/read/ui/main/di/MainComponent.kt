package pl.kamilszustak.read.ui.main.di

import dagger.Subcomponent
import pl.kamilszustak.read.ui.base.di.module.BaseUiModule
import pl.kamilszustak.read.ui.main.MainActivity
import pl.kamilszustak.read.ui.main.di.scope.MainScope

@Subcomponent(
    modules = [
        BaseUiModule::class,
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