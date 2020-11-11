package pl.kamilszustak.read.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.common.di.ResourceModule

@Module(
    includes = [
        ResourceModule::class,
    ]
)
interface ApplicationModule {
    @Binds
    fun provideApplicationContext(application: Application): Context
}