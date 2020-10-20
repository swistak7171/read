package pl.kamilszustak.read.model.mapper

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import pl.kamilszustak.read.common.util.withDefaultContext

abstract class Mapper<T, R> {
    abstract fun map(model: T): R

    suspend fun mapAsync(model: T): R = withDefaultContext {
        map(model)
    }

    fun mapAll(models: Iterable<T>): List<R> = models.map { model ->
        this.map(model)
    }

    suspend fun mapAllAsync(models: Iterable<T>): List<R> = withDefaultContext {
        models.map { async { map(it) } }
            .awaitAll()
    }

    inline fun onMap(model: T, action: (R) -> Unit) {
        val mapped = this.map(model)
        action(mapped)
    }

    inline fun onMapAll(models: Iterable<T>, action: (List<R>) -> Unit) {
        val mapped = this.mapAll(models)
        action(mapped)
    }
}