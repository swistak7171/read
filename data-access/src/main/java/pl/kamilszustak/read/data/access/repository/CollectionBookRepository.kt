package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.domain.CollectionBook

interface CollectionBookRepository {
    suspend fun add(book: CollectionBook): Result<Unit>
}