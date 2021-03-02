package pl.kamilszustak.read.work.di.module

import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.work.ListenableWorkerFactory
import pl.kamilszustak.read.work.di.factory.WorkerFactoryImpl
import pl.kamilszustak.read.work.di.key.WorkerKey
import pl.kamilszustak.read.work.worker.DailyReadingGoalNotificationWorker

@Module
interface WorkerFactoryModule {
    @Binds
    fun bindWorkerFactory(factoryImpl: WorkerFactoryImpl): WorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(DailyReadingGoalNotificationWorker::class)
    fun bindDailyReadingGoalNotificationWorkerFactory(factory: DailyReadingGoalNotificationWorker.Factory): ListenableWorkerFactory
}