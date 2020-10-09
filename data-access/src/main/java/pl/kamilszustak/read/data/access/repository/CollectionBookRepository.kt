package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.domain.CollectionBook

interface CollectionBookRepository {
    fun add(book: CollectionBook)
}