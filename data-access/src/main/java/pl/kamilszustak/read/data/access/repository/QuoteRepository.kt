package pl.kamilszustak.read.data.access.repository

import pl.kamilszustak.read.model.domain.Quote

interface QuoteRepository {
    suspend fun add(quote: Quote): Result<Unit>
}