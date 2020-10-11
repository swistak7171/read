package pl.kamilszustak.read.model.mapper

abstract class Mapper<T, R> {
    abstract fun map(model: T): R

    fun mapAll(models: Iterable<T>): List<R> = models.map { model ->
        this.map(model)
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