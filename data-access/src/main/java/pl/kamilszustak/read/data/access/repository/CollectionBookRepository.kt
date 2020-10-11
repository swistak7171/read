package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.data.CollectionBookEntity

interface CollectionBookRepository {
    suspend fun add(book: CollectionBookEntity): Result<Unit>
}