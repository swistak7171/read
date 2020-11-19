package pl.kamilszustak.read.work.di.module

import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.work.ListenableWorkerFactory
import pl.kamilszustak.read.work.di.factory.WorkerFactoryImpl
import pl.kamilszustak.read.work.di.key.WorkerKey
import pl.kamilszustak.read.work.worker.ReadingGoalNotificationWorker

@Module
interface WorkerFactoryModule {
    @Binds
    fun bindWorkerFactory(factoryImpl: WorkerFactoryImpl): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(ReadingGoalNotificationWorker::class)
    fun bindReadingGoalNotificationWorkerFactory(factory: ReadingGoalNotificationWorker.Factory): ListenableWorkerFactory
}