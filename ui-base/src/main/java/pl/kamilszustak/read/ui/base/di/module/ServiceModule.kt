package pl.kamilszustak.read.ui.base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kamilszustak.read.ui.base.service.FirebaseCloudMessagingService

@Module
interface ServiceModule {
    @ContributesAndroidInjector
    fun contributeFirebaseCloudMessagingServiceInjector(): FirebaseCloudMessagingService
}