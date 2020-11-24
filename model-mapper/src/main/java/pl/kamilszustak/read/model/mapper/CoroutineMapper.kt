package pl.kamilszustak.read.model.mapper

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import pl.kamilszustak.read.common.util.withDefaultContext

abstract class CoroutineMapper<T, R, P> {
    abstract suspend fun mapInternal(model: T, parameter: P): R

    suspend fun map(model: T, parameter: P): R = withDefaultContext {
        mapInternal(model, parameter)
    }

    suspend fun mapAll(models: Iterable<T>, parameter: P): List<R> = withDefaultContext {
        models.map { async { mapInternal(it, parameter) } }
            .awaitAll()
    }

    suspend inline fun onMap(model: T, parameter: P, action: (R) -> Unit) {
        val mapped = this.mapInternal(model, parameter)
        action(mapped)
    }

    suspend inline fun onMapAll(models: Iterable<T>, parameter: P, action: (List<R>) -> Unit) {
        val mapped = this.mapAll(models, parameter)
        action(mapped)
    }
}