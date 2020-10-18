package pl.kamilszustak.read.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {
    @Binds
    fun provideApplicationContext(application: Application): Context
}