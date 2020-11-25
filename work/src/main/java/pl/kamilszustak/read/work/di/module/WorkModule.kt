package pl.kamilszustak.read.work.di.module

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        WorkerFactoryModule::class,
        AssistedInjectModule::class,
    ]
)
class WorkModule {
    @Provides
    @Singleton
    fun provideWorkManager(application: Application, workerFactory: WorkerFactory): WorkManager {
        val configuration = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build()

        WorkManager.initialize(application, configuration)

        return WorkManager.getInstance(application)
    }
}