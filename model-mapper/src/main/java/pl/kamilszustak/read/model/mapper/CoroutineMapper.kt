package pl.kamilszustak.read.model.mapper

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import pl.kamilszustak.read.common.util.withDefaultContext

abstract class CoroutineMapper<T, R> {
    abstract suspend fun mapInternal(model: T): R

    suspend fun map(model: T): R = withDefaultContext {
        mapInternal(model)
    }

    suspend fun mapAll(models: Iterable<T>): List<R> = withDefaultContext {
        models.map { async { mapInternal(it) } }
            .awaitAll()
    }

    suspend inline fun onMap(model: T, action: (R) -> Unit) {
        val mapped = this.mapInternal(model)
        action(mapped)
    }

    suspend inline fun onMapAll(models: Iterable<T>, action: (List<R>) -> Unit) {
        val mapped = this.mapAll(models)
        action(mapped)
    }
}