package pl.kamilszustak.read.work.di.module

import androidx.work.WorkerFactory
import dagger.Binds
import dagger.Module
import pl.kamilszustak.read.work.di.factory.WorkerFactoryImpl

@Module
interface WorkerFactoryModule {
    @Binds
    fun bindWorkerFactory(factoryImpl: WorkerFactoryImpl): WorkerFactory


}