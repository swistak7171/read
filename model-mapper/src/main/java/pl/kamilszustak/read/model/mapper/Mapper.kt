package pl.kamilszustak.read.model.mapper

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import pl.kamilszustak.read.common.util.withDefaultContext

abstract class Mapper<T, R, P> {
    abstract fun map(model: T, parameter: P): R

    suspend fun mapAsync(model: T, parameter: P): R = withDefaultContext {
        map(model, parameter)
    }

    fun mapAll(models: Iterable<T>, parameter: P): List<R> = models.map { model ->
        map(model, parameter)
    }

    suspend fun mapAllAsync(models: Iterable<T>, parameter: P): List<R> = withDefaultContext {
        models.map { async { map(it, parameter) } }
            .awaitAll()
    }

    inline fun onMap(model: T, parameter: P, action: (R) -> Unit) {
        val mapped = map(model, parameter)
        action(mapped)
    }

    inline fun onMapAll(models: Iterable<T>, parameter: P, action: (List<R>) -> Unit) {
        val mapped = mapAll(models, parameter)
        action(mapped)
    }
}