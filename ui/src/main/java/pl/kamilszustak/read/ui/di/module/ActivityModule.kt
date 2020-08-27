package pl.kamilszustak.read.ui.di.module

import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.activity.authentication.AuthenticationActivity
import pl.kamilszustak.read.ui.di.factory.InjectingFragmentFactory
import pl.kamilszustak.read.ui.di.scope.ActivityScope

@Module
interface ActivityModule {
    @Binds
    fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            //AuthenticationActivityFragmentModule::class
        ]
    )
    fun contributeAuthenticationActivity(): AuthenticationActivity
}